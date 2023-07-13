package com.assignment.springbootapplication.payload.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.util.Date;

@Data
public class UserDetailsResponse {

    @JsonProperty(value = "userId")
    private int id;

    @JsonProperty(value = "firstName")
    private String name;

    @JsonProperty(value = "emailAddress")
    private String email;

    @JsonProperty(value = "dateOfBirth")
    private Date dob;
}
