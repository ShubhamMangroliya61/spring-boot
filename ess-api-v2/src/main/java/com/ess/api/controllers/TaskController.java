package com.ess.api.controllers;

import com.ess.api.entities.*;
import com.ess.api.request.AddTaskRequest;
import com.ess.api.response.ApiResponse;
import com.ess.api.services.EmployeeService;
import com.ess.api.services.ProjectMemberService;
import com.ess.api.services.ProjectService;
import com.ess.api.services.TaskService;
import com.ess.api.utils.GetCurrentEmployee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

@RestController
@RequestMapping("/api/task")
@CrossOrigin(origins = "http://localhost:5173")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private ProjectMemberService projectMemberService;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private GetCurrentEmployee getCurrentEmployee;

    // Add task
    @PostMapping
    public ResponseEntity<?> createNewTask(@RequestBody AddTaskRequest addTaskRequest, Authentication authentication){
        Project project = projectService.getProjectById(addTaskRequest.getProjectId());
        Employee assignedBy = getCurrentEmployee.getCurrentEmployee(authentication);

        AtomicBoolean managerFound = new AtomicBoolean(false);
        if(!assignedBy.getRole().getName().equalsIgnoreCase("admin")){
            project.getMembers().forEach(projectMember -> {
                if(projectMember.getEmployee() == assignedBy && projectMember.getRole() == Project.RoleInProject.MANAGER){
                    managerFound.set(true);
                }
            });
        }else{
            managerFound.set(true);
        }
        if(!managerFound.get()){
            ApiResponse response = new ApiResponse("You are not authorized", false);
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        }

        Employee assignTo = employeeService.getEmployee(addTaskRequest.getAssignedTo());
        Task newTask = new Task(addTaskRequest.getName(), project, assignedBy, assignTo, addTaskRequest.getStatus(), addTaskRequest.getPriority());
        ProjectLog newTaskLog = taskService.addTask(newTask);

        return ResponseEntity.ok(newTaskLog);
    }

    // get tasks by employee
    @GetMapping("/assignedToMe")
    public ResponseEntity<?> getTaskAssignedToCurrentEmployee(Authentication authentication){
        Employee currentEmployee = getCurrentEmployee.getCurrentEmployee(authentication);
        List<Task> filteredTasks = taskService.getAllTasksByAssignedTo(currentEmployee);
        return ResponseEntity.ok(filteredTasks);
    }
}
