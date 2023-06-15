package com.totemcrew.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/test")
public class TestController {
  @GetMapping("/all")
  public String allAccess() {
    return "Public Content.";
  }

  @GetMapping("/student")
  @PreAuthorize("hasRole('STUDENT')")
  public String userAccess() {
    return "Student Content.";
  }

  @GetMapping("/principal")
  @PreAuthorize("hasRole('PRINCIPAL')")
  public String moderatorAccess() {
    return "Principal Board.";
  }

  @GetMapping("/teacher")
  @PreAuthorize("hasRole('TEACHER')")
  public String adminAccess() {
    return "Teacher Board.";
  }
}
