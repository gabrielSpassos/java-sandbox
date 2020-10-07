package com.gabrielspassos.poc.service;

import com.gabrielspassos.poc.crypt.CryptUtils;
import com.gabrielspassos.poc.crypt.TripleDES;
import com.gabrielspassos.poc.enumerator.TripleDESMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AuthorizationService {

    private static final Logger LOG = LoggerFactory.getLogger(AuthorizationService.class);

    public static String createEncryptedPanBlock(String cardTrailOrNumber, String secretKey) {
        String panBlock = CardService.createPanBlock(cardTrailOrNumber);

        try {
            return TripleDES.encryptText(panBlock, secretKey, TripleDESMode.ECB_NO_PADDING, null);
        } catch (Exception e) {
            LOG.error("Error to encrypt pan block", e);
            throw new RuntimeException(e);
        }
    }

    public static String createPinPanBlock(String cardTrailOrNumber, String cardPassword, String secretKey) {
        String panBlock = CardService.createPanBlock(cardTrailOrNumber);
        String pinBlock = CardService.createPinBlock(cardPassword);

        String xorPinPanBlock = xorWithPanBlock(pinBlock, panBlock);

        try {
            return TripleDES.encryptHex(xorPinPanBlock, secretKey, TripleDESMode.ECB_NO_PADDING, null);
        } catch (Exception e) {
            LOG.error("Error to create pin pan block", e);
            throw new RuntimeException(e);
        }
    }

    private static String xorWithPanBlock(String value, String panBlock) {
        byte[] xor = new byte[8];

        byte[] valueParsed = CryptUtils.hexStringToByteArray(value);
        byte[] panParsed = CryptUtils.hexStringToByteArray(panBlock);

        for (int i = 0; i < valueParsed.length; i++) {
            xor[i] = (byte) (valueParsed[i] ^ panParsed[i]);
        }

        return CryptUtils.byteArrayToHex(xor);
    }
}
