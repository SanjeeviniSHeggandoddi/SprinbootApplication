package com.assignment.springbootapplication.service;

import com.assignment.springbootapplication.entity.User;
import com.assignment.springbootapplication.payload.request.UserDetailsRequest;
import com.assignment.springbootapplication.payload.response.UserDetailsResponse;
import java.util.List;

public interface UserService {
    UserDetailsResponse getUserById(int id);
    UserDetailsResponse createUser(UserDetailsRequest userDetailsRequest);
    List<User> findByNameContaining(String letter);
    List<User> findByName(String name);
    UserDetailsResponse updateUser(int id, UserDetailsRequest userDetailsRequest);
    void deleteUser(int id);
}
