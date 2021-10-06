package com.example.idealhome.Pojo;

import android.content.Context;

import java.io.Serializable;

public class User {
    private int id;
    private String email;
    private String username;
    private String password;
    private String mobilenumber;

    public User() {

    }
    public User(String name){
        this.username = name;
    }

    public User(int id, String email, String username, String password, String mobilenumber) {
        this.id = id;
        this.email = email;
        this.username = username;
        this.password = password;
        this.mobilenumber = mobilenumber;
    }

    public User(String email, String username, String password, String mobilenumber) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.mobilenumber = mobilenumber;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMobilenumber() {
        return mobilenumber;
    }

    public void setMobilenumber(String mobilenumber) {
        this.mobilenumber = mobilenumber;
    }
}
