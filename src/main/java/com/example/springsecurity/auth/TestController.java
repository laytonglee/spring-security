package com.example.springsecurity.auth;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TestController {

    @GetMapping("/public")
    public String Home () {
        return "Hello from Public";
    }

    @GetMapping("/auth")
    public String Auth () {
        return "Hello from Authentication ";
    }
}
