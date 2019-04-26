package com.sjsu.project.cmpe202.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "payments")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "total", nullable = false)
    private Double total;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name= "user_id", nullable = false)
    private User user;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name= "card_id", nullable = false)
    private Card card;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name= "order_id", nullable = false)
    private Card order;

    public int getId() {
        return id;
    }

    public Double getTotal() {
        return total;
    }

    public User getUser() {
        return user;
    }

    public Card getCard() {
        return card;
    }

    public Card getOrder() {
        return order;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public void setOrder(Card order) {
        this.order = order;
    }
}
