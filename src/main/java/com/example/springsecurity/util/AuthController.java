package com.example.springsecurity.util;


import com.example.springsecurity.request.AuthLoginRequest;
import com.example.springsecurity.response.AuthResponse;
import com.example.springsecurity.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("")
public class AuthController {
    private final AuthService authService;


    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/api/auth/login")
    public ResponseEntity<AuthResponse> login (@Validated @RequestBody AuthLoginRequest request){
        return new ResponseEntity<>(authService.login(request), HttpStatus.OK);
    }
}
