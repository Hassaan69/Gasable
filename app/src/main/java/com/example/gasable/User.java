package com.example.gasable;

public class User {
    String id;
    String username;
    String name;
    String email;
    String password;
    String mobilenumber;


    public User() {
    }

    public User(String id ,String name ,String username, String email, String password, String mobilenumber) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.mobilenumber = mobilenumber;
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
