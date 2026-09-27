package com.rabtech.order.adapters;

import com.rabtech.order.domain.Order;
import com.rabtech.order.domain.OrderId;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryAdaptersTest {

    @Test
    void repositoryStoresAndLoadsOrder() {
        InMemoryOrderRepository repository = new InMemoryOrderRepository();
        Order order = new Order(new OrderId("O-1"));

        repository.save(order);

        assertTrue(repository.findById(new OrderId("O-1")).isPresent());
    }
}
