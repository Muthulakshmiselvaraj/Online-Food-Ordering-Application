package com.online.foodapp.Validator_User;

public class AddressValidator implements Validator {
    public boolean isValid(String input) {
        return input.matches("^[A-Za-z0-9#,/\\s]+$");

    }
}
