package com.acnovate.services;

import com.acnovate.entities.Employee;
import com.acnovate.exceptions.ResourceNotFoundException;

import java.util.List;
import java.util.Map;

public interface EmployeeService {

    List<Employee> addNewEmployeeDetails(Map<String, String> employeesMap);

    Employee getSupervisor(String employeeName) throws ResourceNotFoundException;

    List<Employee> getAllEmployeeDetails();
}
