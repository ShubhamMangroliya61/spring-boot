package com.security.demo.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;

public class JwtFilter extends GenericFilterBean {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        // extract and validate JWT token
        // set authentication in SecurityContext
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String authorizationHeader = httpServletRequest.getHeader("Authorization");

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String token = authorizationHeader.substring(7);
            Claims claims = Jwts.parser().setSigningKey("your_secret_key").parseClaimsJws(token).getBody();
            String userId = claims.getSubject(); // assuming subject contains user ID
            httpServletRequest.setAttribute("userId", userId);
        }
        chain.doFilter(request, response);
    }
}
