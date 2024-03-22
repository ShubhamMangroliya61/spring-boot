package com.ess.api.services;

import com.ess.api.entities.Employee;
import com.ess.api.entities.Project;
import com.ess.api.entities.ProjectLog;
import com.ess.api.entities.Task;
import com.ess.api.exceptions.ResourceAlreadyExistsException;
import com.ess.api.exceptions.ResourceNotFoundException;
import com.ess.api.repositories.ProjectRepository;
import com.ess.api.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private ProjectLogService projectLogService;

    @Autowired
    private ProjectRepository projectRepository;

    // Add task
    public ProjectLog addTask(Task task){
        Task existingTask = taskRepository.findTaskByName(task.getName());
        if(existingTask != null){
            throw new ResourceAlreadyExistsException("Task ", " with name ", task.getName());
        }
        Task savedTask = taskRepository.save(task);
        return projectLogService.addLogToProject(task.getProject(), savedTask.getName() + " task assign to " + savedTask.getAssignTo().getEmail() + " by " + savedTask.getAssignBy().getEmail());
    }

    // Get all
    public List<Task> getAllTasks(){
        return taskRepository.findAll();
    }

    // Get all by assignedTo
    public List<Task> getAllTasksByAssignedTo(Employee assignedTo){
        return this.getAllTasks().stream().filter(task -> task.getAssignTo() == assignedTo).toList();
    }


    // Get all by assignedBy
    public List<Task> getAllTasksByAssignedBy(Employee assignedBy){
        return this.getAllTasks().stream().filter(task -> task.getAssignBy() == assignedBy).toList();
    }

    // Get by id
    public Task getTaskById(long taskId){
        return taskRepository.findById(taskId).orElseThrow(() -> new ResourceNotFoundException("Task", "taskId", ""+taskId));
    }

    // Update task status
    public ProjectLog updateTaskStatus(Task task){
        Task oldTask = this.getTaskById(task.getId());
        Task.TaskStatus oldStatus = oldTask.getStatus();
        oldTask.setStatus(task.getStatus());
        Task updatedTask = taskRepository.save(oldTask);
        System.out.println(updatedTask.getStatus());
        return projectLogService.addLogToProject(task.getProject(), " updated task status of " + updatedTask.getName() + " from " + oldStatus + " to " + updatedTask.getStatus());
    }
}
