package com.gabrielspassos.poc.service;

import com.gabrielspassos.poc.crypt.Keys;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AuthorizationServiceTest {

    private final String CARD_TRAIL = "5115881573557547=00000000000000000000";
    private final String CARD_PASSWORD = "1020";
    private final String PIN_PAN_BLOCK = "D9B9DF424BB9E936";
    private final String ENCRYPTED_PAN_BLOCK = "1571B97D63865531AAC154EB94862377";

    @Test
    public void shouldCreateEncryptedPanBlock() {
        String encryptedPanBlock = AuthorizationService.createEncryptedPanBlock(CARD_TRAIL, Keys.TRIPLE_DES_COMPOSED_KEY);

        assertEquals(ENCRYPTED_PAN_BLOCK, encryptedPanBlock);
    }

    @Test
    public void shouldCreatePinPanBlock() {
        String pinPanBlock = AuthorizationService.createPinPanBlock(CARD_TRAIL, CARD_PASSWORD, Keys.TRIPLE_DES_COMPOSED_KEY);

        assertEquals(PIN_PAN_BLOCK, pinPanBlock);
    }

}