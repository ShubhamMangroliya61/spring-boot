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
    public Employee updateEmployee(Long empId, Employee employee){
        Employee employeeWithId = this.getEmployee(empId);
        if(employee.getFirstName() != null) employeeWithId.setFirstName(employee.getFirstName());
        if(employee.getLastName() != null) employeeWithId.setLastName(employee.getLastName());
        if(employee.getEmail() != null) employeeWithId.setEmail(employee.getEmail());
        if(employee.getPassword() != null) employeeWithId.setPassword(employee.getPassword());
        if(employee.getRole() != null) employeeWithId.setRole(employee.getRole());
        if(employee.getTeam() != null) employeeWithId.setTeam(employee.getTeam());
        if(employee.getLeaves() != null) employeeWithId.setLeaves(employee.getLeaves());

        return employeeRepository.save(employeeWithId);
    }


    // Get all
    public List<Employee> getAllEmployees(){
        return employeeRepository.findAll();
    }

    // Get by id
    public Employee getEmployee(long empId){
        return employeeRepository.findById(empId).orElseThrow(() -> new ResourceNotFoundException("Employee", "EmpId", "" + empId));
    }

    // Get by email
    public Employee getEmployeeByEmail(String empEmail){
        try{
            return employeeRepository.findByEmail(empEmail).orElseThrow(() -> new ResourceNotFoundException("Employee", "Email", empEmail));
        }catch (Exception e){
            throw new ResourceNotFoundException("Employee", "Email", empEmail);
        }
    }
}
