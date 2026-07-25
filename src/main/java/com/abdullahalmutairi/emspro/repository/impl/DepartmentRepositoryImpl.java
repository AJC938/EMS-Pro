package com.abdullahalmutairi.emspro.repository.impl;

import com.abdullahalmutairi.emspro.database.ConnectionManager;
import com.abdullahalmutairi.emspro.model.Department;
import com.abdullahalmutairi.emspro.repository.DepartmentRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DepartmentRepositoryImpl implements DepartmentRepository {

    @Override
    public void save(Department department) throws SQLException {
        String sql = "INSERT INTO Departments (DepartmentID, Name) VALUES (?, ?)";

        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, department.getDepartmentId());
            statement.setString(2, department.getName());
            statement.executeUpdate();
        }
    }

    @Override
    public Optional<Department> findById(int departmentId) throws SQLException {
        String sql = "SELECT DepartmentID, Name FROM Departments WHERE DepartmentID = ?";

        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, departmentId);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return Optional.of(mapRow(resultSet));
                }
                return Optional.empty();
            }
        }
    }

    @Override
    public List<Department> findAll() throws SQLException {
        String sql = "SELECT DepartmentID, Name FROM Departments";
        List<Department> departments = new ArrayList<>();

        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                departments.add(mapRow(resultSet));
            }
        }

        return departments;
    }

    @Override
    public void update(Department department) throws SQLException {
        String sql = "UPDATE Departments SET Name = ? WHERE DepartmentID = ?";

        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, department.getName());
            statement.setInt(2, department.getDepartmentId());
            statement.executeUpdate();
        }
    }

    @Override
    public void deleteById(int departmentId) throws SQLException {
        String sql = "DELETE FROM Departments WHERE DepartmentID = ?";

        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, departmentId);
            statement.executeUpdate();
        }
    }

    private Department mapRow(ResultSet resultSet) throws SQLException {
        return new Department(
                resultSet.getInt("DepartmentID"),
                resultSet.getString("Name")
        );
    }
}
