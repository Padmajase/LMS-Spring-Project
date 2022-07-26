package com.example.lms.repository;

import com.example.lms.model.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends  JpaRepository<UserDetails, Integer>{
    Optional<UserDetails> findByEmailId(String emailId);


    @Query("select userDetails from UserDetails userDetails where userDetails.emailId = :emailId ")
    List<UserDetails> findUserDetailsBy(@Param("emailId") String emailId);

}
