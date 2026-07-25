# EMS Pro — Domain Model

## 1. Domain Overview

This document describes the business domain of EMS Pro, an Enterprise
Employee Management System. The domain is composed of four business
entities — Employee, Department, Position, and Attendance — which
together support the stated business features: Employee Management,
Department Management, Position Management, Attendance Tracking, Search,
and Reporting. This document describes the domain conceptually and does
not describe implementation.

## 2. Business Entities

- **Employee** — represents an individual employed by the company.
- **Department** — represents an organizational unit to which employees
  belong.
- **Position** — represents a job role that MAY be held by an employee.
- **Attendance** — represents a record associated with an employee's
  attendance.

## 3. Entity Responsibilities

### Employee
The Employee entity is responsible for representing an individual within
the organization who MUST belong to exactly one Department and MUST hold
exactly one Position.

### Department
The Department entity is responsible for representing an organizational
grouping. A Department MAY contain multiple Employees.

### Position
The Position entity is responsible for representing a job role within the
organization. A Position MAY be assigned to multiple Employees.

### Attendance
The Attendance entity is responsible for representing a record that MUST
belong to exactly one Employee.

## 4. Entity Attributes (High-Level Only)

The following attributes reflect only what is necessary to satisfy the
stated business rules and business features. No additional attributes are
defined.

### Employee
- A unique identifier (Employee ID MUST be unique).
- A unique email address (Employee email MUST be unique).
- A salary value (Salary MUST be zero or greater).
- An association to exactly one Department.
- An association to exactly one Position.

### Department
- An identifying attribute distinguishing one Department from another, as
  required to support Department Management.

### Position
- An identifying attribute distinguishing one Position from another, as
  required to support Position Management.

### Attendance
- An association to exactly one Employee.
- An attribute identifying the point in time the attendance record
  represents, as required to support Attendance Tracking.

## 5. Business Rules

- Every Employee MUST belong to exactly one Department.
- Every Employee MUST have exactly one Position.
- Employee email MUST be unique.
- Employee ID MUST be unique.
- Salary MUST be zero or greater.
- Attendance MUST belong to one Employee.
- Departments MAY contain multiple Employees.
- Positions MAY be assigned to multiple Employees.

## 6. Relationships

- **Employee to Department** — Every Employee MUST belong to exactly one
  Department. A Department MAY contain multiple Employees.
- **Employee to Position** — Every Employee MUST have exactly one
  Position. A Position MAY be assigned to multiple Employees.
- **Employee to Attendance** — Every Attendance record MUST belong to
  exactly one Employee. The specification does not state whether an
  Employee MAY be associated with multiple Attendance records; this is
  addressed in Domain Assumptions.

## 7. Constraints

- Employee ID MUST be unique across all Employees.
- Employee email MUST be unique across all Employees.
- Salary MUST be zero or greater.
- Every Employee MUST be associated with exactly one Department, no more
  and no fewer.
- Every Employee MUST be associated with exactly one Position, no more
  and no fewer.
- Every Attendance record MUST be associated with exactly one Employee.

## 8. Domain Assumptions

- The specification does not define whether an Employee MAY have multiple
  Attendance records. This is reported here rather than assumed.
- The specification does not define attributes for Department and
  Position beyond an identifying attribute necessary to support Department
  Management and Position Management. This is reported here rather than
  assumed.
- The specification does not define additional Employee attributes beyond
  those required by the stated Business Rules. This is reported here
  rather than assumed.

## 9. Future Domain Expansion

The specification does not define future domain expansion. No additional
entities, attributes, or relationships beyond those documented above are
defined at this time.
