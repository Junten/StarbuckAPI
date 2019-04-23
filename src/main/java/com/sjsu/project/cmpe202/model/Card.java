package com.sjsu.project.cmpe202.model;

import javax.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "cards")
public class Card implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "card_number", length = 9, nullable = false)
    private String cardNumber;

    @Column(name = "card_code", length = 3, nullable = false)
    private String cardCode;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "userID")
    private User user;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCardCode() {
        return cardCode;
    }

    public void setCardCode(String cardCode) {
        this.cardCode = cardCode;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
