# EMS Pro

**Enterprise Employee Management System**

A Java application for managing employees, departments, positions, and attendance records using a layered architecture with JDBC and SQLite persistence.

## Features

- Full CRUD for Employees, Departments, Positions, and Attendance
- Search by ID
- Attendance lookup by employee
- Summary reports
- Duplicate-email prevention
- Input validation
- Console-based menu navigation

## Architecture

EMS Pro follows a **Layered Architecture**:

- **Controller** — console UI and user interaction
- **Service** — business rules and orchestration
- **Repository** — data-access contracts and JDBC implementations
- **Model** — domain entities
- **Validation / Exception** — validation and custom exceptions
- **Database / Config** — SQLite and JDBC connection management

See the [`docs/`](docs) directory for the project's requirements, architecture, domain model, glossary, and database design.

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

## Technologies

- Java 17
- Maven
- SQLite
- JDBC
- JUnit 5

## Requirements

- Java 17 or later
- Git
- Maven Wrapper included

## Database

The application uses a SQLite database at `database/emspro.db`. The database file is intentionally not committed to version control. Initialize it using `database/schema.sql` before running the application.

## Running

```bash
./mvnw clean install
./mvnw compile exec:java -Dexec.mainClass="com.abdullahalmutairi.emspro.Main"
```

## Console Screenshots

Screenshots of the running console application should be added here once the final UI/output captures are prepared.

## Future Improvements

- Automated unit and integration test coverage
- Additional reporting
- Externalized database configuration
- Distributable executable JAR
- Richer desktop UI

## Author

**Abdullah Almutairi**  
Electrical & Computer Engineering Student · King Abdulaziz University

## License

MIT License
