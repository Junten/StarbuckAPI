package com.sjsu.project.cmpe202.service;

import com.sjsu.project.cmpe202.model.Card;
import com.sjsu.project.cmpe202.model.User;

import java.util.List;

public interface UserService {
    User findByUsername(String username);

//    List<Card> findCardByUser(User user);
    void save(Card card);
}
