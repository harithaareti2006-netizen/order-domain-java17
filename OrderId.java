package com.rabtech.order.domain;

import java.util.Objects;

public record OrderId(String value) {
    public OrderId {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Order id cannot be blank");
        }
    }
}
