package com.gabrielspassos.poc.crypt;

import com.gabrielspassos.poc.enumerator.TripleDESMode;
import org.junit.jupiter.api.Test;

import javax.crypto.spec.IvParameterSpec;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TripleDESTest {

    private final String CARD_NUMBER = "5115881573557547";
    private final String CARD_NUMBER_HEX_ECB = "17EC9C512B9744FC6B48C5DA004EDC44";
    private final String CARD_NUMBER_HEX_CBC = "17EC9C512B9744FCB0DC0494EEEE87AA";
    private final String PIN_BLOCK = "041234FFFFFFFFFF";
    private final String PIN_BLOCK_HEX_ECB = "0B2E1137D6486F23";
    private final String PIN_BLOCK_HEX_CBC = "0B2E1137D6486F23";

    @Test
    public void shouldEncryptTextWithCBC() throws Exception {
        IvParameterSpec parameterSpec = new IvParameterSpec(new byte[]{0, 0, 0, 0, 0, 0, 0, 0});

        String encrypt = TripleDES.encryptText(
                CARD_NUMBER, Keys.TRIPLE_DES_COMPOSED_KEY, TripleDESMode.CBC_NO_PADDING, parameterSpec
        );

        assertEquals(CARD_NUMBER_HEX_CBC, encrypt);
    }

    @Test
    public void shouldEncryptHexWithCBC() throws Exception {
        IvParameterSpec parameterSpec = new IvParameterSpec(new byte[]{0, 0, 0, 0, 0, 0, 0, 0});

        String encrypt = TripleDES.encryptHex(
                PIN_BLOCK, Keys.TRIPLE_DES_COMPOSED_KEY, TripleDESMode.CBC_NO_PADDING, parameterSpec
        );

        assertEquals(PIN_BLOCK_HEX_CBC, encrypt);
    }

    @Test
    public void shouldEncryptTextWithECB() throws Exception {
        String encrypt = TripleDES.encryptText(
                CARD_NUMBER, Keys.TRIPLE_DES_COMPOSED_KEY, TripleDESMode.ECB_NO_PADDING, null
        );

        assertEquals(CARD_NUMBER_HEX_ECB, encrypt);
    }

    @Test
    public void shouldEncryptHexWithECB() throws Exception {
        String encrypt = TripleDES.encryptHex(
                PIN_BLOCK, Keys.TRIPLE_DES_COMPOSED_KEY, TripleDESMode.ECB_NO_PADDING, null
        );

        assertEquals(PIN_BLOCK_HEX_ECB, encrypt);
    }
}