package com.ess.api.entities;

import java.time.LocalDateTime;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

@Entity
@Table(name = "projects")
public class Project {

    public enum ProjectStatus {
        NEW, IN_PROGRESS, ON_HOLD, COMPLETED, CANCELED
    }

    public enum RoleInProject {
        OWNER, MANAGER, MEMBER, EXTERNAL
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "project_id")
    private long id;

    @Column(name = "project_name")
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "project_status")
    private ProjectStatus status;

    @Column(name = "project_created_on")
    private LocalDateTime createdOn;

    public Project() {
        super();
    }

    @JsonCreator
    public Project(@JsonProperty("name") String name,@JsonProperty("status") ProjectStatus status){
        this.name = name;
        this.status = status;
        this.createdOn = LocalDateTime.now();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ProjectStatus getStatus() {
        return status;
    }

    public void setStatus(ProjectStatus status) {
        this.status = status;
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
    }


    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", status=" + status +
                ", createdOn=" + createdOn +
                '}';
    }
}
