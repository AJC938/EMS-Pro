package com.abdullahalmutairi.emspro.service;

import com.abdullahalmutairi.emspro.exception.DuplicateEmailException;
import com.abdullahalmutairi.emspro.model.Employee;
import com.abdullahalmutairi.emspro.repository.EmployeeRepository;
import com.abdullahalmutairi.emspro.util.EmployeeValidator;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public void addEmployee(Employee employee) throws SQLException {
        EmployeeValidator.validate(employee);
        if (employeeRepository.existsByEmail(employee.getEmail())) {
            throw new DuplicateEmailException("Employee email already exists.");
        }
        employeeRepository.save(employee);
    }

    public Optional<Employee> getEmployeeById(int employeeId) throws SQLException {
        return employeeRepository.findById(employeeId);
    }

    public List<Employee> getAllEmployees() throws SQLException {
        return employeeRepository.findAll();
    }

    public void updateEmployee(Employee employee) throws SQLException {
        employeeRepository.update(employee);
    }

    public void deleteEmployee(int employeeId) throws SQLException {
        employeeRepository.deleteById(employeeId);
    }
}
