package com.assignment.springbootapplication.service;

import com.assignment.springbootapplication.entity.User;
import com.assignment.springbootapplication.exception.UserNotFoundException;
import com.assignment.springbootapplication.payload.request.UserDetailsRequest;
import com.assignment.springbootapplication.payload.response.UserDetailsResponse;
import com.assignment.springbootapplication.util.SequenceGeneratorUtil;
import com.assignment.springbootapplication.util.UserDetailsUtil;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceMapImpl implements UserService {
    private HashMap<Integer, User> userHashMap = new HashMap<>();

    @Override
    public UserDetailsResponse getUserById(int id) {
        User user = userHashMap.get(id);
        return UserDetailsUtil.toResponse(user);
    }

    @Override
    public UserDetailsResponse createUser(UserDetailsRequest userDetailsRequest) {
        User user = UserDetailsUtil.toCreate(userDetailsRequest);

        int id = SequenceGeneratorUtil.getIdSequence();
        user.setId(id);
        userHashMap.put(id, user);
        return UserDetailsUtil.toResponse(user);
    }

    @Override
    public List<User> findByNameContaining(String letter) {
        List<User> result = new ArrayList<>();
        for (User user : userHashMap.values()) {
            if (user.getName().contains(letter)) {
                result.add(user);
            }
        }
        return result;
    }

    @Override
    public List<User> findByName(String name) {
        List<User> result = new ArrayList<>();

        for (User user : userHashMap.values()) {
            if (user.getName().equalsIgnoreCase(name)) {
                result.add(user);
            }
        }
        return result;
    }

    @Override
    public UserDetailsResponse updateUser(int id, UserDetailsRequest userDetailsRequest) {
        Optional<User> optionalUser = Optional.ofNullable(userHashMap.get(id));
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setName(userDetailsRequest.getName());
            user.setEmail(userDetailsRequest.getEmail());
            user.setDob(userDetailsRequest.getDob());

            return UserDetailsUtil.toResponse(user);

        }
        throw new UserNotFoundException("User not found with ID: " + id);
    }

    @Override
    public void deleteUser(int id) {
        userHashMap.remove(id);
    }
}
