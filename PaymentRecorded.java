package com.rabtech.order.domain;

import java.time.Instant;

public record PaymentRecorded(OrderId orderId, Instant occurredAt) implements DomainEvent {}
