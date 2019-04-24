package com.sjsu.project.cmpe202.model;

import javax.persistence.*;


@Entity
@Table(name = "cards")
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "card_number", length = 9, nullable = false)
    private String cardNumber;

    @Column(name = "card_code", length = 3, nullable = false)
    private String cardCode;

    @Column(name = "balance", nullable = false)
    private Double balance;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name= "user_id", nullable = false)
    private User user;

    public Card(String cardNumber, String cardCode, Double balance, User user) {
        this.cardNumber = cardNumber;
        this.cardCode = cardCode;
        this.balance = balance;
        this.user = user;
    }

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

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


}
