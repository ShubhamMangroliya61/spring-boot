package com.ess.api.controllers;

import com.ess.api.entities.Employee;
import com.ess.api.entities.Project;
import com.ess.api.entities.ProjectMember;
import com.ess.api.entities.Task;
import com.ess.api.request.AddTaskRequest;
import com.ess.api.services.EmployeeService;
import com.ess.api.services.ProjectMemberService;
import com.ess.api.services.ProjectService;
import com.ess.api.services.TaskService;
import com.ess.api.utils.GetCurrentEmployee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/project")
@CrossOrigin(origins = "http://localhost:5173")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @Autowired
    private GetCurrentEmployee getCurrentEmployee;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private ProjectMemberService projectMemberService;

    @Autowired
    private ProjectService projectService;

    // Add task
    @PostMapping
    public ResponseEntity<?> createNewTask(@RequestBody AddTaskRequest addTaskRequest){
        Project project = projectService.getProjectById(addTaskRequest.getProjectId());
        Employee assignedBy = employeeService.getEmployee(addTaskRequest.getAssignedBy());
        List<Employee> 
    }
}
