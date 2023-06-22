package com.assignment.springbootapplication.entity;

import lombok.Data;
import javax.persistence.*;

@Entity
@Data
public class AdditionResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int num1;
    private int num2;
    private int result;
}
