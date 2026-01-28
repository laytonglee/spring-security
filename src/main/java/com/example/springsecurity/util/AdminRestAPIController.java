package com.example.springsecurity.util;


import com.example.springsecurity.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@Slf4j
@RestController
public class AdminRestAPIController {

    @PreAuthorize("hasRole('ADMIN')")
    @Secured("ADMIN")
    @GetMapping("/api/admin/users")
    public ResponseEntity<String> getAllUsers() {
        return new ResponseEntity<>("Hello From Admin Controller", HttpStatus.OK);

    }

    @GetMapping("/api/admin/007")
    public ResponseEntity<String> adminAPI007(Principal principal, @AuthenticationPrincipal User user) {
            log.info("1. Principle User Information: {}", principal.getName());
            log.info("2.@AuthenticationPrincipal User Information: {}", user.getEmail());
            log.info("3.SecurityContextHolder User Information: {}", SecurityContextHolder.getContext().getAuthentication().getName());
            log.info("4.SecurityUtils User Information: {}", SecurityUtils.getCurrentUser());

            return new ResponseEntity<>("Hello World! From Admin API 007.", HttpStatus.OK);

    }
}
