package com.abdullahalmutairi.emspro.repository.impl;

import com.abdullahalmutairi.emspro.database.ConnectionManager;
import com.abdullahalmutairi.emspro.model.Employee;
import com.abdullahalmutairi.emspro.repository.EmployeeRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EmployeeRepositoryImpl implements EmployeeRepository {

    @Override
    public void save(Employee employee) throws SQLException {
        String sql = "INSERT INTO Employees (EmployeeID, Email, Salary, DepartmentID, PositionID) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, employee.getEmployeeId());
            statement.setString(2, employee.getEmail());
            statement.setDouble(3, employee.getSalary());
            statement.setInt(4, employee.getDepartmentId());
            statement.setInt(5, employee.getPositionId());
            statement.executeUpdate();
        }
    }

    @Override
    public Optional<Employee> findById(int employeeId) throws SQLException {
        String sql = "SELECT EmployeeID, Email, Salary, DepartmentID, PositionID FROM Employees WHERE EmployeeID = ?";

        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, employeeId);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return Optional.of(mapRow(resultSet));
                }
                return Optional.empty();
            }
        }
    }

    @Override
    public List<Employee> findAll() throws SQLException {
        String sql = "SELECT EmployeeID, Email, Salary, DepartmentID, PositionID FROM Employees";
        List<Employee> employees = new ArrayList<>();

        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                employees.add(mapRow(resultSet));
            }
        }

        return employees;
    }

    @Override
    public void update(Employee employee) throws SQLException {
        String sql = "UPDATE Employees SET Email = ?, Salary = ?, DepartmentID = ?, PositionID = ? WHERE EmployeeID = ?";

        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, employee.getEmail());
            statement.setDouble(2, employee.getSalary());
            statement.setInt(3, employee.getDepartmentId());
            statement.setInt(4, employee.getPositionId());
            statement.setInt(5, employee.getEmployeeId());
            statement.executeUpdate();
        }
    }

    @Override
    public void deleteById(int employeeId) throws SQLException {
        String sql = "DELETE FROM Employees WHERE EmployeeID = ?";

        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, employeeId);
            statement.executeUpdate();
        }
    }

    @Override
    public boolean existsByEmail(String email) throws SQLException {
        String sql = "SELECT EmployeeID FROM Employees WHERE Email = ?";

        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, email);

            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next();
            }
        }
    }

    private Employee mapRow(ResultSet resultSet) throws SQLException {
        return new Employee(
                resultSet.getInt("EmployeeID"),
                resultSet.getString("Email"),
                resultSet.getDouble("Salary"),
                resultSet.getInt("DepartmentID"),
                resultSet.getInt("PositionID")
        );
    }
}
