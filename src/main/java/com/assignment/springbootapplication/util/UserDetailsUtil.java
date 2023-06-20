package com.assignment.springbootapplication.util;

import com.assignment.springbootapplication.entity.User;
import com.assignment.springbootapplication.payload.request.UserDetailsRequest;
import com.assignment.springbootapplication.payload.response.UserDetailsResponse;

import java.util.Optional;

public class UserDetailsUtil {

    public static UserDetailsResponse toResponse(Optional<User> userDetails) {
        UserDetailsResponse response = new UserDetailsResponse();
        response.setId(userDetails.get().getId());
        response.setName(userDetails.get().getName());
        response.setEmail(userDetails.get().getEmail());
        response.setDob(userDetails.get().getDob());
        return response;
    }

    public static User toCreate(UserDetailsRequest userDetailsRequest) {
        User userDetails = new User();
        userDetails.setName(userDetailsRequest.getName());
        userDetails.setEmail(userDetailsRequest.getEmail());
        userDetails.setDob(userDetailsRequest.getDob());
        return userDetails;
    }

    public static UserDetailsResponse toResponse(User userDetails) {
        UserDetailsResponse response = new UserDetailsResponse();
        response.setId(userDetails.getId());
        response.setName(userDetails.getName());
        response.setEmail(userDetails.getEmail());
        response.setDob(userDetails.getDob());
        return response;
    }
}
