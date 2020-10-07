package com.gabrielspassos.poc.service;

import com.gabrielspassos.poc.crypt.CryptUtils;
import com.gabrielspassos.poc.crypt.TripleDES;
import com.gabrielspassos.poc.enumerator.TripleDESMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.gabrielspassos.poc.service.LogService.logPanBlockInfo;
import static com.gabrielspassos.poc.service.LogService.logPinPanBlockInfo;

public class AuthorizationService {

    private static final Logger LOG = LoggerFactory.getLogger(AuthorizationService.class);

    public static String createEncryptedPanBlock(String cardTrailOrNumber, String secretKey, TripleDESMode tripleDESMode) {
        String panBlock = CardService.createPanBlock(cardTrailOrNumber);

        try {
            String encryptedPanBlock = TripleDES.encryptText(panBlock, secretKey, tripleDESMode, tripleDESMode.getParameterSpec());
            logPanBlockInfo(encryptedPanBlock, panBlock, cardTrailOrNumber, tripleDESMode, secretKey);
            return encryptedPanBlock;
        } catch (Exception e) {
            LOG.error("Error to encrypt pan block", e);
            throw new RuntimeException(e);
        }
    }

    public static String createPinPanBlock(String cardTrailOrNumber, String cardPassword,
                                           String secretKey, TripleDESMode tripleDESMode) {
        String panBlock = CardService.createPanBlock(cardTrailOrNumber);
        String pinBlock = CardService.createPinBlock(cardPassword);

        String xorPinPanBlock = xorWithPanBlock(pinBlock, panBlock);

        try {
            String encryptedPinPanBlock = TripleDES.encryptHex(xorPinPanBlock, secretKey, tripleDESMode, tripleDESMode.getParameterSpec());
            logPinPanBlockInfo(encryptedPinPanBlock, xorPinPanBlock, cardTrailOrNumber, cardPassword, tripleDESMode, secretKey);
            return encryptedPinPanBlock;
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
