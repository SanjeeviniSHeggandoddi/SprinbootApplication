package com.assignment.springbootapplication.service;

import com.assignment.springbootapplication.entity.User;
import com.assignment.springbootapplication.exception.UserNotFoundException;
import com.assignment.springbootapplication.payload.request.UserDetailsRequest;
import com.assignment.springbootapplication.payload.response.UserDetailsResponse;
import com.assignment.springbootapplication.procedure.StoredProcedureService;
import com.assignment.springbootapplication.repository.UserRepository;
import com.assignment.springbootapplication.util.UserDetailsUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private static final Logger logger = LogManager.getLogger(UserServiceImpl.class);
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetailsResponse getUserById(int id) {
        logger.info("Retrieving user by ID: {}", id);

        Optional<User> user = Optional.ofNullable(userRepository.findById(id).orElse(null));
        if (!user.isPresent()) {
            logger.warn("User not found with id: {}", id);
            throw new UserNotFoundException("User not found with id: " + id);
        }
        UserDetailsResponse userDetailsResponse = UserDetailsUtil.toResponse(user);
        logger.info("User details retrieved successfully: {}", userDetailsResponse);
        return userDetailsResponse;
    }

    @Override
    public UserDetailsResponse createUser(UserDetailsRequest userDetailsRequest) {
        logger.info("Creating user: {}", userDetailsRequest);
        User userDetails = UserDetailsUtil.toCreate(userDetailsRequest);
        User savedUser = userRepository.save(userDetails);
        UserDetailsResponse userDetailsResponse = UserDetailsUtil.toResponse(savedUser);
        logger.info("User created successfully: {}", userDetailsResponse);
        return userDetailsResponse;
    }

    @Override
    public List<User> findByNameContaining(String letter) {
        logger.info("Finding users by name containing letter: {}", letter);
        Optional<List<User>> usersOpt = userRepository.findByNameContaining(letter);
        if (usersOpt.isPresent()) {
            List<User> users = usersOpt.get();
            logger.info("Found {} users matching the search criteria", users.size());
            return users;}else{
                logger.warn("No users found for the letter: {}", letter);
                throw new UserNotFoundException("No users found");
            }
        }

    @Override
    public List<User> findByName(String name) {
        logger.info("Finding users by name: {}", name);

        Optional<List<User>> usersOpt = userRepository.findByName(name);
        if (usersOpt.isPresent()) {
            List<User> users = usersOpt.get();
            logger.info("Found {} users matching the name: {}", users.size(), name);
            return users;
        } else {
            logger.warn("No users found for the name: {}", name);
            throw new UserNotFoundException("No users found");
        }
    }

    @Override
    public UserDetailsResponse updateUser(int id, UserDetailsRequest userDetailsRequest) {
        logger.info("Updating user details for ID: {}", id);
        Optional<User> userDetailsOpt = userRepository.findById(id);
        if (userDetailsOpt.isPresent()) {
            User userDetails = userDetailsOpt.get();
            userDetails.setName(userDetailsRequest.getName());
            userDetails.setEmail(userDetailsRequest.getEmail());
            userDetails.setDob(userDetailsRequest.getDob());
            User updatedUser = userRepository.save(userDetails);
            UserDetailsResponse userDetailsResponse = UserDetailsUtil.toResponse(updatedUser);
            logger.info("User details updated successfully for ID: {}", id);
            return userDetailsResponse;
        } else {
            logger.warn("User not found with ID: {}", id);
            throw new UserNotFoundException("User not found with ID: " + id);
        }
    }

    @Override
    public void deleteUser(int id) {
        logger.info("Deleting user with ID: {}", id);
        Optional<User> userDetailsOpt = userRepository.findById(id);
        if (userDetailsOpt.isPresent()) {
            userRepository.delete(userDetailsOpt.get());
            logger.info("User deleted successfully with ID: {}", id);
        } else {
            logger.warn("User not found with ID: {}", id);
            throw new UserNotFoundException("User not found with ID: " + id);
        }
    }
}

