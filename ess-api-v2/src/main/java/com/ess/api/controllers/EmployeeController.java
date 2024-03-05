package com.ess.api.controllers;

import com.ess.api.response.ApiResponse;
import com.ess.api.entities.Employee;
import com.ess.api.security.services.UserDetailsImpl;
import com.ess.api.security.services.UserDetailsServiceImpl;
import com.ess.api.services.EmployeeService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/employee")
@CrossOrigin(origins = "http://localhost:5173")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    // Add
    @PostMapping
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee){
        Employee newEmployee = employeeService.addEmployee(employee);
        return ResponseEntity.ok(newEmployee);
    }

    // Get all
    @GetMapping("/all")
    public ResponseEntity<List<Employee>> getAllEmployees(HttpSession session){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            UserDetailsImpl userDetails =  (UserDetailsImpl) authentication.getPrincipal();
            String useremail = userDetails.getEmail();
        } else {
            System.out.println("No user logged in.");
        }
        List<Employee> allEmployees = employeeService.getAllEmployees();
        return ResponseEntity.ok(allEmployees);
    }

    // Get by id
    @GetMapping("/{empId}")
    public ResponseEntity<Employee> getEmployee(@PathVariable Long empId, @RequestBody Employee employee){
        Employee employee1 = employeeService.getEmployee(empId);
        return ResponseEntity.ok(employee1);
    }

    // Update by id
    @PutMapping("/{empId}")
    public ResponseEntity<Employee> updateEmployeeById(@PathVariable Long empId, @RequestBody Employee employee, HttpSession session){
        System.out.println(session.getAttribute("LoggedInUser") + ": ");
        Employee updatedEmployee = employeeService.updateEmployee(empId, employee);
        return ResponseEntity.ok(updatedEmployee);
    }

    // Login
    @PostMapping("/login")
    public ResponseEntity<?> loginEmployee(@RequestBody Employee employee, HttpSession session){
        Employee employeeByEmail = employeeService.getEmployeeByEmail(employee.getEmail());
        if(employeeByEmail == null || !Objects.equals(employeeByEmail.getPassword(), employee.getPassword())){
            ApiResponse response = new ApiResponse("Wrong email or password", false);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        employeeByEmail.setPassword("XXXXXXX");
        session.setAttribute("LoggedInUser", employeeByEmail);
        return ResponseEntity.ok(employeeByEmail);
    }
}
