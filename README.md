# EMS Pro

**Enterprise Employee Management System**

A console-based Java application for managing employees, departments,
positions, and attendance records, built on a layered architecture with
JDBC and SQLite persistence.

## Description

EMS Pro is a command-line enterprise application that centralizes core
employee-management operations — creating, viewing, updating, deleting,
and searching Employee, Department, Position, and Attendance records —
backed by a SQLite database accessed through plain JDBC. The project is
organized as a layered Java application (Controller, Service, Repository,
Model) with input validation and a small custom exception layer.

## Features

- Full CRUD (Create, Read, Update, Delete) for Employees, Departments,
  Positions, and Attendance records
- Search by ID for Employees, Departments, and Positions
- Attendance lookup by Employee
- Reports: total counts for Employees, Departments, Positions, and
  Attendance records
- Duplicate-email prevention for Employees
- Input validation on employee data (email, salary)
- Console UI with menu navigation and graceful handling of invalid input

## Architecture Overview

EMS Pro follows a **Layered Architecture**:

- **Controller** — console menus and user interaction (`MainController`)
- **Service** — business rules and orchestration (`EmployeeService`,
  `DepartmentService`, `PositionService`, `AttendanceService`)
- **Repository** — data access contracts and their JDBC implementations
  (`*Repository` interfaces and `repository/impl` classes)
- **Model** — plain Java objects representing the domain entities
  (`Employee`, `Department`, `Position`, `Attendance`)
- **Validation / Exception** — input validation (`EmployeeValidator`) and
  custom exceptions (`DuplicateEmailException`)
- **Database / Config** — JDBC connection management (`ConnectionManager`)
  and configuration constants (`DatabaseConfig`)

Each layer depends only on the layer beneath it through interfaces, and
the controller depends exclusively on the service layer — it never
accesses repositories or the database directly.

For the full set of design and architecture documents produced during
development, see the [`docs/`](docs) directory:

- [Project Vision](docs/01-Project-Vision.md)
- [Software Requirements Specification](docs/02-Software-Requirements-Specification.md)
- [System Architecture](docs/03-System-Architecture.md)
- [Domain Model](docs/04-Domain-Model.md)
- [Glossary](docs/05-Glossary.md)
- [Physical Database Design](docs/06-Physical-Database-Design.md)

## Project Structure

```
EMS-Pro/
├── docs/                        # Architecture and design documentation
├── database/
│   └── schema.sql               # SQLite schema (tables, keys, indexes)
├── src/
│   ├── main/java/com/abdullahalmutairi/emspro/
│   │   ├── Main.java             # Application entry point / wiring
│   │   ├── controller/           # Console UI
│   │   ├── service/               # Business logic
│   │   ├── repository/            # Repository contracts
│   │   │   └── impl/              # JDBC repository implementations
│   │   ├── model/                 # Domain entities (POJOs)
│   │   ├── exception/             # Custom exceptions
│   │   ├── util/                  # Validation utilities
│   │   ├── config/                # Configuration constants
│   │   └── database/              # JDBC connection management
│   └── test/java                  # Unit tests
├── README.md
├── LICENSE
├── .gitignore
└── pom.xml
```

## Technologies Used

- Java 17
- Maven (with Maven Wrapper)
- SQLite
- JDBC (`org.xerial:sqlite-jdbc`)
- JUnit 5

## Requirements

- Java 17 or later
- Git
- No local Maven installation is required — the project includes the
  Maven Wrapper (`mvnw` / `mvnw.cmd`)

## Installation

Clone the repository and build the project:

```bash
git clone https://github.com/<your-username>/EMS-Pro.git
cd EMS-Pro
./mvnw clean install
```

## Database Initialization

EMS Pro uses a SQLite database file located at `database/emspro.db`. This
file is not committed to version control and must be created locally
before running the application, using the schema defined in
[`database/schema.sql`](database/schema.sql).

Using the `sqlite3` CLI:

```bash
sqlite3 database/emspro.db < database/schema.sql
```

If the `sqlite3` CLI is not available, the schema can instead be applied
through any JDBC-based SQLite client using the same
`database/schema.sql` file.

## Running the Application

Once the database has been initialized, run the application via Maven:

```bash
./mvnw compile exec:java -Dexec.mainClass="com.abdullahalmutairi.emspro.Main"
```

Alternatively, package and run the built jar:

```bash
./mvnw clean package
java -cp target/classes:$(find ~/.m2 -name "sqlite-jdbc-*.jar" | head -1) com.abdullahalmutairi.emspro.Main
```

The application starts an interactive console menu for navigating
Employees, Departments, Positions, Attendance, and Reports.

## Console Screenshots

*(Placeholder — screenshots of the running console application will be
added here.)*

## Future Improvements

- Automated unit and integration test coverage for services and
  repositories
- Additional reporting (e.g., attendance rate by department)
- Externalized database configuration (e.g., environment variables)
- Packaging as a distributable executable jar

## Author

Abdullah Almutairi

## License

This project is licensed under the MIT License — see the
[LICENSE](LICENSE) file for details.
