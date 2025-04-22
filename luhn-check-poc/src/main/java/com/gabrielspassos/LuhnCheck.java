package com.gabrielspassos;

public class LuhnCheck {

    public static Boolean isValid(String cardNumber) {
        if (cardNumber == null || cardNumber.isEmpty() || cardNumber.trim().isEmpty()) {
            return false;
        }

        return true;
    }
}
