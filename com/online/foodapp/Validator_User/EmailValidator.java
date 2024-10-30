package com.online.foodapp.Validator_User;

public class EmailValidator implements Validator {
    
    public boolean isValid(String input) {
        return input.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.(com|org|net|edu|gov|mil|co|io)$");
    }
}
