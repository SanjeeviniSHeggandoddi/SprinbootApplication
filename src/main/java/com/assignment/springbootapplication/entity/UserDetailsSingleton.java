package com.assignment.springbootapplication.entity;

public class UserDetailsSingleton {
    private static UserDetailsSingleton userDetails;

    private UserDetailsSingleton() {
    }

    public static synchronized UserDetailsSingleton getInstance() {
        if (userDetails == null) {
            userDetails = new UserDetailsSingleton();
        }
        return userDetails;
    }
}
