# ADR 0003: Represent important state changes as domain events

## Decision
Confirming, paying and cancelling an order return OrderConfirmed, PaymentRecorded and OrderCancelled events.

## Consequences
The domain can notify outside systems without depending on messaging frameworks. Notification is exposed through a port.
