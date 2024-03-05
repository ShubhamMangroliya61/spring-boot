package com.ess.api.services;

import com.ess.api.entities.Team;
import com.ess.api.repositories.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamService {

    @Autowired
    private TeamRepository teamRepository;

    // Add team
    public Team addTeam(Team team){
        return teamRepository.save(team);
    }

    // Get all
    public List<Team> getAllTeams(){
        return teamRepository.findAll();
    }
}
