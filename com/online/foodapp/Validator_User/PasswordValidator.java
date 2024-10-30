package com.online.foodapp.Validator_User;

public class PasswordValidator implements Validator {
    public boolean isValid(String input) {
        return input.matches("^(?=.*[!@#$%^&*])[A-Za-z0-9!@#$%^&*]{6,8}$");
    }
}

