package com.example.springsecurity.enumeration;

public enum Role {
    USER,
    ADMIN,
    MANAGER;

    public String getAuthority() {
        return "ROLE_" + this.name();
    }
}
