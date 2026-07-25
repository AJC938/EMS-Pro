package com.abdullahalmutairi.emspro;

import com.abdullahalmutairi.emspro.controller.MainController;
import com.abdullahalmutairi.emspro.repository.AttendanceRepository;
import com.abdullahalmutairi.emspro.repository.DepartmentRepository;
import com.abdullahalmutairi.emspro.repository.EmployeeRepository;
import com.abdullahalmutairi.emspro.repository.PositionRepository;
import com.abdullahalmutairi.emspro.repository.impl.AttendanceRepositoryImpl;
import com.abdullahalmutairi.emspro.repository.impl.DepartmentRepositoryImpl;
import com.abdullahalmutairi.emspro.repository.impl.EmployeeRepositoryImpl;
import com.abdullahalmutairi.emspro.repository.impl.PositionRepositoryImpl;
import com.abdullahalmutairi.emspro.service.AttendanceService;
import com.abdullahalmutairi.emspro.service.DepartmentService;
import com.abdullahalmutairi.emspro.service.EmployeeService;
import com.abdullahalmutairi.emspro.service.PositionService;

public class Main {

    public static void main(String[] args) {
        EmployeeRepository employeeRepository = new EmployeeRepositoryImpl();
        DepartmentRepository departmentRepository = new DepartmentRepositoryImpl();
        PositionRepository positionRepository = new PositionRepositoryImpl();
        AttendanceRepository attendanceRepository = new AttendanceRepositoryImpl();

        EmployeeService employeeService = new EmployeeService(employeeRepository);
        DepartmentService departmentService = new DepartmentService(departmentRepository);
        PositionService positionService = new PositionService(positionRepository);
        AttendanceService attendanceService = new AttendanceService(attendanceRepository);

        MainController mainController = new MainController(
                employeeService, departmentService, positionService, attendanceService);
        mainController.start();
    }
}
