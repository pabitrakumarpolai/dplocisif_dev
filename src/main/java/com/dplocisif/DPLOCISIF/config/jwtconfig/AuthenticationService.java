package com.dplocisif.DPLOCISIF.config.jwtconfig;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;

@Service
public class  AuthenticationService {
    private String SECRET_KEY;

    public AuthenticationService(@Value("${jwt.secretKey}") String SECRET_KEY) {
        this.SECRET_KEY = SECRET_KEY;
    }

    private String encodedKey;
    public String generateToken(String username) {
        long currentTimeMillis = System.currentTimeMillis();
        long expirationTimeMillis = currentTimeMillis + 43200000; // Token expires in 12 hour
//        long currentTimeMillis = System.currentTimeMillis();
//        long expirationTimeMillis = currentTimeMillis + 60000; // Token expires in 1 minute
        encodedKey = Base64.getEncoder().encodeToString(SECRET_KEY.getBytes());
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date(currentTimeMillis))
                .setExpiration(new Date(expirationTimeMillis))
                .signWith(SignatureAlgorithm.HS256, encodedKey)
                .compact();
    }

}
