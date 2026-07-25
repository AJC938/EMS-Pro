package com.abdullahalmutairi.emspro.service;

import com.abdullahalmutairi.emspro.model.Attendance;
import com.abdullahalmutairi.emspro.repository.AttendanceRepository;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class AttendanceService {

    private final AttendanceRepository attendanceRepository;

    public AttendanceService(AttendanceRepository attendanceRepository) {
        this.attendanceRepository = attendanceRepository;
    }

    public void addAttendance(Attendance attendance) throws SQLException {
        attendanceRepository.save(attendance);
    }

    public Optional<Attendance> getAttendanceById(int attendanceId) throws SQLException {
        return attendanceRepository.findById(attendanceId);
    }

    public List<Attendance> getAllAttendance() throws SQLException {
        return attendanceRepository.findAll();
    }

    public List<Attendance> getAttendanceByEmployeeId(int employeeId) throws SQLException {
        return attendanceRepository.findByEmployeeId(employeeId);
    }

    public void updateAttendance(Attendance attendance) throws SQLException {
        attendanceRepository.update(attendance);
    }

    public void deleteAttendance(int attendanceId) throws SQLException {
        attendanceRepository.deleteById(attendanceId);
    }
}
