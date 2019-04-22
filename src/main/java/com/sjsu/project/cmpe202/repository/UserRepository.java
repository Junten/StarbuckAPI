package com.sjsu.project.cmpe202.repository;

import com.sjsu.project.cmpe202.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {
    void deleteByUsername(String username);

    User findByUsername(String username);
}