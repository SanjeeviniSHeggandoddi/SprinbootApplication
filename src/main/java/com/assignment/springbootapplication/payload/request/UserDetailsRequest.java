package com.assignment.springbootapplication.payload.request;

import lombok.Data;
import java.util.Date;

@Data
public class UserDetailsRequest {

    String name;
    String email;
    Date dob;
}
