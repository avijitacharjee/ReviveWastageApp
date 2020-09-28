package com.avijit.revivewastage.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Avijit Acharjee on 9/10/2020 at 8:44 PM.
 * Email: avijitach@gmail.com.
 */
public class Bid {

    /**
     * id : 1
     * description : abcd
     * amount : 1000
     * product_id : 1
     * user_id : 1
     * name : adf
     * email : adf
     * phone : ad
     * password : wer
     * user_type : 1
     */

    private String id;
    private String description;
    private String amount;
    private String product_id;
    private String user_id;
    private String name;
    private String email;
    private String phone;
    private String password;
    private String user_type;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUser_type() {
        return user_type;
    }

    public void setUser_type(String user_type) {
        this.user_type = user_type;
    }
    public static List<Bid> filterByProductId(List<Bid> bidList,String id){
        List<Bid> result = new ArrayList<>();
        for (Bid bid: bidList) {
            if(bid.getProduct_id().equals(id)){
                result.add(bid);
            }
        }
        return result;
    }
}
