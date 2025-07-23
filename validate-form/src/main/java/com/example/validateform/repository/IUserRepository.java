package com.example.validateform.repository;

import com.example.validateform.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<User, Integer> {
    boolean existsByEmail(String email);
    User findByPhoneNumber(String phoneNumber);
}
