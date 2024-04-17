package com.ess.api.services;

import com.ess.api.entities.*;
import com.ess.api.repositories.SubTaskRepository;
import com.ess.api.request.AddSubTaskRequest;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubTaskService {

    @Autowired
    private SubTaskRepository subTaskRepository;

    @Autowired
    private ProjectLogService projectLogService;

    // Add subtask to given task
    public ProjectLog addSubTask(AddSubTaskRequest addSubTaskRequest, Task task, Employee currentEmployee){
        SubTask subTask = new SubTask(addSubTaskRequest.getDescription(), task, addSubTaskRequest.getStatus());
        SubTask savedSubTask = subTaskRepository.save(subTask);
        return projectLogService.addLogToProject(savedSubTask.getTask().getProject(), currentEmployee.getEmail() + " added child task " + savedSubTask.getDescription());
    }
}
