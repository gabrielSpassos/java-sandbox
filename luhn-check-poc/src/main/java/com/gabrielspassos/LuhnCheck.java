package com.gabrielspassos;

public class LuhnCheck {

    public static Boolean isValid(String cardNumber) {
        if (cardNumber == null || cardNumber.isEmpty() || cardNumber.trim().isEmpty()) {
            return false;
        }

        int sumOfDigits = 0;
        String lastDigit = cardNumber.substring(cardNumber.length() - 1);
        String cardNumberWithoutLastDigit = cardNumber.substring(0, cardNumber.length() - 1);

        boolean shouldDouble = true;
        for (int i = cardNumberWithoutLastDigit.length() - 1; i >= 0; i--) {
            int digit = cardNumberWithoutLastDigit.charAt(i) - '0';
            System.out.println(String.format("Char at %d: %c", i, digit));

            if (shouldDouble) {
                digit = digit * 2;
            }

            int finalDigit = digit > 9 ? digit - 9 : digit;
            sumOfDigits += finalDigit;

            shouldDouble = !shouldDouble;
        }

        System.out.println(String.format("Sum of digits: %d", sumOfDigits));
        int checkDigit = (10 - (sumOfDigits % 10)) % 10;
        System.out.println(String.format("Check digit: %d", checkDigit));
        String checkDigitAsString = String.valueOf(checkDigit);

        return lastDigit.equals(checkDigitAsString);
    }
}
