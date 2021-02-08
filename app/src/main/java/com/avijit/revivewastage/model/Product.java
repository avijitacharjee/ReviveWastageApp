package com.avijit.revivewastage.model;

import java.util.ArrayList;
import java.util.List;


public class Product {
    private String id;
    private String name;
    private String quantity;
    private String price;
    private String details;
    private String image;
    private String category_id;
    private String approved_bid;

    public String getApproved_bid() {
        return approved_bid;
    }

    public void setApproved_bid(String approved_bid) {
        this.approved_bid = approved_bid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCategory_id() {
        return category_id;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }

}
