package com.assignment.springbootapplication.controller;

import com.assignment.springbootapplication.entity.User;
import com.assignment.springbootapplication.exception.UserNotFoundException;
import com.assignment.springbootapplication.payload.request.UserDetailsRequest;
import com.assignment.springbootapplication.payload.response.UserDetailsResponse;
import com.assignment.springbootapplication.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RequestMapping("/api/user-detail")
@RestController
public class UserController {

    @Autowired
    @Qualifier("userServiceMapImpl")
    private UserService userService;

    @GetMapping("fetch/{id}")
    public ResponseEntity<UserDetailsResponse> fetchById(@PathVariable("id") int id) {
        UserDetailsResponse userDetailsResponse;
        try {
            userDetailsResponse = userService.getUserById(id);
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(userDetailsResponse, HttpStatus.OK);
    }

    @PostMapping("create/user")
    public ResponseEntity<UserDetailsResponse> createUser(@RequestBody UserDetailsRequest userDetailsRequest) {
        UserDetailsResponse response = userService.createUser(userDetailsRequest);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

   @GetMapping("fetch/by-name/contains/{letter}")
   public ResponseEntity<List<User>> findByNameContainingLetter(@PathVariable("letter") String letter) {
       List<User> users = userService.findByNameContaining(letter);
       return ResponseEntity.ok(users);
   }

    @GetMapping("fetch/by-name/{name}")
    public ResponseEntity<List<User>> fetchByName(@PathVariable("name") String name) {
        List<User> users = userService.findByName(name);
        return ResponseEntity.ok(users);
    }

    @PutMapping("update/{id}")
    public ResponseEntity<UserDetailsResponse> updateId(@PathVariable("id") int id, @RequestBody UserDetailsRequest userDetailsRequest) {
        try {
            UserDetailsResponse response = userService.updateUser(id, userDetailsRequest);
            return  ResponseEntity.ok(response);
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("remove/{id}")
    public ResponseEntity<Void> removeId(@PathVariable("id") int id) {
        try {
            userService.deleteUser(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

