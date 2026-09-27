package com.rabtech.order.domain;

import java.time.Instant;

public record OrderConfirmed(OrderId orderId, Instant occurredAt) implements DomainEvent {}
