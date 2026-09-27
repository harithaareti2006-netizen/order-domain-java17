package com.rabtech.order.domain;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.Instant;

import static org.junit.jupiter.api.Assertions.*;

class OrderTest {

    private final Instant now = Instant.parse("2026-09-27T00:00:00Z");

    private Order draftWithLine() {
        Order order = new Order(new OrderId("O-100"));
        order.addLine(new OrderLine(
                new ProductId("P-1"),
                new Quantity(2),
                new Money(new BigDecimal("150.00"), "INR")
        ));
        return order;
    }

    @Test
    void cannotConfirmEmptyOrder() {
        Order order = new Order(new OrderId("O-1"));
        assertThrows(IllegalStateException.class, () -> order.confirm(now));
    }

    @Test
    void quantityMustBePositive() {
        assertThrows(IllegalArgumentException.class, () -> new Quantity(0));
        assertThrows(IllegalArgumentException.class, () -> new Quantity(-1));
    }

    @Test
    void confirmsOrderWithAtLeastOneLine() {
        Order order = draftWithLine();

        OrderConfirmed event = order.confirm(now);

        assertEquals(OrderStatus.CONFIRMED, order.status());
        assertEquals(order.id(), event.orderId());
    }

    @Test
    void cancelledOrderCannotBePaid() {
        Order order = draftWithLine();
        order.cancel(now);

        assertThrows(IllegalStateException.class, () -> order.recordPayment(now));
    }

    @Test
    void paidOrderCannotReturnToDraft() {
        Order order = draftWithLine();
        order.confirm(now);
        order.recordPayment(now);

        assertNotEquals(OrderStatus.DRAFT, order.status());
        assertThrows(IllegalStateException.class, () -> order.addLine(
                new OrderLine(new ProductId("P-2"), new Quantity(1),
                        new Money(new BigDecimal("50"), "INR"))
        ));
    }

    @Test
    void totalIsDerivedFromLinePricesAndQuantities() {
        Order order = draftWithLine();
        order.addLine(new OrderLine(
                new ProductId("P-2"),
                new Quantity(3),
                new Money(new BigDecimal("100.00"), "INR")
        ));

        assertEquals(new BigDecimal("600.00"), order.total().amount());
    }
}
