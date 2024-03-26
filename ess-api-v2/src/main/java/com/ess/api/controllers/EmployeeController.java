package com.ess.api.controllers;

import com.ess.api.entities.Leave;
import com.ess.api.entities.Role;
import com.ess.api.entities.Team;
import com.ess.api.request.AddEmployeeRequest;
import com.ess.api.response.ApiResponse;
import com.ess.api.entities.Employee;
import com.ess.api.security.services.UserDetailsImpl;
import com.ess.api.security.services.UserDetailsServiceImpl;
import com.ess.api.services.EmployeeService;
import com.ess.api.services.RoleService;
import com.ess.api.services.TeamService;
import com.ess.api.utils.GetCurrentEmployee;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/api/employee")
@CrossOrigin(origins = "http://localhost:5173")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleService roleService;

    @Autowired
    private TeamService teamService;

    @Autowired
    private GetCurrentEmployee getCurrentEmployee;

    // Add
    @PostMapping
    public ResponseEntity<?> addEmployee(@RequestBody AddEmployeeRequest newEmployee) {
        newEmployee.setPassword(passwordEncoder.encode(newEmployee.getPassword()));
        Role role = roleService.getRoleById(newEmployee.getRoleId());
        Team team = teamService.GetTeamById(newEmployee.getTeamId());
        Employee employeeToAdd = new Employee(newEmployee.getFirstName(), newEmployee.getLastName(),
                newEmployee.getEmail(), newEmployee.getPassword(), role, team);

        Employee newAddedEmployee = employeeService.addEmployee(employeeToAdd);

        return ResponseEntity.ok(newAddedEmployee);
    }

    // Get all
    @GetMapping("/all")
    public ResponseEntity<?> getAllEmployees(HttpSession session, Authentication authentication) {
        /*
         * Authentication authentication =
         * SecurityContextHolder.getContext().getAuthentication();
         * if (authentication != null && authentication.getPrincipal() instanceof
         * UserDetails) {
         * UserDetailsImpl userDetails = (UserDetailsImpl)
         * authentication.getPrincipal();
         * String useremail = userDetails.getEmail();
         * } else {
         * System.out.println("No user logged in.");
         * }
         */
        Employee currentEmployee = getCurrentEmployee.getCurrentEmployee(authentication);
        /*
         * 
         * if (!currentEmployee.getRole().getName().equalsIgnoreCase("admin")) {
         * ApiResponse response = new ApiResponse("You are not authorized", false);
         * return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
         * }
         */
        List<Employee> allEmployees = employeeService.getAllEmployees();
        return ResponseEntity.ok(allEmployees);
    }

    // Get by id
    @GetMapping("/{empId}")
    public ResponseEntity<Employee> getEmployee(@PathVariable Long empId) {
        Employee employee = employeeService.getEmployee(empId);
        return ResponseEntity.ok(employee);
    }

    // Get currentEmployee Data
    @GetMapping("/getCurrent")
    public ResponseEntity<Employee> getCurrentEmployee(Authentication authentication) {
        Employee currentEmployee = getCurrentEmployee.getCurrentEmployee(authentication);
        currentEmployee.setPassword("XXXXXXXX");
        return ResponseEntity.ok(currentEmployee);
    }

    // Update by id
    @PutMapping("/{empId}")
    public ResponseEntity<Employee> updateEmployeeById(@PathVariable Long empId, @RequestBody Employee employee,
            HttpSession session) {
        System.out.println(session.getAttribute("LoggedInUser") + ": ");
        Employee updatedEmployee = employeeService.updateEmployee(empId, employee);
        return ResponseEntity.ok(updatedEmployee);
    }

    // Login
    @PostMapping("/login")
    public ResponseEntity<?> loginEmployee(@RequestBody Employee employee, HttpSession session) {
        Employee employeeByEmail = employeeService.getEmployeeByEmail(employee.getEmail());
        if (employeeByEmail == null || !Objects.equals(employeeByEmail.getPassword(), employee.getPassword())) {
            ApiResponse response = new ApiResponse("Wrong email or password", false);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        employeeByEmail.setPassword("XXXXXXX");
        session.setAttribute("LoggedInUser", employeeByEmail);
        return ResponseEntity.ok(employeeByEmail);
    }

    // get all employees from given team
    @GetMapping("/{teamId}/all")
    public ResponseEntity<?> getAllTheEmployeesInGivenTeam(@PathVariable long teamId, Authentication authentication){
        Employee currentEmployee = getCurrentEmployee.getCurrentEmployee(authentication);
        if(!currentEmployee.getRole().getName().equalsIgnoreCase("admin")){
            ApiResponse response = new ApiResponse("You are not authorized", false);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        List<Employee> employeesWithGivenTeam = employeeService.getEmployeesFromGivenTeam(teamId);
        return ResponseEntity.ok(employeesWithGivenTeam);
    }
}
