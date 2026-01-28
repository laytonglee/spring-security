package com.example.springsecurity.service;

import com.example.springsecurity.model.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;


@Service
public class JwtService {
    private SecretKey getSigninKey() {
        return Keys.hmacShaKeyFor("d211c315e372d92699211eec5202261921df1ce634c569ec8974a0796730e5ff".getBytes());
    }

    public String generateAccessToken(User user) {
        System.out.println("Minlength: " + SignatureAlgorithm.HS256
        );
        return Jwts.builder()
                .setSubject(user.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // 10 hours
                . signWith(getSigninKey())
                .compact();
    }

    public String getRefreshToken(User user){
        return Jwts.builder()
                .setSubject(user.getUsername())
                .setClaims(new HashMap<>())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24))
                .signWith(getSigninKey())
                .compact();
    }
}
