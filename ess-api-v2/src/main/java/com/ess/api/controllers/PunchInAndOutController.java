package com.ess.api.controllers;

import com.ess.api.entities.Employee;
import com.ess.api.entities.PunchIn;
import com.ess.api.entities.PunchOut;
import com.ess.api.request.GetPunchInAndOutRequest;
import com.ess.api.response.Punch;
import com.ess.api.services.PunchInAndOutService;
import com.ess.api.services.PunchInService;
import com.ess.api.services.PunchOutService;
import com.ess.api.utils.GetCurrentEmployee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.List;

@RestController
@RequestMapping("/api/punches")
public class PunchInAndOutController {

    @Autowired
    private PunchInService punchInService;

    @Autowired
    private PunchOutService punchOutService;

    @Autowired
    private GetCurrentEmployee getCurrentEmployee;

    @Autowired
    private PunchInAndOutService punchInAndOutService;

    @PostMapping
    public ResponseEntity<List<Punch>> getAllPunchesOfCurrentUserByDate(Authentication authentication, @RequestBody GetPunchInAndOutRequest getPunchInAndOutRequest ){
        System.out.println(getPunchInAndOutRequest);
        int day = Integer.parseInt(getPunchInAndOutRequest.getDay());
        int month = Integer.parseInt(getPunchInAndOutRequest.getMonth());
        int year = Integer.parseInt(getPunchInAndOutRequest.getYear());

        LocalDate date = LocalDate.of(year, month, day);

        Employee currentEmployee = getCurrentEmployee.getCurrentEmployee(authentication);
        List<Punch> allPunchInAndOut = punchInAndOutService.getAllPunchesByDateAndEmployee(currentEmployee, date);
        return ResponseEntity.ok(allPunchInAndOut);
    }
}
