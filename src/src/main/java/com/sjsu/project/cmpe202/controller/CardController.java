package com.sjsu.project.cmpe202.controller;

import com.sjsu.project.cmpe202.model.Card;
import com.sjsu.project.cmpe202.model.User;
import com.sjsu.project.cmpe202.repository.CardRepository;
import com.sjsu.project.cmpe202.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class CardController {
    @Autowired
    UserRepository userRepository;

    @Autowired
    CardRepository cardRepository;

    @RequestMapping(
            value = "/get_cards_by_user_id",
            method = RequestMethod.POST,
            consumes = "application/json")
    public List<Card> getCardsByUserId(@RequestBody Map<String, Integer> userId) {
        return cardRepository.findCardsByUser(userId.get("user_id"));
    }

    @RequestMapping(
            value = "/get_cards_by_username",
            method = RequestMethod.POST,
            consumes = "application/json")
    public List<Card> getCardsByUsername(@RequestBody Map<String, String> username) {
        User user = userRepository.findByUsername(username.get("username"));
        return cardRepository.findCardsByUser(user.getId());
    }

    @RequestMapping(
            value = "/add_card",
            method = RequestMethod.POST,
            consumes = "application/json")
    public HttpStatus addCard(@RequestBody Map<String, String> card) {
        User user = userRepository.findByUsername(card.get("username"));
        Double balance = Double.parseDouble(card.get("balance"));
        Card cardInstance = new Card(card.get("card_number"), card.get("card_code"), balance, user);
        cardRepository.save(cardInstance);
        return HttpStatus.OK;
    }

    @RequestMapping(
            value = "/get_card_by_card_id",
            method = RequestMethod.POST,
            consumes = "application/json")
    public Card getCardByCardId(@RequestBody Map<String, Integer> cardId) {
        return cardRepository.findCardsById(cardId.get("card_id"));
    }
}
