package com.rabtech.order.domain;

import java.util.Objects;

public record ProductId(String value) {
    public ProductId {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Product id cannot be blank");
        }
    }
}
