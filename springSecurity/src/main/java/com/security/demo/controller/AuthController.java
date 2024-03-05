package com.security.demo.controller;

import com.security.demo.config.JwtUtil;
import com.security.demo.entity.User;
import com.security.demo.services.TokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.context.SecurityContextHolder;

@RestController
public class AuthController {
//    private static final Logger LOG = LoggerFactory.getLogger(AuthController.class);
//
//    private final TokenService tokenService;
//
//    public AuthController(TokenService tokenService) {
//        this.tokenService = tokenService;
//    }
//
//    @PostMapping("/token")
//    public String token(User user) {
//        LOG.debug("Token requested for user: '{}'", user.getName());
//        String token = tokenService.generateToken(user);
//        LOG.debug("Token granted: {}", token);
//        return token;
//    }
    @Autowired
    private JwtUtil jwtUtil;

//    @Autowired
//    private AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user) {
        // Authenticate user
//        Authentication authentication = authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(user.getName(), user.getPassword())
//        );

        // Set authentication in SecurityContext
//        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Generate JWT token
        String token = jwtUtil.generateToken(user.getName(), user.getName(), user.getPassword(), user.getRoles());

        // Return JWT token
        return ResponseEntity.status(HttpStatus.OK).body(token);
    }
}
