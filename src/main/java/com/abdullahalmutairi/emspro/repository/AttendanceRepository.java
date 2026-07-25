package com.abdullahalmutairi.emspro.repository;

import com.abdullahalmutairi.emspro.model.Attendance;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface AttendanceRepository {

    void save(Attendance attendance) throws SQLException;

    Optional<Attendance> findById(int attendanceId) throws SQLException;

    List<Attendance> findAll() throws SQLException;

    void update(Attendance attendance) throws SQLException;

    void deleteById(int attendanceId) throws SQLException;

    List<Attendance> findByEmployeeId(int employeeId) throws SQLException;
}
