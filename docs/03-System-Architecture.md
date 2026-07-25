# EMS Pro — System Architecture

## 1. Architecture Overview

EMS Pro is built using a layered architecture on Java 17, using Maven for
build and dependency management, SQLite as the embedded data store, and
JDBC for data access. The architecture organizes the system into distinct
layers and packages, each with a clearly defined responsibility, so that
concerns such as request handling, business logic, and persistence remain
separated from one another.

## 2. Architecture Style

EMS Pro follows a Layered Architecture style (ADR-001), adopted for
separation of concerns. In this style, each layer depends on the layer
directly below it and exposes its functionality to the layer directly
above it. Business logic is confined to the Service layer (ADR-004), and
persistence access is confined to the Repository layer, which communicates
only with the database (ADR-005). This separation keeps request handling,
business rules, and data access independent of one another, so that a
change in one layer does not require changes in unrelated layers.

## 3. Layer Responsibilities

### Controller Layer
Serves as the entry point to the system. It receives incoming requests and
delegates processing to the Service layer. It does not contain business
logic.

### Service Layer
Contains all business logic for the system (ADR-004). It coordinates
operations across one or more repositories and applies the business rules
that govern how employee, department, position, and attendance data is
processed.

### Repository Layer
Implements the Repository Pattern (ADR-002) to separate business logic
from persistence. Repositories communicate only with the database
(ADR-005) and do not contain business logic. They provide the Service
layer with access to persisted data without exposing database-specific
details.

### Model Layer
Represents business entities only (ADR-006). Models do not contain
validation logic (ADR-007); validation is the responsibility of the layers
that operate on the models, not the models themselves.

## 4. Package Responsibilities

- **controller** — Entry-point classes that receive requests and delegate
  to the Service layer.
- **service** — Business logic for the system (ADR-004).
- **repository** — Data access classes implementing the Repository
  Pattern (ADR-002), communicating only with the database (ADR-005).
- **model** — Business entity representations only (ADR-006), without
  validation logic (ADR-007).
- **database** — Underlying database connectivity that the Repository
  layer depends on to interact with SQLite via JDBC.
- **config** — Configuration classes for the system (ADR-008).
- **exception** — Custom exceptions for the system (ADR-009).
- **util** — Utility classes supporting the system (ADR-010).

## 5. Data Flow

The system's data flow follows the direction of the layered architecture:

1. A request is received by the Controller layer.
2. The Controller layer delegates the request to the Service layer.
3. The Service layer applies business logic and, where persistence is
   required, invokes one or more Repositories.
4. The Repository layer communicates with the database package to access
   data stored in SQLite via JDBC.
5. Data returned from the database flows back through the Repository
   layer to the Service layer, which applies any necessary business logic
   before returning a result to the Controller layer.

Cross-cutting packages (config, exception, util) support this flow without
participating in it directly: config supplies configuration used across
layers, exception defines error types that may be raised by any layer, and
util provides shared utility functions.

## 6. Architecture Decisions (ADR)

**ADR-001 — Use Layered Architecture**
Reason: Separation of concerns.

**ADR-002 — Use Repository Pattern**
Reason: Separate business logic from persistence.

**ADR-003 — Use SQLite**
Reason: Embedded database with zero server configuration.

**ADR-004 — Business logic belongs only inside Service Layer**

**ADR-005 — Repositories communicate only with the database**

**ADR-006 — Models represent business entities only**

**ADR-007 — Validation logic must not exist inside Models**

**ADR-008 — Configuration classes belong inside config package**

**ADR-009 — Custom Exceptions belong inside exception package**

**ADR-010 — Utility classes belong inside util package**

## 7. Advantages

- Clear separation of concerns across layers simplifies maintenance and
  reduces the impact of changes in one layer on other layers.
- The Repository Pattern isolates persistence details from business
  logic, so the Service layer does not depend on how data is stored.
- Confining business logic to the Service layer (ADR-004) and keeping
  Models free of validation logic (ADR-007) results in a single,
  predictable location for business rules.
- SQLite requires no server configuration (ADR-003), simplifying setup and
  deployment for the target use case of small and medium companies.
- Dedicated packages for configuration, exceptions, and utilities
  (ADR-008, ADR-009, ADR-010) keep cross-cutting concerns organized and
  easy to locate.

## 8. Limitations

- SQLite is an embedded database and is subject to the concurrency and
  scalability characteristics of embedded database engines, which may
  constrain use in scenarios with a large number of simultaneous users.
- Strict layering requires requests to pass through each layer in
  sequence, which introduces additional indirection compared to a less
  structured design.
- Because Repositories communicate only with the database (ADR-005),
  any data access need must be routed through the Repository layer, which
  requires discipline to maintain as the system grows.

## 9. Future Improvements

The specification does not define specific future architectural changes.
The layered structure and the Repository Pattern (ADR-002) are intended to
allow the persistence mechanism to be reconsidered in the future without
requiring changes to the Service or Controller layers, but no specific
future improvements are defined at this time.
