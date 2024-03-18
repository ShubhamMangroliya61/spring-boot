package com.ess.api.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "project_tasks")
public class Task {

    public enum TaskStatus {
        TODO, IN_PROGRESS, IN_REVIEW, DONE
    }

    public enum TaskPriority {
        NONE, LOW, MEDIUM, HIGH
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "task_id")
    private long id;

    @Column(name = "task_name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    @ManyToOne
    @JoinColumn(name = "assign_by")
    private Employee assignBy;

    @ManyToOne
    @JoinColumn(name = "assign_to")
    private Employee assignTo;

    @Enumerated(EnumType.STRING)
    @Column(name = "task_status")
    private TaskStatus status;

    @Enumerated(EnumType.STRING)
    @Column(name = "task_priority")
    private TaskPriority priority;

    public Task() {
        super();
    }

    public Task(String name, Project project, Employee assignBy, Employee assignTo, TaskStatus status, TaskPriority priority) {
        this.name = name;
        this.project = project;
        this.assignBy = assignBy;
        this.assignTo = assignTo;
        this.status = status;
        this.priority = priority;
    }

    // Getters and setters

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

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Employee getAssignBy() {
        return assignBy;
    }

    public void setAssignBy(Employee assignBy) {
        this.assignBy = assignBy;
    }

    public Employee getAssignTo() {
        return assignTo;
    }

    public void setAssignTo(Employee assignTo) {
        this.assignTo = assignTo;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public TaskPriority getPriority() {
        return priority;
    }

    public void setPriority(TaskPriority priority) {
        this.priority = priority;
    }

    // toString

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", project=" + project +
                ", assignBy=" + assignBy +
                ", assignTo=" + assignTo +
                ", status=" + status +
                ", priority=" + priority +
                '}';
    }
}
