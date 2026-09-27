package com.rabtech.order.domain;

public record OrderLine(ProductId productId, Quantity quantity, Money unitPrice) {

    public OrderLine {
        if (productId == null || quantity == null || unitPrice == null) {
            throw new IllegalArgumentException("Order line fields are required");
        }
    }

    public Money total() {
        return unitPrice.multiply(quantity.value());
    }
}
