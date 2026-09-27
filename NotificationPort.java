package com.rabtech.order.domain.port;

import com.rabtech.order.domain.DomainEvent;

public interface NotificationPort {
    void publish(DomainEvent event);
}
