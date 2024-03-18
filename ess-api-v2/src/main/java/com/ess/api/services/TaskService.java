package com.ess.api.services;

import com.ess.api.entities.ProjectLog;
import com.ess.api.entities.Task;
import com.ess.api.exceptions.ResourceAlreadyExistsException;
import com.ess.api.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private ProjectLogService projectLogService;

    // Add task
    public ProjectLog addTask(Task task){
        Task existingTask = taskRepository.findTaskByName(task.getName());
        if(existingTask != null){
            throw new ResourceAlreadyExistsException("Task ", " with name ", task.getName());
        }
        Task savedTask = taskRepository.save(task);
        return projectLogService.addLogToProject(task.getProject(), savedTask.getName() + " task assign to " + savedTask.getAssignTo().getEmail() + " by " + savedTask.getAssignBy().getEmail());
    }
}
