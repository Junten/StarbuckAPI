package com.sjsu.project.cmpe202.repository;

import com.sjsu.project.cmpe202.model.Card;
import com.sjsu.project.cmpe202.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CardRepository extends CrudRepository<Card, Integer> {
    List<Card> findCardsByUser(User user);

    Card findCardsByCardNumber(String cardNumber);
}
