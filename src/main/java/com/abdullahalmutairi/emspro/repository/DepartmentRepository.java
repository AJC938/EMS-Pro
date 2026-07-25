package com.abdullahalmutairi.emspro.repository;

import com.abdullahalmutairi.emspro.model.Department;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface DepartmentRepository {

    void save(Department department) throws SQLException;

    Optional<Department> findById(int departmentId) throws SQLException;

    List<Department> findAll() throws SQLException;

    void update(Department department) throws SQLException;

    void deleteById(int departmentId) throws SQLException;
}
