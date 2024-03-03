package com.ess.api.services;

import com.ess.api.entities.Employee;
import com.ess.api.exceptions.ResourceNotFoundException;
import com.ess.api.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    // Add Employee
    public Employee addEmployee(Employee employee){
        return employeeRepository.save(employee);
    }

    // Update by id
    /*
    public Employee updateEmployee(Long empId, Employee employee){
        Employee employeeWithId = this.getEmployee(empId);
        if(employee.getEmail() != null) employeeWithId.setEmail(employee.getEmail());
    }
    */

    // Get all
    public List<Employee> getAllEmployees(){
        return employeeRepository.findAll();
    }

    // Get one
    public Employee getEmployee(long empId){
        return employeeRepository.findById(empId).orElseThrow(() -> new ResourceNotFoundException("Employee", "EmpId", empId));
    }
}
