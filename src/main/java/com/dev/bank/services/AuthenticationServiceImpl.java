package com.dev.bank.services;

import com.dev.bank.dao.client.UserDao;
import com.dev.bank.models.dao.User;
import com.dev.bank.models.request.AuthLoginRequest;
import com.dev.bank.models.request.AuthRegisterRequest;
import com.dev.bank.models.response.AuthLoginResponse;
import com.dev.bank.models.response.AuthRegisterResponse;
import com.dev.bank.services.client.AuthenticationService;
import com.dev.bank.validators.AuthValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZonedDateTime;
import java.util.List;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public AuthLoginResponse login(AuthLoginRequest request) {
        AuthLoginResponse response = new AuthLoginResponse();

        String email = request.getEmail();
        String password = request.getPassword();

        final String emailValidationResult = AuthValidator.validateEmail(email);
        final String passwordValidationResult = AuthValidator.validatePassword(password);
        if (emailValidationResult != null) {
            System.out.println("Validation error: " + emailValidationResult);

            response.setSuccess(false);
            response.setMessage(emailValidationResult);

            return response;
        }

        if (passwordValidationResult != null) {
            System.out.println("Validation error: " + passwordValidationResult);

            response.setSuccess(false);
            response.setMessage(passwordValidationResult);

            return response;
        }

        User user = userDao.getUserByEmail(email);
        if (user == null || !passwordEncoder.matches(password, user.getPassword())) {
            response.setSuccess(false);
            response.setMessage("Wrong credentials");

            return response;
        }

        response.setSuccess(true);
        return response;
    }

    @Override
    public AuthRegisterResponse register(AuthRegisterRequest request) {
        User newUser = new User();
        newUser.setEmail(request.getEmail());
        newUser.setFirstName(request.getFirstName());
        newUser.setLastName(request.getLastName());
        newUser.setPassword(passwordEncoder.encode(request.getPassword()));
        newUser.setPhoneNumber(request.getPhoneNumber());
        newUser.setAge(calculateAge(request.getBirthday()));

        User createdUser = userDao.saveUser(newUser);

        AuthRegisterResponse response = new AuthRegisterResponse();
        response.setUserId(createdUser != null ? createdUser.getId() : null);
        return response;
    }

    private Integer calculateAge(LocalDate birthdayDate) {
        Period period = Period.between(birthdayDate, ZonedDateTime.now().toLocalDate());

        return period.getYears();
    }
}
