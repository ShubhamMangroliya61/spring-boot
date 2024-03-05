package com.ess.api.utils;

import com.ess.api.entities.Employee;
import com.ess.api.repositories.EmployeeRepository;
import com.ess.api.security.services.UserDetailsImpl;
import com.ess.api.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class GetCurrentEmployee {

    @Autowired
    private static EmployeeService employeeRepository;

    public static Employee getCurrentEmployee(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            UserDetailsImpl userDetails =  (UserDetailsImpl) authentication.getPrincipal();
            String useremail = userDetails.getEmail();
            Employee employee = employeeRepository.getEmployeeByEmail(useremail);
            return employee;
        } else {
            System.out.println("No user logged in.");
        }
        return null;
    }
}
