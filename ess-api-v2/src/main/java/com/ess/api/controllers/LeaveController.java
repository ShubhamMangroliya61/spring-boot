package com.ess.api.controllers;

import com.ess.api.entities.Employee;
import com.ess.api.entities.Leave;
import com.ess.api.response.ApiResponse;
import com.ess.api.services.LeaveService;
import com.ess.api.utils.GetCurrentEmployee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@RestController
@RequestMapping("/api/leave")
public class LeaveController {

    @Autowired
    private LeaveService leaveService;

    @Autowired
    private GetCurrentEmployee getCurrentEmployee;
    // Add request
    @PostMapping
    public ResponseEntity<Leave> addLeaveRequest(Authentication authentication, @RequestBody Leave leave){
        Employee currentEmployee = getCurrentEmployee.getCurrentEmployee(authentication);
        leave.setEmployee(currentEmployee);
        long diffInDays = ChronoUnit.DAYS.between(leave.getFrom(), leave.getTo());
        leave.setDays(diffInDays);
        Leave addedLeaveRequest = leaveService.addLeaveRequest(leave);
        return ResponseEntity.ok(addedLeaveRequest);
    }

    // Get all
    @GetMapping("/getAll")
    public ResponseEntity<?> getAllLeaves(Authentication authentication){
        Employee currentEmployee = getCurrentEmployee.getCurrentEmployee(authentication);
        if(!currentEmployee.getRole().toString().equalsIgnoreCase("ADMIN")){
            ApiResponse message = new ApiResponse("You are not allowed", false);
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }
        List<Leave> listOfLeaves = leaveService.getAllLeaves();
        return  ResponseEntity.ok(listOfLeaves);
    }

    // Get by Employee
    @GetMapping
    public ResponseEntity<List<Leave>> getAllLeaveRequestsByEmployee(Authentication authentication){
        Employee currentEmployee = getCurrentEmployee.getCurrentEmployee(authentication);
        List<Leave> listOfRequests = leaveService.getAllLeaveRequestsByEmployee(currentEmployee);
        return ResponseEntity.ok(listOfRequests);
    }
}
