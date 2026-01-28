package com.example.springsecurity;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PublicAPIRestController {

    @GetMapping("/api/public/hello")
    public ResponseEntity<String> home () {
        return new ResponseEntity<>("Hello from Public Rest API", HttpStatus.OK);
    }
}
