package com.example.Authorization.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/private")
public class PrivateApiController {

    @GetMapping("/hello")
    public ResponseEntity<String> helloPrivate() {

        return ResponseEntity.ok("Hello from Private API!");
    }
}

