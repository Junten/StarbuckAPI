package com.sjsu.project.cmpe202.Controller;


import java.util.*;

import com.sjsu.project.cmpe202.model.User;
import com.sjsu.project.cmpe202.model.Card;
import com.sjsu.project.cmpe202.repository.CardRepository;
import com.sjsu.project.cmpe202.repository.UserRepository;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@RestController
public class AppController {
    @Autowired
    UserRepository userRepository;

    @Autowired
    CardRepository cardRepository;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home(){
        return "Hello World!";
    }

    @ResponseBody
    @RequestMapping(
            value = "/user",
            method = RequestMethod.POST,
            consumes = "application/json")
    public User getUser(@RequestBody Map<String, String> username) {
        return userRepository.findByUsername(username.get("username"));
    }

    @RequestMapping(
            value = "/cards",
            method = RequestMethod.POST,
            consumes = "application/json")
    public List<Card> getCard(@RequestBody User user) {
        Card card = new Card();
        card.setUser(user);
        return cardRepository.findCardsByUser(user);
    }

}
