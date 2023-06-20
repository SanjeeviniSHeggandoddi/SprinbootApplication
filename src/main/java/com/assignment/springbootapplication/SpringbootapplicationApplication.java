package com.assignment.springbootapplication;

import com.assignment.springbootapplication.entity.User;
import com.assignment.springbootapplication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringbootapplicationApplication{

	public static void main(String[] args) {

		SpringApplication.run(SpringbootapplicationApplication.class, args);
	}
}
