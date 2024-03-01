package com.ess.api.controllers;

import com.ess.api.entities.Role;
import com.ess.api.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @PostMapping
    public ResponseEntity<Role> addRole(@RequestBody Role role){
        Role newRole = roleService.addRole(role);
        return ResponseEntity.ok(newRole);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Role>> getAllRoles(){
        List<Role> allRoles = roleService.getAllRoles();
        return ResponseEntity.ok(allRoles);
    }

    // Delete all
    /*
    @DeleteMapping("/all")
    public String deleteAll(){
        roleService.deleteAll();
        return "Deleted";
    }
     */

}
