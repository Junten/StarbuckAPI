package com.sjsu.project.cmpe202.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "amount", nullable = false)
    private Double amount;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name= "user_id", nullable = false)
    private User user;

    @Column(name = "created_date")
    private LocalDate date;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "orders_items", joinColumns = @JoinColumn(name = "order_id"), inverseJoinColumns = @JoinColumn(name = "item_id"))
    private Set<Item> items;

    public Order() {

    }

    public int getId() {
        return id;
    }

    public Double getAmount() {
        return amount;
    }

    public User getUser() {
        return user;
    }

    public LocalDate getDate() {
        return date;
    }

    public Set<Item> getItems() {
        return items;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setItems(Set<Item> items) {
        this.items = items;
    }
}
