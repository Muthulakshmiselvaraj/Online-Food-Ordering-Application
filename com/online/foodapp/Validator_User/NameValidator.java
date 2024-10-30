package com.online.foodapp.Validator_User;

public class NameValidator implements Validator {
    
    public boolean isValid(String input) {
        return input.matches("^[A-Za-z\\s]+$");
    }
  }
 

