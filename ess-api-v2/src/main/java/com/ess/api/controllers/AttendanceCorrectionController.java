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
        LocalDate date = LocalDate.of(attendanceCorrectionRequest.getYear(), attendanceCorrectionRequest.getMonth(), attendanceCorrectionRequest.getDay());
        Employee currentEmployee =  getCurrentEmployee.getCurrentEmployee(authentication);
        AttendanceCorrection attendanceCorrection = new AttendanceCorrection(date, attendanceCorrectionRequest.getRemark(), Leave.LeaveStatus.PENDING, currentEmployee);

        AttendanceCorrection existingAttendaceCorrection = attendanceCorrectionService.getByEmployeeAndDate(currentEmployee.getId(), attendanceCorrection.getDate());
        if(existingAttendaceCorrection != null){
            ApiResponse response = new ApiResponse("You already had request for "+attendanceCorrection.getDate(),false);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        AttendanceCorrection addedAttendanceCorrection = attendanceCorrectionService.addRequest(currentEmployee, attendanceCorrection);
        return ResponseEntity.ok(addedAttendanceCorrection);
    }

    // Get all
    @GetMapping("/getAll")
    public ResponseEntity<?> getAll(Authentication authentication){
        Employee currentEmployee = getCurrentEmployee.getCurrentEmployee(authentication);
        if(!currentEmployee.getRole().getName().equalsIgnoreCase("admin") &&  !currentEmployee.getRole().getName().equalsIgnoreCase("manager")){
            ApiResponse response = new ApiResponse("You are not authorized", false);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        List<AttendanceCorrection> list = attendanceCorrectionService.getAll();
        return ResponseEntity.ok(list);
    }

    // Update status
    @PutMapping("/updateStatus/{correctionId}")
    public ResponseEntity<?> updateStatus(@PathVariable long correctionId, @RequestBody AttendanceCorrection attendanceCorrection){
        AttendanceCorrection updatedAttendanceCorrection = attendanceCorrectionService.updateStatus(attendanceCorrection.getStatus(), correctionId);
        return ResponseEntity.ok(updatedAttendanceCorrection);
    }

}
