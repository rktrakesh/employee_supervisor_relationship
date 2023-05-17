package com.acnovate.services.impl;

import com.acnovate.entities.Employee;
import com.acnovate.exceptions.ResourceNotFoundException;
import com.acnovate.repositories.EmployeeRepo;
import com.acnovate.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepo employeeRepo;

    @Override
    public List<Employee> addNewEmployeeDetails(Map<String, String> employeesMap) {
        List<Employee> employees = new ArrayList<>();

        for (Map.Entry<String, String> entry : employeesMap.entrySet()) {
            String employeeName = entry.getKey();
            String supervisorName = entry.getValue();

            Employee employee = employeeRepo.findByName(employeeName);
            Employee supervisor = employeeRepo.findByName(supervisorName);

            if (employee == null) {
                employee = new Employee();
                employee.setName(employeeName);
            }

            if (supervisor == null) {
                supervisor = new Employee();
                supervisor.setName(supervisorName);
                employeeRepo.save(supervisor);
            }

            employee.setSupervisor(supervisor);
            employees.add(employee);
        }
        return employeeRepo.saveAll(employees);
    }

    public Employee getSupervisor(String supervisorName) throws ResourceNotFoundException {
        Employee supervisor = employeeRepo.findByName(supervisorName);
        if (supervisor == null) {
            throw new ResourceNotFoundException("Supervisor details","supervisor name",supervisorName);
        }
        return supervisor;
    }

    @Override
    public List<Employee> getAllEmployeeDetails() {
        return employeeRepo.findAll();
    }

}
