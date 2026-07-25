package com.abdullahalmutairi.emspro.controller;

import com.abdullahalmutairi.emspro.exception.DuplicateEmailException;
import com.abdullahalmutairi.emspro.model.Attendance;
import com.abdullahalmutairi.emspro.model.Department;
import com.abdullahalmutairi.emspro.model.Employee;
import com.abdullahalmutairi.emspro.model.Position;
import com.abdullahalmutairi.emspro.service.AttendanceService;
import com.abdullahalmutairi.emspro.service.DepartmentService;
import com.abdullahalmutairi.emspro.service.EmployeeService;
import com.abdullahalmutairi.emspro.service.PositionService;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.Scanner;

public class MainController {

    private final Scanner scanner = new Scanner(System.in).useLocale(Locale.US);
    private final EmployeeService employeeService;
    private final DepartmentService departmentService;
    private final PositionService positionService;
    private final AttendanceService attendanceService;

    public MainController(EmployeeService employeeService,
                           DepartmentService departmentService,
                           PositionService positionService,
                           AttendanceService attendanceService) {
        this.employeeService = employeeService;
        this.departmentService = departmentService;
        this.positionService = positionService;
        this.attendanceService = attendanceService;
    }

    public void start() {
        boolean running = true;

        while (running) {
            printMainMenu();
            String input = scanner.nextLine();

            switch (input) {
                case "1":
                    employeeMenu();
                    break;
                case "2":
                    departmentMenu();
                    break;
                case "3":
                    positionMenu();
                    break;
                case "4":
                    attendanceMenu();
                    break;
                case "5":
                    reportsMenu();
                    break;
                case "0":
                    System.out.println("Goodbye.");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option.");
                    break;
            }
        }
    }

    private void printMainMenu() {
        System.out.println("=================================");
        System.out.println(" EMS Pro");
        System.out.println("=================================");
        System.out.println("1. Employees");
        System.out.println("2. Departments");
        System.out.println("3. Positions");
        System.out.println("4. Attendance");
        System.out.println("5. Reports");
        System.out.println("0. Exit");
        System.out.print("Choose an option: ");
    }

    // ---------------------------------------------------------------
    // Employee Menu
    // ---------------------------------------------------------------

    private void employeeMenu() {
        boolean inMenu = true;

        while (inMenu) {
            printEmployeeMenu();
            String input = scanner.nextLine();

            switch (input) {
                case "1":
                    addEmployee();
                    break;
                case "2":
                    viewAllEmployees();
                    break;
                case "3":
                    updateEmployee();
                    break;
                case "4":
                    deleteEmployee();
                    break;
                case "5":
                    searchEmployee();
                    break;
                case "0":
                    inMenu = false;
                    break;
                default:
                    System.out.println("Invalid option.");
                    break;
            }
        }
    }

    private void printEmployeeMenu() {
        System.out.println("=================================");
        System.out.println(" Employee Management");
        System.out.println("=================================");
        System.out.println("1. Add Employee");
        System.out.println("2. View All Employees");
        System.out.println("3. Update Employee");
        System.out.println("4. Delete Employee");
        System.out.println("5. Search Employee By ID");
        System.out.println("0. Back");
        System.out.print("Choose an option: ");
    }

