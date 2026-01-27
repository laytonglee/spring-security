package com.example.springsecurity.response;

import lombok.Data;

import java.util.Set;

@Data
public class UserViewResponse {
    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private Set<String> role;
}
