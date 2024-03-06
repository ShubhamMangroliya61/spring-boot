package com.ess.api.controllers;

import com.ess.api.entities.Employee;
import com.ess.api.entities.PunchIn;
import com.ess.api.security.services.UserDetailsImpl;
import com.ess.api.services.EmployeeService;
import com.ess.api.services.PunchInService;
import com.ess.api.utils.GetCurrentEmployee;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/punchIn")
@CrossOrigin(origins = "http://localhost:5173")
public class PunchInController {

    @Autowired
    private PunchInService punchInService;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private GetCurrentEmployee getCurrentEmployee;

    @PostMapping
    public ResponseEntity<?> doPunchIn(Authentication authentication){
        Employee currenEmployee = getCurrentEmployee.getCurrentEmployee(authentication);

        PunchIn punchIn = new PunchIn(currenEmployee);
        PunchIn punchedIn = punchInService.doPunch(punchIn);
        return ResponseEntity.ok(punchedIn);
    }

}
