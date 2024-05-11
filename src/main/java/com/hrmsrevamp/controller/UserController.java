package com.hrmsrevamp.controller;

import com.hrmsrevamp.entity.User;
import com.hrmsrevamp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

  @Autowired private UserService userService;

  @GetMapping("/{role}")
  public ResponseEntity<com.hrmsrevamp.model.CustomResponse> getAllUser(@PathVariable String role){
     return ResponseEntity.ok(userService.getAllUser(role));
  }
}
