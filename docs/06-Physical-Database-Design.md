# EMS Pro — Physical Database Design

## 1. Database Overview

EMS Pro uses SQLite as its database engine. This document describes the
physical database design derived from the Domain Model (docs/04, business
entities: Employee, Department, Position, Attendance) and the Business
Rules stated in the authoritative context. It documents table structure,
columns, keys, constraints, and indexes at a physical design level. It
does not contain SQL syntax or implementation code.

Where the Domain Model or Business Rules did not define a specific
physical detail, this document introduces the minimum structure required
for a relational table to exist (for example, a primary key column), and
explicitly notes this where applicable. Business-level detail that is not
defined anywhere in prior documentation is marked "Not Yet Defined" rather
than assumed.

## 2. Table Definitions

| Table | Purpose |
|---|---|
| Employees | Stores records representing individuals employed by the organization. |
| Departments | Stores records representing organizational units. |
| Positions | Stores records representing job roles. |
| Attendance | Stores records representing an Employee's attendance. |

## 3. Column Definitions

### Employees

| Column | Data Type | Nullable | Default Value | Notes |
|---|---|---|---|---|
| EmployeeID | INTEGER | No | Not Yet Defined | Unique identifier for the Employee, per the Business Rule that Employee ID MUST be unique. |
| Email | TEXT | No | Not Yet Defined | Employee email, per the Business Rule that Employee Email MUST be unique. |
| Salary | REAL | No | Not Yet Defined | Compensation value, per the Business Rule that Salary MUST be zero or greater. |
| DepartmentID | INTEGER | No | Not Yet Defined | References the Department the Employee belongs to, per the Business Rule that every Employee MUST belong to exactly one Department. |
| PositionID | INTEGER | No | Not Yet Defined | References the Position the Employee holds, per the Business Rule that every Employee MUST have exactly one Position. |

Additional Employee attributes beyond those required by the stated
Business Rules are Not Yet Defined, consistent with the Domain Model
(docs/04-Domain-Model.md).

### Departments

| Column | Data Type | Nullable | Default Value | Notes |
|---|---|---|---|---|
| DepartmentID | INTEGER | No | Not Yet Defined | Physical identifier for the Department. Not itself named by a Business Rule; introduced as the minimum structure required to uniquely identify a Department record. |
| Name | TEXT | No | Not Yet Defined | Physical realization of the identifying attribute required by the Domain Model to distinguish one Department from another. |

Additional Department attributes are Not Yet Defined.

### Positions

| Column | Data Type | Nullable | Default Value | Notes |
|---|---|---|---|---|
| PositionID | INTEGER | No | Not Yet Defined | Physical identifier for the Position. Not itself named by a Business Rule; introduced as the minimum structure required to uniquely identify a Position record. |
| Name | TEXT | No | Not Yet Defined | Physical realization of the identifying attribute required by the Domain Model to distinguish one Position from another. |

Additional Position attributes are Not Yet Defined.

### Attendance

| Column | Data Type | Nullable | Default Value | Notes |
|---|---|---|---|---|
| AttendanceID | INTEGER | No | Not Yet Defined | Physical identifier for the Attendance record. Not itself named by a Business Rule; introduced as the minimum structure required to uniquely identify an Attendance record. |
| EmployeeID | INTEGER | No | Not Yet Defined | References the Employee the Attendance record belongs to, per the Business Rule that Attendance MUST belong to exactly one Employee. |
| AttendanceDate | TEXT | No | Not Yet Defined | Physical realization of the point-in-time attribute required by the Domain Model. Represented as TEXT (ISO-8601), a SQLite-compatible convention for date values, since SQLite has no dedicated date storage class. |

Whether an Attendance record additionally captures a status (for example,
present or absent) is Not Yet Defined. Additional Attendance attributes
are Not Yet Defined.

## 4. Primary Keys

