package com.example.springsecurity.service;

import com.example.springsecurity.config.JwtProperties;
import com.example.springsecurity.model.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@Service
@RequiredArgsConstructor
public class JwtService {

    private final JwtProperties jwtProperties;

    private SecretKey getSigninKey() {
        return Keys.hmacShaKeyFor("d211c315e372d92699211eec5202261921df1ce634c569ec8974a0796730e5ff".getBytes());
    }

    public String generateAccessToken(User user) {

        Map <String, Object> claims = new HashMap<>();
        claims.put("uid", user.getId());
//        claims.put("role", user.getRoles());

        return Jwts.builder()
                .setSubject(user.getUsername())
                .addClaims(claims)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtProperties.getExpiration())) // 10 hours
                . signWith(getSigninKey())
                .compact();
    }

    public String getRefreshToken(User user){
        return Jwts.builder()
                .setSubject(user.getUsername())
                .setClaims(new HashMap<>())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()
                        + jwtProperties.getRefreshExpiration()))
                .signWith(getSigninKey())
                .compact();
    }
}
