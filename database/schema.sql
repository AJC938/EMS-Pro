CREATE TABLE Departments (
    DepartmentID INTEGER NOT NULL,
    Name         TEXT NOT NULL,
    CONSTRAINT pk_departments PRIMARY KEY (DepartmentID)
);

CREATE TABLE Positions (
    PositionID INTEGER NOT NULL,
    Name       TEXT NOT NULL,
    CONSTRAINT pk_positions PRIMARY KEY (PositionID)
);

CREATE TABLE Employees (
    EmployeeID   INTEGER NOT NULL,
    Email        TEXT NOT NULL,
    Salary       REAL NOT NULL,
    DepartmentID INTEGER NOT NULL,
    PositionID   INTEGER NOT NULL,
    CONSTRAINT pk_employees PRIMARY KEY (EmployeeID),
    CONSTRAINT uq_employees_email UNIQUE (Email),
    CONSTRAINT ck_employees_salary CHECK (Salary >= 0),
    CONSTRAINT fk_employees_department FOREIGN KEY (DepartmentID) REFERENCES Departments (DepartmentID),
    CONSTRAINT fk_employees_position FOREIGN KEY (PositionID) REFERENCES Positions (PositionID)
);

CREATE TABLE Attendance (
    AttendanceID   INTEGER NOT NULL,
    EmployeeID     INTEGER NOT NULL,
    AttendanceDate TEXT NOT NULL,
    CONSTRAINT pk_attendance PRIMARY KEY (AttendanceID),
    CONSTRAINT fk_attendance_employee FOREIGN KEY (EmployeeID) REFERENCES Employees (EmployeeID)
);

CREATE UNIQUE INDEX idx_employees_email ON Employees (Email);

CREATE INDEX idx_employees_department ON Employees (DepartmentID);

CREATE INDEX idx_employees_position ON Employees (PositionID);

CREATE INDEX idx_attendance_employee ON Attendance (EmployeeID);

CREATE INDEX idx_attendance_date ON Attendance (AttendanceDate);
