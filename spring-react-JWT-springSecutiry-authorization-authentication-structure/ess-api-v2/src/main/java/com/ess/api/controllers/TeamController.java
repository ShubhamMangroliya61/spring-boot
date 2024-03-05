package com.ess.api.controllers;

import com.ess.api.entities.Team;
import com.ess.api.repositories.TeamRepository;
import com.ess.api.services.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/team")
public class TeamController {

    @Autowired
    private TeamService teamService;

    @PostMapping
    public ResponseEntity<Team> addTeam(@RequestBody Team team){
        Team newTeam = teamService.addTeam(team);
        return ResponseEntity.ok(newTeam);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Team>> getAllTeams(){
        List<Team> allTeams = teamService.getAllTeams();
        return ResponseEntity.ok(allTeams);
    }
}
