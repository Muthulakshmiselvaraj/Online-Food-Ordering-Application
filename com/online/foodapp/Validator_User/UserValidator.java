package com.online.foodapp.Validator_User;

import java.util.HashMap;
import java.util.Map;

public class UserValidator {

    private static final Map<String, Validator> validators = new HashMap<>();

    static {
        validators.put("name", new NameValidator());
        validators.put("email", new EmailValidator());
        validators.put("password", new PasswordValidator());
        validators.put("address", new AddressValidator());
        validators.put("phoneNumber", new PhoneNumberValidator());
    }

    public static boolean validateInput(String fieldName, String input) {
        Validator validator = validators.get(fieldName);
        if (validator == null) {
            System.out.println("No validator found for field: " + fieldName);
        }
        if (!validator.isValid(input)) {
            System.out.println(validator.getErrorMessage());
            return false;
        }
        return true;
    }
}
