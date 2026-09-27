# ADR 0002: Use immutable value objects

## Decision
Use Java records for OrderId, ProductId, Quantity, Money and OrderLine.

## Consequences
Business values cannot be mutated behind the aggregate's back. Order total is recalculated from the immutable line prices and quantities.
