package com.assignment.springbootapplication.service;

import com.assignment.springbootapplication.entity.User;
import com.assignment.springbootapplication.exception.UserNotFoundException;
import com.assignment.springbootapplication.payload.request.UserDetailsRequest;
import com.assignment.springbootapplication.payload.response.UserDetailsResponse;
import com.assignment.springbootapplication.util.SequenceGeneratorUtil;
import com.assignment.springbootapplication.util.UserDetailsUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceMapImpl implements UserService {
    private static final Logger logger = LogManager.getLogger(UserServiceMapImpl.class);
    private HashMap<Integer, User> userHashMap = new HashMap<>();

    @Override
    public UserDetailsResponse getUserById(int id) {
        logger.info("Retrieving user by ID: {}", id);
        Optional<User> userOpt = Optional.ofNullable(userHashMap.get(id));
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            logger.info("User found for ID: {}", id);
            return UserDetailsUtil.toResponse(user);
        } else {
            logger.warn("User not found for ID: {}", id);
            throw new UserNotFoundException("User not found with ID: " + id);
        }
    }

    @Override
    public UserDetailsResponse createUser(UserDetailsRequest userDetailsRequest) {
        logger.info("Creating a new user");
        User user = UserDetailsUtil.toCreate(userDetailsRequest);
        int id = SequenceGeneratorUtil.getIdSequence();
        user.setId(id);
        userHashMap.put(id, user);
        logger.info("User created with ID: {}", id);
        return UserDetailsUtil.toResponse(user);
    }

    @Override
    public List<User> findByNameContaining(String letter) {
        logger.info("Finding users by name containing: {}", letter);
        List<User> result = new ArrayList<>();
        for (User user : userHashMap.values()) {
            if (user.getName().contains(letter)) {
                result.add(user);
            }
        }
        logger.info("Found {} users matching the search criteria", result.size());
        return result;
    }

    @Override
    public List<User> findByName(String name) {
        logger.info("Finding users by name: {}", name);
        List<User> result = new ArrayList<>();

        for (User user : userHashMap.values()) {
            if (user.getName().equalsIgnoreCase(name)) {
                result.add(user);
            }
        }
        logger.info("Found {} users matching the name: {}", result.size(), name);
        return result;
    }

    @Override
    public UserDetailsResponse updateUser(int id, UserDetailsRequest userDetailsRequest) {
        logger.info("Updating user with ID: {}", id);
        Optional<User> optionalUser = Optional.ofNullable(userHashMap.get(id));
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setName(userDetailsRequest.getName());
            user.setEmail(userDetailsRequest.getEmail());
            user.setDob(userDetailsRequest.getDob());
            logger.info("User updated successfully with ID: {}", id);
            return UserDetailsUtil.toResponse(user);
        }
        logger.warn("User not found with ID: {}", id);
        throw new UserNotFoundException("User not found with ID: " + id);
    }

    @Override
    public void deleteUser(int id) {
        logger.info("Deleting user with ID: {}", id);
        Optional<User> optionalUser = Optional.ofNullable(userHashMap.get(id));
        if (optionalUser.isPresent()) {
            userHashMap.remove(id);
            logger.info("User deleted successfully with ID: {}", id);
        } else {
            logger.info("User is not present with ID: {}", id);
            throw new UserNotFoundException("User not found with ID: " + id);
        }
    }
}
