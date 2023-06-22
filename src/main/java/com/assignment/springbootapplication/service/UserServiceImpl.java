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
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetailsResponse getUserById(int id) {
        Optional<User> user = Optional.ofNullable(userRepository.findById(id).orElse(null));
        if (!user.isPresent()) {
            throw new UserNotFoundException("User not found with id: " + id);
        }
        return UserDetailsUtil.toResponse(user);
    }
    @Override
    public UserDetailsResponse createUser(UserDetailsRequest userDetailsRequest) {
        User userDetails = UserDetailsUtil.toCreate(userDetailsRequest);
        User savedUser = userRepository.save(userDetails);
        return UserDetailsUtil.toResponse(savedUser);
    }
    @Override
    public List<User> findByNameContaining(String letter) {
        Optional<List<User>> usersOpt = userRepository.findByNameContaining(letter);
        return usersOpt.orElseThrow(() -> new UserNotFoundException("No users found "));
    }
    @Override
    public List<User> findByName(String name) {
        Optional<List<User>> usersOpt = userRepository.findByName(name);
        return usersOpt.orElseThrow(() -> new UserNotFoundException("No users found"));
    }
    @Override
    public UserDetailsResponse updateUser(int id, UserDetailsRequest userDetailsRequest) {
        Optional<User> userDetailsOpt = userRepository.findById(id);
        if (userDetailsOpt.isPresent()) {
            User userDetails = userDetailsOpt.get();
            userDetails.setName(userDetailsRequest.getName());
            userDetails.setEmail(userDetailsRequest.getEmail());
            userDetails.setDob(userDetailsRequest.getDob());
            User updatedUser = userRepository.save(userDetails);
            return UserDetailsUtil.toResponse(updatedUser);
        } else {
            throw new UserNotFoundException("User not found with ID: " + id);
        }
    }
    @Override
    public void deleteUser(int id) {
        Optional<User> userDetailsOpt = userRepository.findById(id);
        if (userDetailsOpt.isPresent()) {
            userRepository.delete(userDetailsOpt.get());
        } else {
            throw new UserNotFoundException("User not found with ID: " + id);
        }
    }
}

