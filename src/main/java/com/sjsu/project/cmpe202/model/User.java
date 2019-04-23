package com.sjsu.project.cmpe202.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "users")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "username", length = 36, nullable = false)
    private String username;

    @Column(name = "pin", length = 4, nullable = false)
    private String pin;

//    @OneToMany(mappedBy = "user")
//    private List<Card> cardList;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return pin;
    }

    public void setPassword(String pin) {
        this.pin = pin;
    }

//    public List<Card> getCardList() {
//        return cardList;
//    }
//
//    public void setCardList(List<Card> cardList) {
//        this.cardList = cardList;
//    }
}
