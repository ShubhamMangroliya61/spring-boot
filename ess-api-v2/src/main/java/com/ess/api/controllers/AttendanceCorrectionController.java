package com.ess.api.controllers;

import com.ess.api.entities.AttendanceCorrection;
import com.ess.api.entities.Employee;
import com.ess.api.entities.Leave;
import com.ess.api.request.AttendanceCorrectionRequest;
import com.ess.api.response.ApiResponse;
import com.ess.api.services.AttendanceCorrectionService;
import com.ess.api.utils.GetCurrentEmployee;
import jdk.dynalink.linker.LinkerServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/correction")
@CrossOrigin(origins = "http://localhost:5173")
public class AttendanceCorrectionController {

    @Autowired
    private AttendanceCorrectionService attendanceCorrectionService;

    @Autowired
    private GetCurrentEmployee getCurrentEmployee;

    // Add request
    @PostMapping
    public ResponseEntity<?> addRequest(Authentication authentication,@RequestBody AttendanceCorrectionRequest attendanceCorrectionRequest){
        System.out.println(attendanceCorrectionRequest);
        LocalDate date = LocalDate.of(attendanceCorrectionRequest.getYear(), attendanceCorrectionRequest.getMonth(), attendanceCorrectionRequest.getDay());
        Employee currentEmployee =  getCurrentEmployee.getCurrentEmployee(authentication);
        AttendanceCorrection attendanceCorrection = new AttendanceCorrection(date, attendanceCorrectionRequest.getRemark(), Leave.LeaveStatus.PENDING, currentEmployee);
        AttendanceCorrection attenAttendanceCorrection = attendanceCorrectionService.addRequest(currentEmployee, attendanceCorrection);
        return ResponseEntity.ok(attenAttendanceCorrection);
    }

    // Get all
    public ResponseEntity<?> getAll(Authentication authentication){
        Employee currentEmployee = getCurrentEmployee.getCurrentEmployee(authentication);
        if(!currentEmployee.getRole().getName().equalsIgnoreCase("admin") &&  !currentEmployee.getRole().getName().equalsIgnoreCase("manager")){
            ApiResponse response = new ApiResponse("You are not authorized", false);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        List<AttendanceCorrection> list = attendanceCorrectionService.getAll();
        return ResponseEntity.ok(list);
    }
}
