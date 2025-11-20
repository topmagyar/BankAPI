package com.dev.bank.dao;

import com.dev.bank.dao.client.UserDao;
import com.dev.bank.models.dao.User;
import com.dev.bank.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserDaoImpl implements UserDao {

    @Autowired
    private UserRepository repository;

    @Override
    public List<User> findAllUsers() {
        return repository.findAll();
    }

    @Override
    public User getUserByEmail(String email) {
        return repository.findByEmail(email);
    }

    @Override
    public User saveUser(User user) {
        try {
            return repository.save(user);
        } catch (Exception e) {
            System.out.println("Error during user save process: " + e.getMessage());
            return null;
        }
    }
}
