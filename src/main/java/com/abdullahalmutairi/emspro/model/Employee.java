package com.abdullahalmutairi.emspro.model;

import java.util.Objects;

public class Employee {

    private int employeeId;
    private String email;
    private double salary;
    private int departmentId;
    private int positionId;

    public Employee() {
    }

    public Employee(int employeeId, String email, double salary, int departmentId, int positionId) {
        this.employeeId = employeeId;
        this.email = email;
        this.salary = salary;
        this.departmentId = departmentId;
        this.positionId = positionId;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public int getPositionId() {
        return positionId;
    }

    public void setPositionId(int positionId) {
        this.positionId = positionId;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employeeId=" + employeeId +
                ", email='" + email + '\'' +
                ", salary=" + salary +
                ", departmentId=" + departmentId +
                ", positionId=" + positionId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Employee employee = (Employee) o;
        return employeeId == employee.employeeId &&
                Double.compare(employee.salary, salary) == 0 &&
                departmentId == employee.departmentId &&
                positionId == employee.positionId &&
                Objects.equals(email, employee.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(employeeId, email, salary, departmentId, positionId);
    }
}
