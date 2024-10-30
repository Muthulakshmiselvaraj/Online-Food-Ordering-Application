package com.online.foodapp.Validator_User;

public class PhoneNumberValidator implements Validator {
    public boolean isValid(String input) {
        return input.matches("^[6-9][0-9]{9}$"); 
    }
}
