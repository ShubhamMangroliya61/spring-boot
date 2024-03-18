package com.ess.api.request;

import com.ess.api.entities.Task;

public class AddTaskRequest {
    private String name;

    private long projectId;
    private long assignedBy;
    private long assignedTo;

    private Task.TaskStatus status;

    private Task.TaskPriority priority;

    public AddTaskRequest() {
        super();
    }

    public AddTaskRequest(String name, long projectId, long assignedBy, long assignedTo, Task.TaskStatus status, Task.TaskPriority priority) {
        this.name = name;
        this.projectId = projectId;
        this.assignedBy = assignedBy;
        this.assignedTo = assignedTo;
        this.status = status;
        this.priority = priority;
    }

    public AddTaskRequest(String name, long projectId, long assignedBy, long assignedTo) {
        this.name = name;
        this.projectId = projectId;
        this.assignedBy = assignedBy;
        this.assignedTo = assignedTo;
        this.status = Task.TaskStatus.TODO;
        this.priority = Task.TaskPriority.NONE;
    }

    public AddTaskRequest(String name, long projectId, long assignedBy, long assignedTo, Task.TaskStatus status) {
        this.name = name;
        this.projectId = projectId;
        this.assignedBy = assignedBy;
        this.assignedTo = assignedTo;
        this.status = status;
        this.priority = Task.TaskPriority.NONE;
    }

    public AddTaskRequest(String name, long projectId, long assignedBy, long assignedTo, Task.TaskPriority priority) {
        this.name = name;
        this.projectId = projectId;
        this.assignedBy = assignedBy;
        this.assignedTo = assignedTo;
        this.status = Task.TaskStatus.TODO;
        this.priority = priority;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getProjectId() {
        return projectId;
    }

    public void setProjectId(long projectId) {
        this.projectId = projectId;
    }

    public long getAssignedBy() {
        return assignedBy;
    }

    public void setAssignedBy(long assignedBy) {
        this.assignedBy = assignedBy;
    }

    public long getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(long assignedTo) {
        this.assignedTo = assignedTo;
    }

    public Task.TaskStatus getStatus() {
        return status;
    }

    public void setStatus(Task.TaskStatus status) {
        this.status = status;
    }

    public Task.TaskPriority getPriority() {
        return priority;
    }

    public void setPriority(Task.TaskPriority priority) {
        this.priority = priority;
    }

    @Override
    public String toString() {
        return "AddTaskRequest{" +
                "name='" + name + '\'' +
                ", projectId=" + projectId +
                ", assignedBy=" + assignedBy +
                ", assignedTo=" + assignedTo +
                ", status=" + status +
                ", priority=" + priority +
                '}';
    }
}
