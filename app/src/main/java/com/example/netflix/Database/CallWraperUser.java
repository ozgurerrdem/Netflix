package com.example.netflix.Database;

import com.example.netflix.Database.Models.User;

import java.util.List;

public interface CallWraperUser {
    void readUserDataCallback(List<User> users);
}
