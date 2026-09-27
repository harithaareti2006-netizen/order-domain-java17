package com.rabtech.order.domain;

public record Quantity(int value) {
    public Quantity {
        if (value <= 0) {
            throw new IllegalArgumentException("Quantity must be a positive whole number");
        }
    }
}
