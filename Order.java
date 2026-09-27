package com.rabtech.order.domain;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public final class Order {
    private final OrderId id;
    private final List<OrderLine> lines = new ArrayList<>();
    private OrderStatus status = OrderStatus.DRAFT;

    public Order(OrderId id) {
        this.id = id;
    }

    public void addLine(OrderLine line) {
        if (status != OrderStatus.DRAFT) {
            throw new IllegalStateException("Lines can only be added to a draft order");
        }
        lines.add(line);
    }

    public OrderConfirmed confirm(Instant now) {
        if (lines.isEmpty()) {
            throw new IllegalStateException("An order must contain at least one line before confirmation");
        }
        if (status != OrderStatus.DRAFT) {
            throw new IllegalStateException("Only a draft order can be confirmed");
        }
        status = OrderStatus.CONFIRMED;
        return new OrderConfirmed(id, now);
    }

    public PaymentRecorded recordPayment(Instant now) {
        if (status == OrderStatus.CANCELLED) {
            throw new IllegalStateException("A cancelled order cannot be paid");
        }
        if (status != OrderStatus.CONFIRMED) {
            throw new IllegalStateException("Only a confirmed order can be paid");
        }
        status = OrderStatus.PAID;
        return new PaymentRecorded(id, now);
    }

    public OrderCancelled cancel(Instant now) {
        if (status == OrderStatus.PAID) {
            throw new IllegalStateException("A paid order cannot be cancelled");
        }
        if (status == OrderStatus.CANCELLED) {
            throw new IllegalStateException("Order is already cancelled");
        }
        status = OrderStatus.CANCELLED;
        return new OrderCancelled(id, now);
    }

    public OrderId id() {
        return id;
    }

    public OrderStatus status() {
        return status;
    }

    public List<OrderLine> lines() {
        return List.copyOf(lines);
    }

    public Money total() {
        if (lines.isEmpty()) {
            return Money.zero("INR");
        }
        Money total = Money.zero(lines.get(0).unitPrice().currency());
        for (OrderLine line : lines) {
            total = total.add(line.total());
        }
        return total;
    }
}
