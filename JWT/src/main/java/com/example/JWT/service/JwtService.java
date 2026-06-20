package com.example.JWT.service;

import java.util.Date;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;


@Service
public class JwtService {
    private final String SECRET="hello000000000000000000000000000000000000000000000000000000000";

    public String generateToken(String username) {

        return Jwts.builder().setSubject(username)
        .setIssuedAt((new Date()))
    .setExpiration(new Date(System.currentTimeMillis()+1000*60*60))
    .signWith(Keys.hmacShaKeyFor(SECRET.getBytes()))
    .compact();
    }

   public String extractUsername(String token) {
    return Jwts.parserBuilder()
            .setSigningKey(Keys.hmacShaKeyFor(SECRET.getBytes()))
            .build()
            .parseClaimsJws(token)
            .getBody()
            .getSubject();
}


}
