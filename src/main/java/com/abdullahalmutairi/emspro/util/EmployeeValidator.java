package com.abdullahalmutairi.emspro.util;

import com.abdullahalmutairi.emspro.model.Employee;

public final class EmployeeValidator {

    private EmployeeValidator() {
    }

    public static void validate(Employee employee) {
        if (employee == null) {
            throw new IllegalArgumentException("Employee must not be null.");
        }
        if (employee.getEmail() == null) {
            throw new IllegalArgumentException("Employee email must not be null.");
        }
        if (employee.getEmail().isBlank()) {
            throw new IllegalArgumentException("Employee email must not be blank.");
        }
        if (employee.getSalary() < 0) {
            throw new IllegalArgumentException("Employee salary must be greater than or equal to zero.");
        }
    }
}