    private void addEmployee() {
        try {
            int employeeId = readInt("Employee ID: ");
            String email = readLine("Email: ");
            double salary = readDouble("Salary: ");
            int departmentId = readInt("Department ID: ");
            int positionId = readInt("Position ID: ");

            Employee employee = new Employee(employeeId, email, salary, departmentId, positionId);
            employeeService.addEmployee(employee);
            System.out.println("Employee added successfully.");
        } catch (DuplicateEmailException e) {
            System.out.println(e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("Validation failed: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Database operation failed.");
        } catch (InputMismatchException e) {
            scanner.nextLine();
            System.out.println("Invalid input.");
        }
    }

    private void viewAllEmployees() {
        execute(() -> {
            List<Employee> employees = employeeService.getAllEmployees();

            if (employees.isEmpty()) {
                System.out.println("No employees found.");
            } else {
                for (Employee employee : employees) {
                    System.out.println(employee);
                }
            }
        });
    }

    private void updateEmployee() {
        execute(() -> {
            int employeeId = readInt("Employee ID: ");
            Optional<Employee> existingEmployee = employeeService.getEmployeeById(employeeId);

            if (existingEmployee.isEmpty()) {
                System.out.println("Employee not found.");
                return;
            }

            String email = readLine("Email: ");
            double salary = readDouble("Salary: ");
            int departmentId = readInt("Department ID: ");
            int positionId = readInt("Position ID: ");

            Employee employee = new Employee(employeeId, email, salary, departmentId, positionId);
            employeeService.updateEmployee(employee);
            System.out.println("Employee updated successfully.");
        });
    }

    private void deleteEmployee() {
        execute(() -> {
            int employeeId = readInt("Employee ID: ");
            Optional<Employee> existingEmployee = employeeService.getEmployeeById(employeeId);

            if (existingEmployee.isEmpty()) {
                System.out.println("Employee not found.");
                return;
            }

            employeeService.deleteEmployee(employeeId);
            System.out.println("Employee deleted successfully.");
        });
    }

    private void searchEmployee() {
        execute(() -> {
            int employeeId = readInt("Employee ID: ");
            Optional<Employee> employee = employeeService.getEmployeeById(employeeId);

            if (employee.isEmpty()) {
                System.out.println("Employee not found.");
            } else {
                System.out.println(employee.get());
            }
        });
    }

    // ---------------------------------------------------------------
    // Department Menu
    // ---------------------------------------------------------------

    private void departmentMenu() {
        boolean inMenu = true;

        while (inMenu) {
            printDepartmentMenu();
            String input = scanner.nextLine();

            switch (input) {
                case "1":
                    addDepartment();
                    break;
                case "2":
                    viewAllDepartments();
                    break;
                case "3":
                    updateDepartment();
                    break;
                case "4":
                    deleteDepartment();
                    break;
                case "5":
                    searchDepartment();
                    break;
                case "0":
                    inMenu = false;
                    break;
                default:
                    System.out.println("Invalid option.");
                    break;
            }
        }
    }

    private void printDepartmentMenu() {
        System.out.println("=================================");
        System.out.println(" Department Management");
        System.out.println("=================================");
        System.out.println("1. Add Department");
        System.out.println("2. View All Departments");
        System.out.println("3. Update Department");
        System.out.println("4. Delete Department");
        System.out.println("5. Search Department By ID");
        System.out.println("0. Back");
        System.out.print("Choose an option: ");
    }

    private void addDepartment() {
        execute(() -> {
            int departmentId = readInt("Department ID: ");
            String name = readLine("Name: ");

            Department department = new Department(departmentId, name);
            departmentService.addDepartment(department);
            System.out.println("Operation completed successfully.");
        });
    }

    private void viewAllDepartments() {
        execute(() -> {
            List<Department> departments = departmentService.getAllDepartments();
            printAll(departments);
        });
    }

    private void updateDepartment() {
        execute(() -> {
            int departmentId = readInt("Department ID: ");
            Optional<Department> existingDepartment = departmentService.getDepartmentById(departmentId);

            if (existingDepartment.isEmpty()) {
                System.out.println("Record not found.");
                return;
            }

            String name = readLine("Name: ");

            Department department = new Department(departmentId, name);
            departmentService.updateDepartment(department);
            System.out.println("Operation completed successfully.");
        });
    }

    private void deleteDepartment() {
        execute(() -> {
            int departmentId = readInt("Department ID: ");
            Optional<Department> existingDepartment = departmentService.getDepartmentById(departmentId);

            if (existingDepartment.isEmpty()) {
                System.out.println("Record not found.");
                return;
            }

            departmentService.deleteDepartment(departmentId);
            System.out.println("Operation completed successfully.");
        });
    }

    private void searchDepartment() {
        execute(() -> {
            int departmentId = readInt("Department ID: ");
            Optional<Department> department = departmentService.getDepartmentById(departmentId);

            if (department.isEmpty()) {
                System.out.println("Department not found.");
            } else {
                System.out.println(department.get());
            }
        });
    }

    // ---------------------------------------------------------------
    // Position Menu
    // ---------------------------------------------------------------

    private void positionMenu() {
        boolean inMenu = true;

        while (inMenu) {
            printPositionMenu();
            String input = scanner.nextLine();

            switch (input) {
                case "1":
                    addPosition();
                    break;
                case "2":
                    viewAllPositions();
                    break;
                case "3":
                    updatePosition();
                    break;
                case "4":
                    deletePosition();
                    break;
                case "5":
                    searchPosition();
                    break;
                case "0":
                    inMenu = false;
                    break;
                default:
                    System.out.println("Invalid option.");
                    break;
            }
        }
    }

    private void printPositionMenu() {
        System.out.println("=================================");
        System.out.println(" Position Management");
        System.out.println("=================================");
        System.out.println("1. Add Position");
        System.out.println("2. View All Positions");
        System.out.println("3. Update Position");
        System.out.println("4. Delete Position");
        System.out.println("5. Search Position By ID");
        System.out.println("0. Back");
        System.out.print("Choose an option: ");
    }

    private void addPosition() {
        execute(() -> {
            int positionId = readInt("Position ID: ");
            String name = readLine("Name: ");

            Position position = new Position(positionId, name);
            positionService.addPosition(position);
            System.out.println("Operation completed successfully.");
        });
    }

    private void viewAllPositions() {
        execute(() -> {
            List<Position> positions = positionService.getAllPositions();
            printAll(positions);
        });
    }

    private void updatePosition() {
        execute(() -> {
            int positionId = readInt("Position ID: ");
            Optional<Position> existingPosition = positionService.getPositionById(positionId);

            if (existingPosition.isEmpty()) {
                System.out.println("Record not found.");
                return;
            }

            String name = readLine("Name: ");

            Position position = new Position(positionId, name);
            positionService.updatePosition(position);
            System.out.println("Operation completed successfully.");
        });
    }

    private void deletePosition() {
        execute(() -> {
            int positionId = readInt("Position ID: ");
            Optional<Position> existingPosition = positionService.getPositionById(positionId);

            if (existingPosition.isEmpty()) {
                System.out.println("Record not found.");
                return;
            }

            positionService.deletePosition(positionId);
            System.out.println("Operation completed successfully.");
        });
    }

    private void searchPosition() {
        execute(() -> {
            int positionId = readInt("Position ID: ");
            Optional<Position> position = positionService.getPositionById(positionId);

            if (position.isEmpty()) {
                System.out.println("Position not found.");
            } else {
                System.out.println(position.get());
            }
        });
    }

    // ---------------------------------------------------------------
    // Attendance Menu
    // ---------------------------------------------------------------

    private void attendanceMenu() {
        boolean inMenu = true;

        while (inMenu) {
            printAttendanceMenu();
            String input = scanner.nextLine();

            switch (input) {
                case "1":
                    addAttendance();
                    break;
                case "2":
                    viewAllAttendance();
                    break;
                case "3":
                    viewAttendanceByEmployee();
                    break;
                case "4":
                    updateAttendance();
                    break;
                case "5":
                    deleteAttendance();
                    break;
                case "0":
                    inMenu = false;
                    break;
                default:
                    System.out.println("Invalid option.");
                    break;
            }
        }
    }

    private void printAttendanceMenu() {
        System.out.println("=================================");
        System.out.println(" Attendance Management");
        System.out.println("=================================");
        System.out.println("1. Add Attendance");
        System.out.println("2. View All Attendance");
        System.out.println("3. View Attendance By Employee");
        System.out.println("4. Update Attendance");
        System.out.println("5. Delete Attendance");
        System.out.println("0. Back");
        System.out.print("Choose an option: ");
    }

    private void addAttendance() {
        execute(() -> {
            int attendanceId = readInt("Attendance ID: ");
            int employeeId = readInt("Employee ID: ");
            String attendanceDate = readLine("Attendance Date: ");

            Attendance attendance = new Attendance(attendanceId, employeeId, attendanceDate);
            attendanceService.addAttendance(attendance);
            System.out.println("Operation completed successfully.");
        });
    }

    private void viewAllAttendance() {
        execute(() -> {
            List<Attendance> attendances = attendanceService.getAllAttendance();
            printAll(attendances);
        });
    }

    private void viewAttendanceByEmployee() {
        execute(() -> {
            int employeeId = readInt("Employee ID: ");
            List<Attendance> attendances = attendanceService.getAttendanceByEmployeeId(employeeId);
            printAll(attendances);
        });
    }

    private void updateAttendance() {
        execute(() -> {
            int attendanceId = readInt("Attendance ID: ");
            Optional<Attendance> existingAttendance = attendanceService.getAttendanceById(attendanceId);

            if (existingAttendance.isEmpty()) {
                System.out.println("Record not found.");
                return;
            }

            int employeeId = readInt("Employee ID: ");
            String attendanceDate = readLine("Attendance Date: ");

            Attendance attendance = new Attendance(attendanceId, employeeId, attendanceDate);
            attendanceService.updateAttendance(attendance);
            System.out.println("Operation completed successfully.");
        });
    }

    private void deleteAttendance() {
        execute(() -> {
            int attendanceId = readInt("Attendance ID: ");
            Optional<Attendance> existingAttendance = attendanceService.getAttendanceById(attendanceId);

            if (existingAttendance.isEmpty()) {
                System.out.println("Record not found.");
                return;
            }

            attendanceService.deleteAttendance(attendanceId);
            System.out.println("Operation completed successfully.");
        });
    }

    // ---------------------------------------------------------------
    // Reports Menu
    // ---------------------------------------------------------------

    private void reportsMenu() {
        boolean inMenu = true;

        while (inMenu) {
            printReportsMenu();
            String input = scanner.nextLine();

            switch (input) {
                case "1":
                    totalEmployeesReport();
                    break;
                case "2":
                    totalDepartmentsReport();
                    break;
                case "3":
                    totalPositionsReport();
                    break;
                case "4":
                    totalAttendanceRecordsReport();
                    break;
                case "0":
                    inMenu = false;
                    break;
                default:
                    System.out.println("Invalid option.");
                    break;
            }
        }
    }

    private void printReportsMenu() {
        System.out.println("=================================");
        System.out.println(" Reports");
        System.out.println("=================================");
        System.out.println("1. Total Employees");
        System.out.println("2. Total Departments");
        System.out.println("3. Total Positions");
        System.out.println("4. Total Attendance Records");
        System.out.println("0. Back");
        System.out.print("Choose an option: ");
    }

    private void totalEmployeesReport() {
        execute(() -> System.out.println("Total Employees: " + employeeService.getAllEmployees().size()));
    }

    private void totalDepartmentsReport() {
        execute(() -> System.out.println("Total Departments: " + departmentService.getAllDepartments().size()));
    }

    private void totalPositionsReport() {
        execute(() -> System.out.println("Total Positions: " + positionService.getAllPositions().size()));
    }

    private void totalAttendanceRecordsReport() {
        execute(() -> System.out.println("Total Attendance Records: " + attendanceService.getAllAttendance().size()));
    }

    // ---------------------------------------------------------------
    // Shared Helpers
    // ---------------------------------------------------------------

    @FunctionalInterface
    private interface SqlAction {
        void run() throws SQLException;
    }

    private void execute(SqlAction action) {
        try {
            action.run();
        } catch (SQLException e) {
            System.out.println("Database operation failed.");
        } catch (InputMismatchException e) {
            scanner.nextLine();
            System.out.println("Invalid input.");
        }
    }

    private void printAll(List<?> records) {
        if (records.isEmpty()) {
            System.out.println("No records found.");
        } else {
            for (Object record : records) {
                System.out.println(record);
            }
        }
    }

    private int readInt(String prompt) {
        System.out.print(prompt);
        int value = scanner.nextInt();
        scanner.nextLine();
        return value;
    }

    private double readDouble(String prompt) {
        System.out.print(prompt);
        double value = scanner.nextDouble();
        scanner.nextLine();
        return value;
    }

    private String readLine(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }
}
