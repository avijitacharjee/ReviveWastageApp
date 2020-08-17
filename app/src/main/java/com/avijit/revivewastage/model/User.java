package com.avijit.revivewastage.model;

import com.google.gson.Gson;

/**
 * Created by Avijit Acharjee on 8/17/2020 at 4:17 PM.
 * Email: avijitach@gmail.com.
 */
public class User {
    private String id;
    private String name;
    private String email;
    private String phone;
    private String password;

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

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
