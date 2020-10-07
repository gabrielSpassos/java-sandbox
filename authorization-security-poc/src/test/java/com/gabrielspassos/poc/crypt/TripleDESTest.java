package com.gabrielspassos.poc.crypt;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TripleDESTest {

    private final String PIN_BLOCK = "041234FFFFFFFFFF";
    private final String PIN_BLOCK_HEX_ECB = "0B2E1137D6486F23";

    @Test
    public void shouldEncrypt() throws Exception {
        String encrypt = TripleDES.encryptHexWithECBMode(PIN_BLOCK, Keys.TRIPLE_DES_COMPOSED_KEY);

        assertEquals(PIN_BLOCK_HEX_ECB, encrypt);
    }
}