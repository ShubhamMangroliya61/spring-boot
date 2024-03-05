package com.security.demo.services;

import com.security.demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class TokenService {
    @Autowired
    private final JwtEncoder encoder;

    public TokenService(JwtEncoder jwtEncoder) {
        this.encoder = jwtEncoder;
    }

    public String generateToken(User user) {
        Instant now = Instant.now();
        String scope =
                user.getRoles();
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(now)
                .subject(user.getName())
                .claim("id", user.getId())
                .claim("name", user.getName())
                .claim("roles", user.getRoles())
                .claim("scope", scope)
                .build();
        return this.encoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }

}
