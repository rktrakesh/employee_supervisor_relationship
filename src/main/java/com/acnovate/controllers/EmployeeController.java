package com.acnovate.controllers;

import com.acnovate.entities.Employee;
import com.acnovate.exceptions.ResourceNotFoundException;
import com.acnovate.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/employee/api")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<?> addNewEmployeeDetails(@RequestBody Map<String, String> employeesMap) {
        List<Employee> employees = employeeService.addNewEmployeeDetails(employeesMap);

        // Customize the response message
        String message = "Employees added successfully!";

        HttpHeaders headers = new HttpHeaders();
        headers.add("Custom-Message", message);

        return ResponseEntity.ok()
                .headers(headers)
                .body(employees);
    }

    @GetMapping("/{employeeName}/supervisor")
    public ResponseEntity<?> getSupervisor(@PathVariable String employeeName) {
        try {
            Employee supervisor = employeeService.getSupervisor(employeeName);
            return ResponseEntity.ok(supervisor);
        } catch (ResourceNotFoundException e) {
            String errorMessage = "Supervisor not found for employee: " + employeeName;
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
        }
    }


    @GetMapping("/getAllEmp")
    public ResponseEntity<List<Employee>> getAllEmployeeDetails() {
        List<Employee> employees = employeeService.getAllEmployeeDetails();
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }
}
