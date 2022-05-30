package com.example.mad21_practical_2___android_activity_ngrayzin;

import android.icu.lang.UProperty;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class User {

    public String name;
    public String description;
    public Integer id;
    public Boolean followed;
    public User(){};
    public User(String n, String d, Integer id, Boolean f){
        name = n;
        description = d;
        this.id = id;
        followed = f;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }
    public void setId(Integer id){
        this.id = id;
    }
    public Integer getId(){
        return this.id;
    }
    public void setDescription(String description){
        this.description = description;
    }
    public String getDescription(){
        return this.description;
    }
    public void setFollowed(Boolean followed){
        this.followed = followed;
    }
    public Boolean getFollowed(){
        return this.followed;
    }
}


