package com.abdullahalmutairi.emspro.repository;

import com.abdullahalmutairi.emspro.model.Employee;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface EmployeeRepository {

    void save(Employee employee) throws SQLException;

    Optional<Employee> findById(int employeeId) throws SQLException;

    List<Employee> findAll() throws SQLException;

    void update(Employee employee) throws SQLException;

    void deleteById(int employeeId) throws SQLException;

    boolean existsByEmail(String email) throws SQLException;
}