| Table | Primary Key | Rationale |
|---|---|---|
| Employees | EmployeeID | Enforces the Business Rule that Employee ID MUST be unique and provides the identity used by Department, Position, and Attendance relationships. |
| Departments | DepartmentID | Provides a unique identity for each Department, required for Employees to reference a specific Department. |
| Positions | PositionID | Provides a unique identity for each Position, required for Employees to reference a specific Position. |
| Attendance | AttendanceID | Provides a unique identity for each Attendance record. |

## 5. Foreign Keys

| Table | Foreign Key | References | Rationale |
|---|---|---|---|
| Employees | DepartmentID | Departments.DepartmentID | Enforces the Business Rule that every Employee MUST belong to exactly one Department. |
| Employees | PositionID | Positions.PositionID | Enforces the Business Rule that every Employee MUST have exactly one Position. |
| Attendance | EmployeeID | Employees.EmployeeID | Enforces the Business Rule that Attendance MUST belong to exactly one Employee. |

No foreign keys exist on Departments or Positions. This reflects the
Business Rules that Departments MAY contain multiple Employees and
Positions MAY be assigned to multiple Employees, meaning the relationship
is owned by the Employees table.

## 6. Constraints

| Table | Constraint | Rationale |
|---|---|---|
| Employees | Primary key on EmployeeID | Enforces uniqueness of Employee ID. |
| Employees | Unique constraint on Email | Enforces the Business Rule that Employee Email MUST be unique. |
| Employees | Check constraint: Salary >= 0 | Enforces the Business Rule that Salary MUST be zero or greater. |
| Employees | Not-null on DepartmentID | Enforces the Business Rule that every Employee MUST belong to exactly one Department. |
| Employees | Not-null on PositionID | Enforces the Business Rule that every Employee MUST have exactly one Position. |
| Departments | Primary key on DepartmentID | Enforces uniqueness of Department identity. |
| Departments | Not-null on Name | Ensures the identifying attribute required by the Domain Model is always present. |
| Positions | Primary key on PositionID | Enforces uniqueness of Position identity. |
| Positions | Not-null on Name | Ensures the identifying attribute required by the Domain Model is always present. |
| Attendance | Primary key on AttendanceID | Enforces uniqueness of Attendance record identity. |
| Attendance | Not-null on EmployeeID | Enforces the Business Rule that Attendance MUST belong to exactly one Employee. |
| Attendance | Not-null on AttendanceDate | Ensures the point-in-time attribute required by the Domain Model is always present. |

## 7. Recommended Indexes

| Table | Index | Rationale |
|---|---|---|
| Employees | Unique index on Email | Supports enforcement of the Email uniqueness constraint and efficient lookup by email. |
| Employees | Index on DepartmentID | Supports retrieval of Employees belonging to a given Department, consistent with Department Management. |
| Employees | Index on PositionID | Supports retrieval of Employees holding a given Position, consistent with Position Management. |
| Attendance | Index on EmployeeID | Supports retrieval of Attendance records for a specific Employee, consistent with Attendance Tracking. |
| Attendance | Index on AttendanceDate | Supports retrieval of Attendance records within a specified date range, consistent with Attendance Tracking. |

## 8. Design Rationale

The physical design mirrors the Domain Model directly: each Business
Entity (Employee, Department, Position, Attendance) maps to one table.
Primary keys and foreign keys are used to enforce the cardinality rules
stated in the Business Rules — an Employee referencing exactly one
Department and exactly one Position, and an Attendance record referencing
exactly one Employee.

Where a table required a physical identifier that was not itself named by
a Business Rule (DepartmentID, PositionID, AttendanceID), a surrogate
identifier is introduced as the minimum structure necessary for the table
to function as a relational table with enforceable uniqueness and
referenceability. This is a physical design necessity rather than an
additional business concept.

SQLite is used as specified in the Authoritative Context. Data types are
chosen from SQLite's supported storage classes (INTEGER, TEXT, REAL).
Dates are represented as TEXT following the ISO-8601 convention, since
SQLite does not provide a dedicated date storage class.

## 9. Future Database Expansion

Not Yet Defined. The specification does not describe future database
expansion beyond the tables, columns, keys, constraints, and indexes
documented above.
