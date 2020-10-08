package com.gabrielspassos.poc.crypt;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class CryptUtilsTest {

    private final byte[] HEX_STRING_AS_BYTES = new byte[]{4, 18, 52, -1, -1, -1, -1, -1};
    private final String HEX_STRING = "041234FFFFFFFFFF";
    private final byte[] TEXT_AS_BYTES = new byte[]{53, 49, 49, 53, 56, 56, 49, 53, 55, 51, 53, 53, 55, 53, 52, 55};
    private final String TEXT = "5115881573557547";

    @Test
    public void shouldReturnByteArrayFromHexString() {
        byte[] bytes = CryptUtils.hexStringToByteArray(HEX_STRING);

        assertTrue(Arrays.equals(HEX_STRING_AS_BYTES, bytes));
    }

    @Test
    public void shouldReturnHexStringFromByteArray() {
        String hexString = CryptUtils.byteArrayToHex(HEX_STRING_AS_BYTES);

        assertEquals(HEX_STRING, hexString);
    }

    @Test
    public void shouldReturnByteArrayFromText() {
        byte[] bytes = CryptUtils.pureTextToByteArray(TEXT);

        assertTrue(Arrays.equals(TEXT_AS_BYTES, bytes));
    }

    @Test
    public void shouldReturnTextFromByteArray() {
        String text = CryptUtils.byteArrayToPureText(TEXT_AS_BYTES);

        assertEquals(TEXT, text);
    }
}