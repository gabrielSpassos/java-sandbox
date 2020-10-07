package com.gabrielspassos.poc.crypt;

import com.gabrielspassos.poc.enumerator.TripleDESMode;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import java.security.spec.KeySpec;

public class TripleDES {

    public static String encryptTextWithECBMode(String valueToEncrypt, String secretKey) throws Exception {
        Cipher cipher = createCipher(Cipher.ENCRYPT_MODE, secretKey, TripleDESMode.ECB_NO_PADDING);
        byte[] encryptedText = cipher.doFinal( CryptUtils.pureTextToByteArray(valueToEncrypt));
        return CryptUtils.byteArrayToHex(encryptedText);
    }

    public static String encryptHexWithECBMode(String hexValueToEncrypt, String secretKey) throws Exception {
        Cipher cipher = createCipher(Cipher.ENCRYPT_MODE, secretKey, TripleDESMode.ECB_NO_PADDING);
        byte[] encryptedText = cipher.doFinal( CryptUtils.hexStringToByteArray(hexValueToEncrypt));
        return CryptUtils.byteArrayToHex(encryptedText);
    }

    private static Cipher createCipher(Integer operationMode, String secretKey, TripleDESMode tripleDESMode) throws Exception{
        byte[] keyAsBytes = CryptUtils.hexStringToByteArray(secretKey);
        KeySpec keySpec = new DESedeKeySpec(keyAsBytes);
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DESede");

        Cipher cipher = Cipher.getInstance(tripleDESMode.getMode());

        SecretKey key = keyFactory.generateSecret(keySpec);
        cipher.init(operationMode, key);
        return cipher;
    }

}
