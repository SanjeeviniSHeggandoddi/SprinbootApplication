package com.assignment.springbootapplication.service;

import com.assignment.springbootapplication.entity.User;
import com.assignment.springbootapplication.exception.UserNotFoundException;
import com.assignment.springbootapplication.payload.request.UserDetailsRequest;
import com.assignment.springbootapplication.payload.response.UserDetailsResponse;
import com.assignment.springbootapplication.repository.UserRepository;
import com.assignment.springbootapplication.util.UserDetailsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    private UserDetailsUtil userDetailsUtil;

    public UserDetailsResponse getUserById(int id) {
        Optional<User> user = Optional.ofNullable(userRepository.findById(id).orElse(null));
        if (!user.isPresent()) {
            throw new UserNotFoundException("User not found with id: " + id);
        }
        return UserDetailsUtil.toResponse(user);
    }

    public UserDetailsResponse createUser(UserDetailsRequest userDetailsRequest) {
        User userDetails = UserDetailsUtil.toCreate(userDetailsRequest);
        User savedUser = userRepository.save(userDetails);
        return
                userDetailsUtil.toResponse(Optional.of(savedUser));
    }

    public List<User> usingNativegetUsersWithNameStartingWithS() {
        Optional<List<User>> usersOpt = userRepository.usingNativegetUsersWithNameStartingWithS();
        return usersOpt.orElseThrow(() -> new UserNotFoundException("No users found starting with 's'"));
    }

    public List<User> usingJpagetUsersWithNameStartingWithS() {
        Optional<List<User>> usersOpt = userRepository.usingJpagetUsersWithNameStartingWithS();
        return usersOpt.orElseThrow(() -> new UserNotFoundException("No users found starting with 's'"));
    }

    public UserDetailsResponse updateUser(int id, UserDetailsRequest userDetailsRequest) {
        Optional<User> userDetailsOpt = userRepository.findById(id);
        if (userDetailsOpt.isPresent()) {
            User userDetails = userDetailsOpt.get();
            userDetails.setName(userDetailsRequest.getName());
            userDetails.setEmail(userDetailsRequest.getEmail());
            userDetails.setDob(userDetailsRequest.getDob());
            User updatedUser = userRepository.save(userDetails);
            return userDetailsUtil.toResponse(Optional.of(updatedUser));
        } else {
            throw new UserNotFoundException("User not found with ID: " + id);
        }
    }

    public void deleteUser(int id) {
        Optional<User> userDetailsOpt = userRepository.findById(id);
        if (userDetailsOpt.isPresent()) {
            userRepository.delete(userDetailsOpt.get());
        } else {
            throw new UserNotFoundException("User not found with ID: " + id);
        }
    }
}

