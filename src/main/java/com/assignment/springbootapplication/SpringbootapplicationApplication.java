package com.assignment.springbootapplication;

import com.assignment.springbootapplication.entity.User;
import com.assignment.springbootapplication.entity.UserBuilder;
import com.assignment.springbootapplication.entity.UserDetailsSingleton;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringbootapplicationApplication{
	public static void main(String[] args) {
		SpringApplication.run(SpringbootapplicationApplication.class, args);
		UserDetailsSingleton singleton1 = UserDetailsSingleton.getInstance();
		UserDetailsSingleton singleton2 = UserDetailsSingleton.getInstance();
		if (singleton1 == singleton2) {
			System.out.println("Both the objects are the same instance.");//main changes
		}

		User userDetails3builder=new UserBuilder().setId(1).setEmail("sanju123").setName("sanju").getUser();
		System.out.println(userDetails3builder);
	}
}
