package com.sjsu.project.cmpe202.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "items")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "product", nullable = false)
    private String product;

    @Column(name = "image_url")
    private String imageUrl;

    public Item() {

    }

    public int getId() {
        return id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getProduct() {
        return product;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setProduct(String product) {
        this.product = product;
    }
}
