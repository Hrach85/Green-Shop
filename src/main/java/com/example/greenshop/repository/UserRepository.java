package com.example.greenshop.repository;

import com.example.greenshop.entity.User;
import com.example.greenshop.security.CurrentUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);
}
