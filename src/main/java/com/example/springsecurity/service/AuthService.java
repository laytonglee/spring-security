package com.example.springsecurity.service;


import com.example.springsecurity.request.AuthLoginRequest;
import com.example.springsecurity.response.AuthResponse;
import org.springframework.stereotype.Service;

@Service
public interface AuthService {
    AuthResponse login (AuthLoginRequest request);
}
