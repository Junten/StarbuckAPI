package com.sjsu.project.cmpe202.controller;

import com.sjsu.project.cmpe202.model.User;
import com.sjsu.project.cmpe202.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class UserController {
    @Autowired
    UserRepository userRepository;

    @ResponseBody
    @RequestMapping(
            value = "/get_user_by_username",
            method = RequestMethod.POST,
            consumes = "application/json")
    public User getUser(@RequestBody Map<String, String> username) {
        return userRepository.findByUsername(username.get("username"));
    }
}
