package com.ess.api.controllers;

import com.ess.api.entities.Employee;
import com.ess.api.entities.PunchOut;
import com.ess.api.services.EmployeeService;
import com.ess.api.services.PunchOutService;
import com.ess.api.utils.GetCurrentEmployee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/punchOut")
@CrossOrigin(origins = "http://localhost:5173")
public class PunchOutController {

    @Autowired
    private PunchOutService punchOutService;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private GetCurrentEmployee getCurrentEmployee;

    @PostMapping
    public ResponseEntity<?> doPunchOut(Authentication authentication){
        Employee currentEmployee = getCurrentEmployee.getCurrentEmployee(authentication);

        PunchOut punchOut = new PunchOut(currentEmployee);
        PunchOut punchedOut = punchOutService.doPunch(punchOut);

        return ResponseEntity.ok(punchedOut);
    }
}
