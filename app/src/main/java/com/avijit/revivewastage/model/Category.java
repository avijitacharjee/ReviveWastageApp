package com.avijit.revivewastage.model;

import com.google.gson.Gson;

/**
 * Created by Avijit Acharjee on 9/1/2020 at 5:57 PM.
 * Email: avijitach@gmail.com.
 */
public class Category {
    private String id;
    private String name;
    private String image;
    private String update;
    private String delete;

    public Category(String id, String name, String image) {
        this.id = id;
        this.name = name;
        this.image = image;
    }

    public Category() {
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getUpdate() {
        return update;
    }

    public void setUpdate(String update) {
        this.update = update;
    }

    public String getDelete() {
        return delete;
    }

    public void setDelete(String delete) {
        this.delete = delete;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
