package com.sjsu.project.cmpe202.service;

import com.sjsu.project.cmpe202.model.Card;
import com.sjsu.project.cmpe202.model.User;
import com.sjsu.project.cmpe202.repository.CardRepository;
import com.sjsu.project.cmpe202.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    CardRepository cardRepository;

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

//    @Override
//    public List<Card> findCardByUser(User user) {
//        List<Card> cardList = cardRepository.findCardsByUser(user);
//        return cardList;
//    }

    @Override
    public void save(Card card) {
        cardRepository.save(card);
    }
}
