package com.assignment.springbootapplication.payload.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.*;
import java.util.Date;

@Data
public class UserDetailsRequest {

    @Pattern(regexp = "[a-zA-Z]+", message = "Invalid Firstname")
    @NotBlank(message = "Username is required")
    @Size(max = 10, message = "Name must not exceed 10 characters")
    @JsonProperty("firstName")
    String name;

    @Email(message = "Invalid email.")
    @JsonProperty("emailAddress")
    String email;

    @NotNull(message = "Date of birth is a mandatory field")
    @JsonProperty("dateOfBirth")
    Date dob;
}
