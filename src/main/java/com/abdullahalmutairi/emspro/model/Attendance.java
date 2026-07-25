package com.abdullahalmutairi.emspro.model;

import java.util.Objects;

public class Attendance {

    private int attendanceId;
    private int employeeId;
    private String attendanceDate;

    public Attendance() {
    }

    public Attendance(int attendanceId, int employeeId, String attendanceDate) {
        this.attendanceId = attendanceId;
        this.employeeId = employeeId;
        this.attendanceDate = attendanceDate;
    }

    public int getAttendanceId() {
        return attendanceId;
    }

    public void setAttendanceId(int attendanceId) {
        this.attendanceId = attendanceId;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getAttendanceDate() {
        return attendanceDate;
    }

    public void setAttendanceDate(String attendanceDate) {
        this.attendanceDate = attendanceDate;
    }

    @Override
    public String toString() {
        return "Attendance{" +
                "attendanceId=" + attendanceId +
                ", employeeId=" + employeeId +
                ", attendanceDate='" + attendanceDate + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Attendance attendance = (Attendance) o;
        return attendanceId == attendance.attendanceId &&
                employeeId == attendance.employeeId &&
                Objects.equals(attendanceDate, attendance.attendanceDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(attendanceId, employeeId, attendanceDate);
    }
}
