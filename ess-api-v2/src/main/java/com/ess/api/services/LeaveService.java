package com.ess.api.services;

import com.ess.api.entities.Employee;
import com.ess.api.entities.Leave;
import com.ess.api.repositories.LeaveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeaveService {

    @Autowired
    private LeaveRepository leaveRepository;

    // Add request
    public Leave addLeaveRequest(Leave leave){
        return leaveRepository.save(leave);
    }

    // Get all
    public List<Leave> getAllLeaves(){
        return leaveRepository.findAll();
    }

    // Get by Employee
    public List<Leave> getAllLeaveRequestsByEmployee(Employee employee){
        return leaveRepository.findByEmployee(employee);
    }
}
