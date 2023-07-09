package com.assignment.springbootapplication.payload.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.util.Date;

@Data
public class UserDetailsResponse {
    @JsonProperty(value = "user_id")
    private int id;
    @JsonProperty(value = "first_name")
    private String name;
    @JsonProperty(value = "email_address")
    private String email;
    @JsonProperty(value = "date_of_birth")
    private Date dob;
}
