package com.sjsu.project.cmpe202.controller;


import java.util.*;

import com.sjsu.project.cmpe202.model.User;
import com.sjsu.project.cmpe202.model.Card;
import com.sjsu.project.cmpe202.repository.CardRepository;
import com.sjsu.project.cmpe202.repository.UserRepository;

import com.sjsu.project.cmpe202.service.UserService;
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

    @Autowired
    UserService userService;

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
    public List<Card> getCards(@RequestBody Map<String, Integer> userId) {
        List<Card> cards = cardRepository.findCardsByUser(userId.get("user_id"));
        return cards;
    }

    @RequestMapping(
            value = "/addCard",
            method = RequestMethod.POST,
            consumes = "application/json")
    public String addCard(@RequestBody Map<String, String> card) {
        User user = userRepository.findByUsername(card.get("username"));
        Double balance = Double.parseDouble(card.get("balance"));
        Card cardInstance = new Card(card.get("card_number"), card.get("card_code"), balance, user);
        cardRepository.save(cardInstance);
        return "Add card Done";
    }

    @RequestMapping(
            value = "/card",
            method = RequestMethod.POST,
            consumes = "application/json")
    public Card getCard(@RequestBody Map<String, Integer> cardId) {
        return cardRepository.findCardsById(cardId.get("card_id"));
    }





}
