package com.security.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/api")
public class HomeController {

    @GetMapping("/public")
    public ResponseEntity<String> publicUser(){
        return ResponseEntity.ok("I am PUBLIC user");
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/normal")
    public ResponseEntity<String> normalUser(Principal principal){
        return ResponseEntity.ok("I am NORMAL user: " + principal.getName());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin")
    public ResponseEntity<String> adminUser(Principal principal){
        return ResponseEntity.ok("I am ADMIN user: " + principal.getName());
    }
}
