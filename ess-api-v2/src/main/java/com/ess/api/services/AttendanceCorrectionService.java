package com.ess.api.services;

import com.ess.api.entities.*;
import com.ess.api.exceptions.ResourceNotFoundException;
import com.ess.api.repositories.AttendaceCorrectionRepository;
import com.ess.api.repositories.PunchInRepository;
import com.ess.api.repositories.PunchOutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
public class AttendanceCorrectionService {

    @Autowired
    private AttendaceCorrectionRepository attendaceCorrectionRepository;

    @Autowired
    private PunchInAndOutService punchInAndOutService;

    @Autowired
    private PunchInRepository punchInRepository;

    @Autowired
    private PunchOutRepository punchOutRepository;

    // Add request
    public AttendanceCorrection addRequest(Employee employee, AttendanceCorrection attendanceCorrection){
        attendanceCorrection.setEmployee(employee);
        return attendaceCorrectionRepository.save(attendanceCorrection);
    }

    // Get by id
    public AttendanceCorrection getById(Long correctionId){
        return attendaceCorrectionRepository.findById(correctionId).orElseThrow(() -> new ResourceNotFoundException("AttendaceCorrection", "id", correctionId.toString()));
    }

    // Get all
    public List<AttendanceCorrection> getAll(){
        return attendaceCorrectionRepository.findAll();
    }

    // Update status
    public AttendanceCorrection updateStatus(Leave.LeaveStatus status, Long correctionId){
        AttendanceCorrection existingattendanceCorrection = this.getById(correctionId);
        existingattendanceCorrection.setStatus(status);
        if(status == Leave.LeaveStatus.APPROVED){
            punchInAndOutService.deletePunchesOfGivenDateOfGivenEmployee(existingattendanceCorrection.getEmployee(), existingattendanceCorrection.getDate());
        }
        PunchIn newPunchIn = new PunchIn(existingattendanceCorrection.getDate(), LocalTime.of(10,0,0), existingattendanceCorrection.getEmployee());
        PunchOut newPunchOut = new PunchOut(existingattendanceCorrection.getDate(), LocalTime.of(17,30,0), existingattendanceCorrection.getEmployee());
        punchInRepository.save(newPunchIn);
        punchOutRepository.save(newPunchOut);
        return attendaceCorrectionRepository.save(existingattendanceCorrection);
    }

    // Get by employee and date
    public AttendanceCorrection getByEmployeeAndDate(Long employeeId, LocalDate date){
        List<AttendanceCorrection> allTheAttendaceCorrection = this.getAll();
        for (AttendanceCorrection attendanceCorrection : allTheAttendaceCorrection) {
            /*System.out.print(attendanceCorrection.getEmployee().getId() + " " + employeeId + " " + attendanceCorrection.getDate() + " " + date);
            System.out.print(attendanceCorrection.getEmployee().getId() == employeeId);
            System.out.println(attendanceCorrection.getDate().equals(date));*/
            if (attendanceCorrection.getEmployee().getId() == employeeId && attendanceCorrection.getDate().equals(date)) {
                System.out.println("------------------------------> returning non null" );
                return attendanceCorrection;
            }
        }
        System.out.println("------------------------------> returning null" );
        return null;
    }
}
