package com.assignment.springbootapplication.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "userdetails")
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(value = "user_id")
    private int id;
    @JsonProperty(value = "first_name")
    private String name;
    @JsonProperty(value = "email_address")
    private String email;
    @JsonProperty(value = "date_of_birth")
    private Date dob;
}
