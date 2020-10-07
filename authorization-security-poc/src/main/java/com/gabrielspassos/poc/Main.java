package com.gabrielspassos.poc;

import com.gabrielspassos.poc.crypt.Keys;
import com.gabrielspassos.poc.enumerator.TripleDESMode;
import com.gabrielspassos.poc.service.AuthorizationService;

public class Main {

    public static void main(String[] args) {
        String cardTrailOrNumber = "5115881573557547";
        String cardPassword = "1020";
        String key = Keys.TRIPLE_DES_COMPOSED_KEY;

        AuthorizationService.createEncryptedPanBlock(cardTrailOrNumber, key, TripleDESMode.CBC_NO_PADDING);
        AuthorizationService.createPinPanBlock(cardTrailOrNumber, cardPassword, key, TripleDESMode.CBC_NO_PADDING);
    }
}
