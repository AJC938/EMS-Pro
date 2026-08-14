# EMS-Pro

**Enterprise Employee Management System** — a Java application for managing employees, departments, positions, and attendance records using a layered architecture with **JDBC + SQLite** persistence.

## Product Scope

EMS-Pro models a small enterprise HR workflow and separates presentation, business logic, persistence, and domain models instead of placing database logic directly in the UI.

## Features

- CRUD for Employees, Departments, Positions, and Attendance
- Employee search by ID
- Attendance lookup by employee
- Summary reporting
- Duplicate-email prevention
- Input validation
- Custom exception handling
- Console-based application workflow

## Architecture

```text
Controller
    ↓
Service
    ↓
Repository
    ↓
JDBC / SQLite
```

Supporting layers include domain models, configuration, database connection management, validation, and custom exceptions.

Detailed requirements, architecture, domain modeling, glossary, and database design are documented in [`docs/`](docs).

## Project Structure

```text
EMS-Pro/
├── docs/
├── database/
│   └── schema.sql
├── src/
│   ├── main/java/com/abdullahalmutairi/emspro/
│   │   ├── controller/
│   │   ├── service/
│   │   ├── repository/
│   │   ├── model/
│   │   ├── exception/
│   │   ├── util/
│   │   ├── config/
│   │   └── database/
│   └── test/java/
├── README.md
├── LICENSE
├── .gitignore
└── pom.xml
```

## Tech Stack

- Java 17
- Maven
- SQLite
- JDBC
- JUnit 5

## Requirements

- Java 17+
- Git
- Maven Wrapper (included)

## Database Setup

The application uses a SQLite database at `database/emspro.db`. The database file is intentionally excluded from version control.

Initialize the schema with:

```bash
sqlite3 database/emspro.db < database/schema.sql
```

If SQLite CLI is unavailable, execute `database/schema.sql` using any SQLite database tool before running the application.

## Run

### Windows

```bash
mvnw.cmd clean install
mvnw.cmd compile exec:java -Dexec.mainClass="com.abdullahalmutairi.emspro.Main"
```

### macOS / Linux

```bash
./mvnw clean install
./mvnw compile exec:java -Dexec.mainClass="com.abdullahalmutairi.emspro.Main"
```

## Portfolio Status

The application is implemented as a console-based enterprise-style Java project. Final runtime screenshots are not currently stored in the repository, so broken or placeholder images have intentionally not been added.

## Future Improvements

- Broader automated unit and integration test coverage
- Additional reporting capabilities
- Externalized database configuration
- Distributable executable JAR
- Desktop or web presentation layer

## Author

**Abdullah Almutairi**  
Electrical & Computer Engineering Student · King Abdulaziz University

## License

MIT License
