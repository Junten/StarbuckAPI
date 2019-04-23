package com.sjsu.project.cmpe202.Controller;

import com.sjsu.project.cmpe202.model.User;
import com.sjsu.project.cmpe202.model.Card;
import java.util.*;

import com.sjsu.project.cmpe202.repository.CardRepository;
import com.sjsu.project.cmpe202.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public class AppController {
    @Autowired
    UserRepository userRepository;

    @Autowired
    CardRepository cardRepository;

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public User getUser(@RequestBody String username) {
        return userRepository.findByUsername(username);
    }

    @RequestMapping(value = "cards", method = RequestMethod.POST)
    public List<Card> getCard(@RequestBody User User) {
        return cardRepository.findCardsByUser(User);
    }
}
