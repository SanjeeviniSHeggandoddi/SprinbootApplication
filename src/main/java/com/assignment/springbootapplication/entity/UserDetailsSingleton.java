package com.assignment.springbootapplication.entity;

public class UserDetailsSingleton {
    private static User userDetails;
    private UserDetailsSingleton() {
    }
    public static synchronized User getInstance() {
        if (userDetails == null) {
            userDetails = new User();
        }
        return userDetails;
    }

}
