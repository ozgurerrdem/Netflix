package com.example.netflix.Database.Models;

public class User {
    private String name;
    private String username;
    private String password;
    private String mail;

    public User(String name, String username, String password, String mail) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.mail = mail;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getMail() {
        return mail;
    }
}
