package com.security.demo.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {

    private static final String SECRET_KEY = "your_secret_key";

    public static String generateToken(String userId, String userEmail, String userPassword, String userRole) {
        Claims claims = Jwts.claims().setSubject(userId);
        claims.put("userEmail", userEmail);
        claims.put("userPassword", userPassword);
        claims.put("userRole", userRole);

        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + 1000 * 60 * 60); // 1 hour

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }
}
