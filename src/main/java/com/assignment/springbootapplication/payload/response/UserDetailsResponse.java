package com.assignment.springbootapplication.payload.response;

import lombok.Data;

@Data
public class UserDetailsResponse {

    private int id;
    private String name;
    private String email;
    private String dob;
}
