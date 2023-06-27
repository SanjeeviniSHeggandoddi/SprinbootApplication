package com.assignment.springbootapplication.entity;

import java.util.Date;

public class UserBuilder {
    private int id;
    private String name;
    private String email;
    private Date dob;

    public UserBuilder setId(int id) {
        this.id = id;
        return this;
    }

    public UserBuilder setEmail(String email) {
        this.email = email;
        return this;
    }

    public UserBuilder setDob(Date dob) {
        this.dob = dob;
        return this;
    }

    public UserBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public User getUser() {
        return new User(id, name, email, dob);
    }
}
