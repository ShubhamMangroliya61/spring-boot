package com.ess.api.services;

import com.ess.api.entities.Employee;
import com.ess.api.entities.PunchIn;
import com.ess.api.entities.PunchOut;
import com.ess.api.response.Punch;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PunchInAndOutService {
    @Autowired
    private PunchInService punchInService;

    @Autowired
    private PunchOutService punchOutService;

    @Autowired
    private ModelMapper modelMapper;

    public List<Punch> getAllPunchesByDateAndEmployee(Employee employee, LocalDate date){
        List<PunchIn> allPunchInByDateAndEmployee = punchInService.getAllPunchInByDateAndEmployee(date, employee);
        List<PunchOut> allPunchOutByDateAndEmployee = punchOutService.getAllPunchOutByDateAndEmployee(date, employee);

        List<Punch> allPunchIn = allPunchInByDateAndEmployee
                .stream()
                .map(punchIn -> {
                    Punch punch = modelMapper.map(punchIn, Punch.class);
                    punch.setPunchIn(true);
                    punch.setPunchOut(false);
                    return punch;
                }).toList();

        List<Punch> allPunchOut = allPunchOutByDateAndEmployee
                .stream()
                .map(punchOut -> {
                    Punch punch = modelMapper.map(punchOut, Punch.class);
                    punch.setPunchIn(false);
                    punch.setPunchOut(true);
                    return punch;
                }).toList();

        List<Punch> allPunches = new ArrayList<>();
        allPunches.addAll(allPunchIn);
        allPunches.addAll(allPunchOut);

        allPunches.sort(Comparator.comparing(Punch::getTime));
        return allPunches;
    }
}
