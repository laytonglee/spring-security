package com.example.springsecurity.service.impl;

import com.example.springsecurity.model.User;
import com.example.springsecurity.repository.UserRepository;
import com.example.springsecurity.request.AuthLoginRequest;
import com.example.springsecurity.response.AuthResponse;
import com.example.springsecurity.response.UserViewResponse;
import com.example.springsecurity.service.AuthService;
import com.example.springsecurity.service.JwtService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Slf4j
@Service
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final JwtService jwtService;

    public AuthServiceImpl(AuthenticationManager authenticationManager, UserRepository userRepository, JwtService jwtService) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.jwtService = jwtService;
    }


    @Override
    public AuthResponse login(AuthLoginRequest request){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        User user = userRepository.findByEmail(request.getUsername()).orElse(null);

        AuthResponse authResponse = new AuthResponse();
        authResponse.setAccessToken(jwtService.generateAccessToken(user));
        authResponse.setRefreshToken(jwtService.getRefreshToken(user));
        authResponse.setType("Bearer");

        UserViewResponse userViewResponse = new UserViewResponse();
        userViewResponse.setId(user.getId());
        userViewResponse.setEmail(user.getEmail());
        userViewResponse.setRole(user.getRoles().stream().map(role -> role.getName()).collect(Collectors.toSet()));

        authResponse.setUser(userViewResponse);
        return authResponse;
    }
}
