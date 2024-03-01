package com.ess.api.controllers;

import com.ess.api.entities.Employee;
import com.ess.api.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee){
        Employee newEmployee = employeeService.addEmployee(employee);
        return ResponseEntity.ok(newEmployee);
    }

    @GetMapping
    public ResponseEntity<Employee> getEmployee(@RequestBody Employee employee){
        long employeeId = employee.getId();
        Employee employee1 = employeeService.getEmployee(employeeId);
        return ResponseEntity.ok(employee1);
    }
}
