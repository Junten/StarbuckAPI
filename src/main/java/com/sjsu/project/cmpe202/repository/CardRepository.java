package com.sjsu.project.cmpe202.repository;

import com.sjsu.project.cmpe202.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.List;

public interface CardRepository extends JpaRepository<Card, Integer> {

    @Query("SELECT c.cardNumber, c.cardCode, c.balance FROM Card c WHERE c.user.id = :userId")
    List<Card> findCardsByUser(@Param("userId") Integer userId);

    Card findCardsById(Integer id);
}
