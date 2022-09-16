package com.example.io.controller;

import com.example.io.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/get-list")
    public ResponseEntity<Void> getUsersList() {
        userService.getUsers();
        return ResponseEntity.ok().build();
    }
}
