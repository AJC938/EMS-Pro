# EMS Pro — Glossary

This glossary defines the official business vocabulary used throughout
EMS Pro. It is the single source of truth for terminology. All future
documentation must use these definitions consistently.

## Employee

**Definition:** An individual employed by the organization, represented
within the system as a business entity.

**Purpose:** Serves as the central subject of employee management,
department assignment, position assignment, and attendance tracking.

**Related Terms:** Department, Position, Attendance, Employee ID,
Employee Email, Salary, Entity

**Notes:** Every Employee MUST belong to exactly one Department and MUST
have exactly one Position.

## Department

**Definition:** An organizational unit to which one or more Employees
belong.

**Purpose:** Groups Employees for organizational and reporting purposes.

**Related Terms:** Employee, Entity

**Notes:** A Department MAY contain multiple Employees.

## Position

**Definition:** A job role that MAY be assigned to one or more Employees.

**Purpose:** Identifies the role held by an Employee within the
organization.

**Related Terms:** Employee, Entity

**Notes:** A Position MAY be assigned to multiple Employees.

## Attendance

**Definition:** A record associated with an Employee's attendance.

**Purpose:** Supports tracking of Employee attendance.

**Related Terms:** Employee, Entity

**Notes:** Attendance MUST belong to exactly one Employee. Whether an
Employee MAY have multiple Attendance records is Not Yet Defined.

## Employee ID

**Definition:** A unique identifier assigned to an Employee.

**Purpose:** Distinguishes one Employee from another.

**Related Terms:** Employee, Constraint

**Notes:** Employee ID MUST be unique.

## Employee Email

**Definition:** An email address associated with an Employee.

**Purpose:** Distinguishes one Employee from another by contact
identifier.

**Related Terms:** Employee, Constraint

**Notes:** Employee Email MUST be unique.

## Salary

**Definition:** A monetary value associated with an Employee.

**Purpose:** Represents the compensation value recorded for an Employee.

**Related Terms:** Employee, Constraint

**Notes:** Salary MUST be zero or greater.

## Business Rule

**Definition:** A statement that constrains or defines an aspect of the
business domain that the system MUST enforce.

**Purpose:** Establishes authoritative business behavior independent of
implementation.

**Related Terms:** Constraint, Domain Model

**Notes:** Business Rules for EMS Pro are documented in the Domain Model.

## Domain Model

**Definition:** A conceptual representation of the business entities,
their responsibilities, attributes, relationships, and rules within
EMS Pro.

**Purpose:** Provides a shared, implementation-independent understanding
of the business domain.

**Related Terms:** Entity, Business Rule

**Notes:** None.

## Layered Architecture

**Definition:** An architectural style that organizes a system into
distinct layers, each with a defined responsibility, where each layer
depends only on the layer directly below it.

**Purpose:** Separates concerns such as request handling, business logic,
and data access from one another.

**Related Terms:** Service Layer, Controller, Repository

**Notes:** None.

## Repository Pattern

**Definition:** A pattern that separates business logic from persistence
access by providing a dedicated layer responsible for data access.

**Purpose:** Isolates the Service Layer from the details of how data is
accessed.

**Related Terms:** Repository, Service Layer, Layered Architecture

**Notes:** None.

## Service Layer

**Definition:** The layer responsible for containing business logic
within a Layered Architecture.

**Purpose:** Coordinates operations and applies Business Rules.

**Related Terms:** Layered Architecture, Controller, Repository,
Business Rule

**Notes:** None.

## Controller

**Definition:** The entry-point layer responsible for receiving requests
and delegating them to the Service Layer.

**Purpose:** Provides the point of entry into the system without
containing business logic.

**Related Terms:** Layered Architecture, Service Layer

**Notes:** None.

## Repository

**Definition:** A component responsible for accessing persisted data,
communicating only with the underlying data store.

**Purpose:** Provides the Service Layer with access to data without
exposing data-store-specific details.

**Related Terms:** Repository Pattern, Layered Architecture, Service
Layer

**Notes:** None.

## Entity

**Definition:** A conceptual representation of a business object within
the Domain Model.

**Purpose:** Identifies the core business concepts that the system
manages.

**Related Terms:** Domain Model, Employee, Department, Position,
Attendance

**Notes:** None.

## Constraint

**Definition:** A condition that restricts the acceptable values or
relationships of an Entity.

**Purpose:** Enforces data integrity within the Domain Model.

**Related Terms:** Business Rule, Entity

**Notes:** None.

## Validation

**Definition:** The process of confirming that data satisfies defined
Constraints and Business Rules.

**Purpose:** Ensures data integrity is enforced before data is accepted
by the system.

**Related Terms:** Constraint, Business Rule

**Notes:** Validation logic MUST NOT exist inside Models.
