package com.ess.api.entities;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "teams")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Team {
    
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "team_id")
    private long id;

    @Column(name = "team_name", nullable = false, unique = true)
    private String name;

    @Column(name = "team_description")
    private String  description;

    @JsonCreator
    public Team(@JsonProperty("name") String name,
                @JsonProperty("description") String description){
        this.name = name;
        this.description = (description != null) ? description : name.trim().toUpperCase()+"_DESCRIPTION";
    }

    public Team(String name) {
        this.name = name;
        this.description = name.trim().toUpperCase()+"_DESCRIPTION";
    }

    @Override
    public String toString() {
        return "Team{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
