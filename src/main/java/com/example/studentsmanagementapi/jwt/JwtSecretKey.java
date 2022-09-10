package com.example.studentsmanagementapi.jwt;


import io.jsonwebtoken.security.Keys;
import org.springframework.context.annotation.Bean;

import javax.crypto.SecretKey;

public class JwtSecretKey {

    private final JwtConfig jwtConfig;


    public JwtSecretKey(JwtConfig jwtConfig) {
        this.jwtConfig = jwtConfig;
    }

    @Bean
    public SecretKey secretKey() {
        return Keys.hmacShaKeyFor(jwtConfig.getSecretKey().getBytes());
    }

}
