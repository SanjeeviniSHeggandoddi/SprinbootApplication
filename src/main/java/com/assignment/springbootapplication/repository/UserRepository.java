package com.assignment.springbootapplication.repository;

import com.assignment.springbootapplication.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
     @Query(value = "SELECT * FROM userdetails WHERE name LIKE '%:letter%'", nativeQuery = true)
   // @Query(value = "SELECT * FROM userdetails WHERE INSTR(name, :letter) > 0", nativeQuery = true)//check the query replace with like
    Optional<List<User>> findByNameContaining(@Param("letter") String letter);

    @Query("SELECT u FROM User u WHERE u.name = :name")
    Optional<List<User>> findByName(String name);
}
