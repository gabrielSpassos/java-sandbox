package com.gabrielspassos.poc.service;

import com.gabrielspassos.poc.crypt.Keys;
import com.gabrielspassos.poc.enumerator.TripleDESMode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AuthorizationServiceTest {

    private final String CARD_TRAIL = "5115881573557547=00000000000000000000";
    private final String CARD_NUMBER = "5115881573557547";
    private final String CARD_PASSWORD = "1020";
    private final String ECB_PIN_PAN_BLOCK = "D9B9DF424BB9E936";
    private final String CBC_PIN_PAN_BLOCK = "D9B9DF424BB9E936";
    private final String ECB_ENCRYPTED_PAN = "17EC9C512B9744FC6B48C5DA004EDC44";
    private final String CBC_ENCRYPTED_PAN = "17EC9C512B9744FCB0DC0494EEEE87AA";

    @Test
    public void shouldCreateCBCEncryptedPanBlock() {
        String encryptedPanBlock = AuthorizationService
                .encryptPan(CARD_TRAIL, Keys.TRIPLE_DES_COMPOSED_KEY, TripleDESMode.CBC_NO_PADDING);

        assertEquals(CBC_ENCRYPTED_PAN, encryptedPanBlock);
    }

    @Test
    public void shouldCreateECBEncryptedPanBlock() {
        String encryptedPanBlock = AuthorizationService
                .encryptPan(CARD_TRAIL, Keys.TRIPLE_DES_COMPOSED_KEY, TripleDESMode.ECB_NO_PADDING);

        assertEquals(ECB_ENCRYPTED_PAN, encryptedPanBlock);
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

    @Test
    public void shouldDecryptPanWithCBC() {
        String decryptedPanBlock = AuthorizationService
                .decryptPanBlock(CBC_ENCRYPTED_PAN, Keys.TRIPLE_DES_COMPOSED_KEY, TripleDESMode.CBC_NO_PADDING);

        assertEquals(CARD_NUMBER, decryptedPanBlock);
    }

    @Test
    public void shouldDecryptPanWithECB() {
        String decryptedPanBlock = AuthorizationService
                .decryptPanBlock(ECB_ENCRYPTED_PAN, Keys.TRIPLE_DES_COMPOSED_KEY, TripleDESMode.ECB_NO_PADDING);

        assertEquals(CARD_NUMBER, decryptedPanBlock);
    }

    @Test
    public void shouldDecryptPinPanBlockWithCBC() {
        String decryptedPinPanBlock = AuthorizationService
                .decryptPinPanBlock(CBC_PIN_PAN_BLOCK, CARD_TRAIL, Keys.TRIPLE_DES_COMPOSED_KEY, TripleDESMode.CBC_NO_PADDING);

        assertEquals(CARD_PASSWORD, decryptedPinPanBlock);
    }

    @Test
    public void shouldDecryptPinPanBlockWithECB() {
        String decryptedPinPanBlock = AuthorizationService
                .decryptPinPanBlock(ECB_PIN_PAN_BLOCK, CARD_TRAIL, Keys.TRIPLE_DES_COMPOSED_KEY, TripleDESMode.ECB_NO_PADDING);

        assertEquals(CARD_PASSWORD, decryptedPinPanBlock);
    }

}