package com.dev.bank.models.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDate;

public class AuthRegisterRequest {
    @NotBlank(message = "Username is required")
    @Size(min = 4, max = 20, message = "User length should be between 4 and 20")
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    @Pattern(regexp = "^\\+?[0-9]{10,12}$", message = "phoneNumber should contains digits (10-12) and could start with +")
    private String phoneNumber;
    @NotBlank(message = "Email is required")
    @Email(message = "Email must be valid")
    private String email;
    private LocalDate birthday;

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public LocalDate getBirthday() {
        return birthday;
    }
}
