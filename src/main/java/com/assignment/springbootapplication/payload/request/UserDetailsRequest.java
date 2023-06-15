package com.assignment.springbootapplication.payload.request;

import lombok.Data;

@Data
public class UserDetailsRequest {

    String name;
    String email;
    String dob;
}
