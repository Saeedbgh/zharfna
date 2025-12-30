package com.zharfna.zharfna.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.List;

@Component
public class JwtTokenProvider {

    private static final String SECRET = "very-very-secret-key-for-jwt-signing-256-bit!!";

    private static final long EXPIRATION_TIME = 1000 * 60 * 60 * 24;

    private final SecretKey key = Keys.hmacShaKeyFor(SECRET.getBytes());


    public String generateToken(String username, List<String> roles) {
        return Jwts.builder()
                .setSubject(username) //Payload
                .claim("roles", roles) //Payload
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public String getUsername(String token) {
        String username =
    }



    public boolean validateToken(String token){

    }

    private Jws<Claims> getClaims(String token){

    }
}
