package com.assignment.springbootapplication.repository;

import com.assignment.springbootapplication.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    @Query(value = "SELECT * FROM userdetails WHERE name LIKE 's%'", nativeQuery = true)
    Optional<List<User>> usingNativegetUsersWithNameStartingWithS();

    @Query("SELECT u FROM User u WHERE u.name LIKE 's_n%'")
    Optional<List<User>> usingJpagetUsersWithNameStartingWithS();
}
