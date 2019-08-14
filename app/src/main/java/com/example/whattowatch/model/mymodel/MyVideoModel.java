package com.example.whattowatch.model.mymodel;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class MyVideoModel {

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @PrimaryKey
    private Integer id;
    private String name;
    private String key;

    public MyVideoModel(String name, String key) {
        this.name = name;
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
