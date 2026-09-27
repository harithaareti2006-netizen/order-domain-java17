# Order Domain - Java 17

A pure domain implementation for the Order aggregate.

## Requirements covered
- States: DRAFT, CONFIRMED, PAID, CANCELLED
- An order must contain at least one line before confirmation
- Quantity must be a positive whole number
- Cancelled orders cannot be paid
- Paid orders cannot return to draft
- Total is derived from immutable line prices and quantities
- Domain is independent of Spring, JPA, HTTP and database adapters

## Build and test

```bash
mvn test
```

Java 17 is required.

## Suggested GitHub submission

Create a public GitHub repository and upload this project. Then submit the repository URL to the internship task.
