package com.assignment.springbootapplication.controller;

import com.assignment.springbootapplication.entity.User;
import com.assignment.springbootapplication.exception.UserNotFoundException;
import com.assignment.springbootapplication.payload.request.UserDetailsRequest;
import com.assignment.springbootapplication.payload.response.UserDetailsResponse;
import com.assignment.springbootapplication.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/api/user-detail")
@RestController
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    @Qualifier("userServiceImpl")
    private UserService userService;

    @GetMapping("fetch/{id}")
    public ResponseEntity<UserDetailsResponse> fetchById(@PathVariable("id") int id) {
        logger.info("Fetching user details for ID: {}", id);
        UserDetailsResponse userDetailsResponse;
        try {
            userDetailsResponse = userService.getUserById(id);
            logger.info("User details found for ID: {}", id);
        } catch (UserNotFoundException e) {
            logger.warn("User not found for ID: {}", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(userDetailsResponse, HttpStatus.OK);
    }

    @PostMapping("create/user")
    public ResponseEntity<UserDetailsResponse> createUser(@Valid @RequestBody UserDetailsRequest userDetailsRequest) {
        logger.info("Creating a new user");
        UserDetailsResponse response = userService.createUser(userDetailsRequest);
        logger.info("User created successfully");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("fetch/by-name/contains/{letter}")
    public ResponseEntity<List<User>> findByNameContainingLetter(@PathVariable("letter") String letter) {
        logger.info("Finding users by name containing letter: {}", letter);
        List<User> users = userService.findByNameContaining(letter);
        logger.info("Found {} users matching the search criteria", users.size());
        return ResponseEntity.ok(users);
    }

    @GetMapping("fetch/by-name/{name}")
    public ResponseEntity<List<User>> fetchByName(@PathVariable("name") String name) {
        logger.info("Finding users by name: {}", name);
        List<User> users = userService.findByName(name);
        logger.info("Found {} users matching the name: {}", users.size(), name);
        return ResponseEntity.ok(users);
    }

    @PutMapping("update/{id}")
    public ResponseEntity<UserDetailsResponse> updateId(@PathVariable("id") int id, @RequestBody UserDetailsRequest userDetailsRequest) {
        logger.info("Updating user with ID: {}", id);
        try {
            UserDetailsResponse response = userService.updateUser(id, userDetailsRequest);
            logger.info("User with ID {} updated successfully", id);
            return ResponseEntity.ok(response);
        } catch (UserNotFoundException e) {
            logger.warn("User not found for ID: {}", id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("remove/{id}")
    public ResponseEntity<Void> removeId(@PathVariable("id") int id) {
        logger.info("Removing user with ID: {}", id);
        try {
            userService.deleteUser(id);
            logger.info("User with ID {} removed successfully", id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (UserNotFoundException e) {
            logger.warn("User not found for ID: {}", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

