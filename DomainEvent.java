package com.rabtech.order.domain;

import java.time.Instant;

public interface DomainEvent {
    OrderId orderId();
    Instant occurredAt();
}
