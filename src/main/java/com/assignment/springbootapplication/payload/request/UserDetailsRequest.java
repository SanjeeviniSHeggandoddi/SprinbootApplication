package com.assignment.springbootapplication.payload.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.util.Date;

@Data
public class UserDetailsRequest {
    @JsonProperty(value = "first_name")
    String name;
    @JsonProperty(value = "email_address")
    String email;
    @JsonProperty(value = "date_of_birth")
    Date dob;
}
