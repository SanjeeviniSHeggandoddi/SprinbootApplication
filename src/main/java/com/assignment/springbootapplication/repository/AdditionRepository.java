package com.assignment.springbootapplication.repository;

import com.assignment.springbootapplication.entity.AdditionResult;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdditionRepository extends JpaRepository<AdditionResult, Long> {
}
