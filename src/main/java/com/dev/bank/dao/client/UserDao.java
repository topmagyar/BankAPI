package com.dev.bank.dao.client;

import com.dev.bank.models.dao.User;

import java.util.List;

public interface UserDao {
    List<User> findAllUsers();
    User getUserByEmail(String email);
    User saveUser(User user);
}
