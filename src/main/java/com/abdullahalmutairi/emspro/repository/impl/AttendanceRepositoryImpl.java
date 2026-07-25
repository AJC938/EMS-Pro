package com.abdullahalmutairi.emspro.repository.impl;

import com.abdullahalmutairi.emspro.database.ConnectionManager;
import com.abdullahalmutairi.emspro.model.Attendance;
import com.abdullahalmutairi.emspro.repository.AttendanceRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AttendanceRepositoryImpl implements AttendanceRepository {

    @Override
    public void save(Attendance attendance) throws SQLException {
        String sql = "INSERT INTO Attendance (AttendanceID, EmployeeID, AttendanceDate) VALUES (?, ?, ?)";

        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, attendance.getAttendanceId());
            statement.setInt(2, attendance.getEmployeeId());
            statement.setString(3, attendance.getAttendanceDate());
            statement.executeUpdate();
        }
    }

    @Override
    public Optional<Attendance> findById(int attendanceId) throws SQLException {
        String sql = "SELECT AttendanceID, EmployeeID, AttendanceDate FROM Attendance WHERE AttendanceID = ?";

        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, attendanceId);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return Optional.of(mapRow(resultSet));
                }
                return Optional.empty();
            }
        }
    }

    @Override
    public List<Attendance> findAll() throws SQLException {
        String sql = "SELECT AttendanceID, EmployeeID, AttendanceDate FROM Attendance";
        List<Attendance> attendances = new ArrayList<>();

        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                attendances.add(mapRow(resultSet));
            }
        }

        return attendances;
    }

    @Override
    public void update(Attendance attendance) throws SQLException {
        String sql = "UPDATE Attendance SET EmployeeID = ?, AttendanceDate = ? WHERE AttendanceID = ?";

        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, attendance.getEmployeeId());
            statement.setString(2, attendance.getAttendanceDate());
            statement.setInt(3, attendance.getAttendanceId());
            statement.executeUpdate();
        }
    }

    @Override
    public void deleteById(int attendanceId) throws SQLException {
        String sql = "DELETE FROM Attendance WHERE AttendanceID = ?";

        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, attendanceId);
            statement.executeUpdate();
        }
    }

    @Override
    public List<Attendance> findByEmployeeId(int employeeId) throws SQLException {
        String sql = "SELECT AttendanceID, EmployeeID, AttendanceDate FROM Attendance WHERE EmployeeID = ?";
        List<Attendance> attendances = new ArrayList<>();

        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, employeeId);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    attendances.add(mapRow(resultSet));
                }
            }
        }

        return attendances;
    }

    private Attendance mapRow(ResultSet resultSet) throws SQLException {
        return new Attendance(
                resultSet.getInt("AttendanceID"),
                resultSet.getInt("EmployeeID"),
                resultSet.getString("AttendanceDate")
        );
    }
}
