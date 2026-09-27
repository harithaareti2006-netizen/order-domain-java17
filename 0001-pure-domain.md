# ADR 0001: Keep the domain module framework independent

## Decision
The domain module contains only Java domain objects and Java standard-library types.

## Consequences
Spring, JPA, HTTP and database-specific code cannot leak into the domain model. Infrastructure integrations are implemented through ports and adapters.
