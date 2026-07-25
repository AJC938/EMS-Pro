package com.abdullahalmutairi.emspro.service;

import com.abdullahalmutairi.emspro.model.Department;
import com.abdullahalmutairi.emspro.repository.DepartmentRepository;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    public void addDepartment(Department department) throws SQLException {
        departmentRepository.save(department);
    }

    public Optional<Department> getDepartmentById(int departmentId) throws SQLException {
        return departmentRepository.findById(departmentId);
    }

    public List<Department> getAllDepartments() throws SQLException {
        return departmentRepository.findAll();
    }

    public void updateDepartment(Department department) throws SQLException {
        departmentRepository.update(department);
    }

    public void deleteDepartment(int departmentId) throws SQLException {
        departmentRepository.deleteById(departmentId);
    }
}
