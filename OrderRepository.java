package com.rabtech.order.domain.port;

import com.rabtech.order.domain.Order;
import com.rabtech.order.domain.OrderId;

import java.util.Optional;

public interface OrderRepository {
    void save(Order order);
    Optional<Order> findById(OrderId id);
}
