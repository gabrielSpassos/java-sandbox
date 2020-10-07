package com.gabrielspassos.poc.service;

import org.apache.commons.lang3.StringUtils;

public class CardService {

    private static final String PAN_BLOCK_PREFIX = "0000";
    private static final String CARD_TRAIL_SEPARATOR = "=";
    private static final Integer PIN_BLOCK_LENGTH = 16;
    private static final Integer PREFIX_LENGTH = 2;
    private static final String PIN_BLOCK_FILLER = "F";
    private static final String PREFIX_FILLER = "0";

    public static String createPanBlock(String cardTrailOrNumber) {
        String cardNumber = getNumberFromTrail(cardTrailOrNumber);

        String cardNumberWithoutStart = cardNumber.substring(3);
        String cardNumberWithoutStartAndEnd = cardNumberWithoutStart.substring(0, cardNumberWithoutStart.length() - 1);

        return PAN_BLOCK_PREFIX.concat(cardNumberWithoutStartAndEnd);
    }

    public static String createPinBlock(String cardPassword) {
        String prefix = createPinBlockPrefix(cardPassword);

        String cardPasswordWithPrefix = prefix.concat(cardPassword);

        return StringUtils.rightPad(cardPasswordWithPrefix, PIN_BLOCK_LENGTH, PIN_BLOCK_FILLER);
    }

    private static String getNumberFromTrail(String cardTrailOrNumber) {
        if (!cardTrailOrNumber.contains(CARD_TRAIL_SEPARATOR)) {
            return cardTrailOrNumber;
        }

        int indexOfSeparator = cardTrailOrNumber.indexOf(CARD_TRAIL_SEPARATOR);

        return cardTrailOrNumber.substring(0, indexOfSeparator);
    }

    private static String createPinBlockPrefix(String cardPassword) {
        String cardPasswordLength = String.valueOf(cardPassword.length());
        return StringUtils.leftPad(cardPasswordLength, PREFIX_LENGTH, PREFIX_FILLER);
    }
}
