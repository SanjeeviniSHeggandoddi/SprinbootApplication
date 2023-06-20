package com.assignment.springbootapplication.payload.response;

import lombok.Data;

import java.util.Date;

@Data
public class UserDetailsResponse {

    private int id;
    private String name;
    private String email;
    private Date dob;
}
