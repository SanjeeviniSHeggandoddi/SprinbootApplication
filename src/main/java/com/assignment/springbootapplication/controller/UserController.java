package com.assignment.springbootapplication.controller;

import com.assignment.springbootapplication.entity.User;
import com.assignment.springbootapplication.exception.UserNotFoundException;
import com.assignment.springbootapplication.payload.request.UserDetailsRequest;
import com.assignment.springbootapplication.payload.response.UserDetailsResponse;
import com.assignment.springbootapplication.repository.UserRepository;
import com.assignment.springbootapplication.service.UserService;
import com.assignment.springbootapplication.util.UserDetailsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/userdetails")
@RestController
public class UserController {

    @Autowired
    private UserService userService;
    private UserRepository userRepository;
    private UserDetailsUtil userDetailsUtil;

    @GetMapping("/{id}")
    public ResponseEntity<UserDetailsResponse> getUserById(@PathVariable("id") int id) {
        UserDetailsResponse userDetailsResponse;
        try {
            userDetailsResponse = userService.getUserById(id);
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(userDetailsResponse, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<UserDetailsResponse> createUser(@RequestBody UserDetailsRequest userDetailsRequest) {
        UserDetailsResponse response = userService.createUser(userDetailsRequest);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/native-start-with-s")
    public ResponseEntity<List<User>> usingNativegetUsersWithNameStartingWithS() {
        List<User> users = userService.usingNativegetUsersWithNameStartingWithS();
        return ResponseEntity.ok(users);
    }

    @GetMapping("jpa-/start-with-s")
    public ResponseEntity<List<User>> usingJpagetUsersWithNameStartingWithS() {
        List<User> users = userService.usingJpagetUsersWithNameStartingWithS();
        return ResponseEntity.ok(users);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDetailsResponse> updateUser(@PathVariable("id") int id, @RequestBody UserDetailsRequest userDetailsRequest) {
        try {
            UserDetailsResponse response = userService.updateUser(id, userDetailsRequest);
            return  ResponseEntity.ok(response);
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") int id) {
        try {
            userService.deleteUser(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

