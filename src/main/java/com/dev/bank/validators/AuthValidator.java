package com.dev.bank.validators;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class AuthValidator {

    public static String validateUsername(String username){
        if(username == null || username.isEmpty()){
            return("Username can't be empty");
        }

        if(username.length() > 20){
            return("Username is too big");
        }

        for (int i = 0; i < username.length(); i++) {
            char c = username.charAt(i);
            if (!Character.isLetterOrDigit(c) && c != '_'){
                return("Username can only have letters, numbers and underscores");
            }
        }
        return null;
    }

    public static String validatePassword(String password){
        Boolean containsUppercaseLetter = false;
        Boolean containsLowerLetter = false;
        Boolean containsDigit = false;

        if (password == null || password.isEmpty()) {
            return("Password can't be empty");
        }

        if (password.length() < 8) {
            return("Password must be at least 8 characters long");
        }

        for (int i = 0; i < password.length(); i++) {
            char c = password.charAt(i);
            if (Character.isUpperCase(c)){
                containsUppercaseLetter = true;
            }
            else if (Character.isLowerCase(c)) {
                containsLowerLetter = true;
            }
            else if (Character.isDigit(c)) {
                containsDigit = true;
            }

            if (containsUppercaseLetter && containsLowerLetter && containsDigit) {
                break;
            }
        }

        if(!containsUppercaseLetter || !containsLowerLetter || !containsDigit){
            return("Password must contain at least one uppercase letter, one lowercase letter and one digit");
        }

        return null;
    }

    public static String validateEmail(String email){
        if (email == null || email.isEmpty()) {
            return "Email can't be empty";
        }

        String regex = "^[\\w._%+-]+@[\\w.-]+\\.[a-zA-Z]{2,}$";
        if (!email.matches(regex)) {
            return "Invalid email format";
        }

        return null;
    }

    public static String validateBirthday(String birthday){
        if (birthday == null || birthday.isEmpty()) {
            return "Birthday can't be empty";
        }

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            LocalDate date = LocalDate.parse(birthday, formatter);

            if (date.isAfter(LocalDate.now())) {
                return "Please provide your real birthday";
            }

            int age = LocalDate.now().getYear() - date.getYear();
            if (age > 100) {
                return "Please provide your real birthday";
            }

            return null;
        }
        catch (DateTimeParseException e) {
            return "Expected date format: dd-MM-yyyy";
        }
    }

    public static String validatePhoneNumber(String phoneNumber){
        String regex = "^(\\+380|380|0)\\d{9}$";
        if(phoneNumber != null && !phoneNumber.isEmpty()){
            if(!phoneNumber.matches(regex)){
                return("Expected phone number format: +380XXXXXXXXX, 380XXXXXXXXX or 0XXXXXXXXX");
            }
        }
        return null;
    }
}
