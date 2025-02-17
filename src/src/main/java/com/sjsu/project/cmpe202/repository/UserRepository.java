package com.sjsu.project.cmpe202.repository;

import com.sjsu.project.cmpe202.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String username);
}