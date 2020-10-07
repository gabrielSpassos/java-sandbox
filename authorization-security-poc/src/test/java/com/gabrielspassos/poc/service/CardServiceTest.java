package com.gabrielspassos.poc.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CardServiceTest {

    private final String CARD_TRAIL = "5158952551367981=000000000000";
    private final String CARD_NUMBER = "5158952551367981";
    private final String PIN_BLOCK = "0000895255136798";
    private final String FOUR_DIGITS_PASSWORD = "1020";
    private final String FOUR_DIGITS_PASSWORD_PIN_BLOCK = "041020FFFFFFFFFF";
    private final String SIX_DIGITS_PASSWORD = "102030";
    private final String SIX_DIGITS_PASSWORD_PIN_BLOCK = "06102030FFFFFFFF";

    @Test
    public void shouldReturnPanBlock() {
        String panBlock = CardService.createPanBlock(CARD_TRAIL);

        assertEquals(PIN_BLOCK, panBlock);
    }

    @Test
    public void shouldReturnPanBlockWithCardNumber() {
        String panBlock = CardService.createPanBlock(CARD_NUMBER);

        assertEquals(PIN_BLOCK, panBlock);
    }

    @Test
    public void shouldReturnPinBlockForFourDigits() {
        String pinBlock = CardService.createPinBlock(FOUR_DIGITS_PASSWORD);

        assertEquals(FOUR_DIGITS_PASSWORD_PIN_BLOCK, pinBlock);
    }

    @Test
    public void shouldReturnPinBlockForSixDigits() {
        String pinBlock = CardService.createPinBlock(SIX_DIGITS_PASSWORD);

        assertEquals(SIX_DIGITS_PASSWORD_PIN_BLOCK, pinBlock);
    }

}