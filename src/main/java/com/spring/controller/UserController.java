package com.spring.controller;

import com.spring.dao.UserEntity;
import com.spring.dto.RegisterRequest;
import com.spring.service.AuthenticationService;
import com.spring.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/app")
@AllArgsConstructor
public class UserController {

    private UserService userService;
    private final AuthenticationService authenticationService;

    @PostMapping(path = "/register", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> signup(@RequestBody RegisterRequest registerRequest) {
        authenticationService.register(registerRequest);
        return new ResponseEntity<>("User Registration Successful", HttpStatus.CREATED);
    }

    @GetMapping("/test1")
    public String test1() {
        return "<h1>TEST</h1>";
    }

    @GetMapping("users")
    public UserDetails findUserByUsername(@RequestParam String name) {
        return userService.loadUserByUsername(name);
    }

    @GetMapping("users/{id}")
    @ResponseBody
    public UserDetails findUserById(@PathVariable Long id) {
        return userService.findUserById(id);
    }

    @GetMapping("users/all")
    @ResponseBody
    public List<UserEntity> findAllUsers() {
        return userService.showAllUsers();
    }

    @DeleteMapping("users/delete/{id}")
    public void removeUserById(@PathVariable Long id) {
        userService.removeUserById(id);
    }
}



