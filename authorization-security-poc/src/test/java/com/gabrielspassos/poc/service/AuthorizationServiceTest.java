package com.gabrielspassos.poc.service;

import com.gabrielspassos.poc.crypt.Keys;
import com.gabrielspassos.poc.enumerator.TripleDESMode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AuthorizationServiceTest {

    private final String CARD_TRAIL = "5115881573557547=00000000000000000000";
    private final String CARD_PASSWORD = "1020";
    private final String ECB_PIN_PAN_BLOCK = "D9B9DF424BB9E936";
    private final String CBC_PIN_PAN_BLOCK = "D9B9DF424BB9E936";
    private final String ECB_ENCRYPTED_PAN_BLOCK = "1571B97D63865531AAC154EB94862377";
    private final String CBC_ENCRYPTED_PAN_BLOCK = "1571B97D6386553123ECB3FA8B62DAC6";

    @Test
    public void shouldCreateCBCEncryptedPanBlock() {
        String encryptedPanBlock = AuthorizationService
                .createEncryptedPanBlock(CARD_TRAIL, Keys.TRIPLE_DES_COMPOSED_KEY, TripleDESMode.CBC_NO_PADDING);

        assertEquals(CBC_ENCRYPTED_PAN_BLOCK, encryptedPanBlock);
    }

    @Test
    public void shouldCreateECBEncryptedPanBlock() {
        String encryptedPanBlock = AuthorizationService
                .createEncryptedPanBlock(CARD_TRAIL, Keys.TRIPLE_DES_COMPOSED_KEY, TripleDESMode.ECB_NO_PADDING);

        assertEquals(ECB_ENCRYPTED_PAN_BLOCK, encryptedPanBlock);
    }

    @Test
    public void shouldCreateCBCPinPanBlock() {
        String pinPanBlock = AuthorizationService
                .createPinPanBlock(CARD_TRAIL, CARD_PASSWORD, Keys.TRIPLE_DES_COMPOSED_KEY, TripleDESMode.CBC_NO_PADDING);

        assertEquals(CBC_PIN_PAN_BLOCK, pinPanBlock);
    }

    @Test
    public void shouldCreateECBPinPanBlock() {
        String pinPanBlock = AuthorizationService
                .createPinPanBlock(CARD_TRAIL, CARD_PASSWORD, Keys.TRIPLE_DES_COMPOSED_KEY, TripleDESMode.ECB_NO_PADDING);

        assertEquals(ECB_PIN_PAN_BLOCK, pinPanBlock);
    }

}