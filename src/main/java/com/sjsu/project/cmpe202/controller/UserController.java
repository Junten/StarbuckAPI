package com.sjsu.project.cmpe202.controller;

import com.sjsu.project.cmpe202.model.User;
import com.sjsu.project.cmpe202.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class UserController {
    @Autowired
    UserRepository userRepository;

    @ResponseBody
    @RequestMapping(
            value = "/user/get_user_by_username",
            method = RequestMethod.POST,
            consumes = "application/json")
    public User getUser(@RequestBody Map<String, String> username) {
        return userRepository.findByUsername(username.get("username"));
    }


    @ResponseBody
    @RequestMapping(
            value = "/user/add_new_user",
            method = RequestMethod.POST,
            consumes = "application/json")
    public HttpStatus addUser(@RequestBody Map<String, String> user) {
        User newUser = new User();
        newUser.setUsername(user.get("username"));
        newUser.setPin(user.get("pin"));
        newUser.setFirstName(user.get("first_name"));
        newUser.setLastName("last_name");
        newUser.setEmail("email");
        userRepository.save(newUser);
        return HttpStatus.OK;
    }
}
