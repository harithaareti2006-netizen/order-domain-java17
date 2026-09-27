package com.rabtech.order.adapters;

import com.rabtech.order.domain.DomainEvent;
import com.rabtech.order.domain.port.NotificationPort;

import java.util.ArrayList;
import java.util.List;

public final class InMemoryNotificationAdapter implements NotificationPort {
    private final List<DomainEvent> events = new ArrayList<>();

    @Override
    public void publish(DomainEvent event) {
        events.add(event);
    }

    public List<DomainEvent> events() {
        return List.copyOf(events);
    }
}
