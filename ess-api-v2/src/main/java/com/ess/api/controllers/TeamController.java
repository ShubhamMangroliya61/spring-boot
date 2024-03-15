package com.ess.api.controllers;

import com.ess.api.entities.Employee;
import com.ess.api.entities.Team;
import com.ess.api.repositories.TeamRepository;
import com.ess.api.response.ApiResponse;
import com.ess.api.services.TeamService;
import com.ess.api.utils.GetCurrentEmployee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/team")
public class TeamController {

    @Autowired
    private TeamService teamService;

    @Autowired
    private GetCurrentEmployee getCurrentEmployee;

    @PostMapping
    public ResponseEntity<Team> addTeam(@RequestBody Team team){
        Team newTeam = teamService.addTeam(team);
        return ResponseEntity.ok(newTeam);
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllTeams(Authentication authentication){
        Employee currentEmployee = getCurrentEmployee.getCurrentEmployee(authentication);
        if(!currentEmployee.getRole().getName().equalsIgnoreCase("admin")){
            ApiResponse response = new ApiResponse("You are not authorized", false);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        List<Team> allTeams = teamService.getAllTeams();
        return ResponseEntity.ok(allTeams);
    }
}
