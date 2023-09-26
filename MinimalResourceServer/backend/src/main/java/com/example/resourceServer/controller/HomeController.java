package com.example.resourceServer.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;

@RestController
public class HomeController {

  @GetMapping("/home")
  public ResponseEntity<String> home(Authentication authentication) {
    return ResponseEntity.ok("Hello " + authentication.getName() + ", now is " + Instant.now());
  }

  @GetMapping("/admin")
  public ResponseEntity<String> admin(Authentication authentication) {
    return ResponseEntity.ok("Hello from internal " + authentication.getName() + ", now is " + Instant.now());
  }
}
