package com.rabtech.order.adapters;

import com.rabtech.order.domain.port.ClockPort;

import java.time.Instant;

public final class SystemClockAdapter implements ClockPort {
    @Override
    public Instant now() {
        return Instant.now();
    }
}
