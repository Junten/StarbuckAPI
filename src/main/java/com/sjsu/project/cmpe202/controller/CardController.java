package com.sjsu.project.cmpe202.controller;

import com.sjsu.project.cmpe202.model.Card;
import com.sjsu.project.cmpe202.model.User;
import com.sjsu.project.cmpe202.repository.CardRepository;
import com.sjsu.project.cmpe202.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class CardController {
    @Autowired
    UserRepository userRepository;

    @Autowired
    CardRepository cardRepository;

    @RequestMapping(
            value = "/add_card/get_cards_by_user_id",
            method = RequestMethod.POST,
            consumes = "application/json")
    public List<Card> getCardsByUserId(@RequestBody Map<String, Integer> userId) {
        return cardRepository.findCardsByUser(userId.get("user_id"));
    }

    @RequestMapping(
            value = "/add_card/get_cards_by_username",
            method = RequestMethod.POST,
            consumes = "application/json")
    public List<Card> getCardsByUsername(@RequestBody Map<String, String> username) {
        User user = userRepository.findByUsername(username.get("username"));
        return cardRepository.findCardsByUser(user.getId());
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @RequestMapping(
            value = "/add_card/add_new_card",
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
            value = "/add_card/get_card_by_card_id",
            method = RequestMethod.POST,
            consumes = "application/json")
    public Card getCardByCardId(@RequestBody Map<String, Integer> cardId) {
        return cardRepository.findCardById(cardId.get("card_id"));
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @RequestMapping(
            value = "/delete_card_by_card_id",
            method = RequestMethod.DELETE,
            consumes = "application/json")
    public HttpStatus deleteCardByCardId(@RequestBody Map<String, Integer> cardId) {
        cardRepository.deleteById(cardId.get("card_id"));
        return HttpStatus.OK;
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @RequestMapping(
            value = "/update_card_by_card_number",
            method = RequestMethod.POST,
            consumes = "application/json")
    public HttpStatus updateCardByCardId(@RequestBody Map<String, String> cardNumber) {
        Double balance = Double.parseDouble(cardNumber.get("balance"));
        Card card = cardRepository.findCardByCardNumber(cardNumber.get("card_number"));
        card.setBalance(balance);
        cardRepository.save(card);
        return HttpStatus.OK;
    }
}
