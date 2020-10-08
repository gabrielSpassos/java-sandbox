package com.gabrielspassos.poc.crypt;

import com.gabrielspassos.poc.enumerator.TripleDESMode;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.KeySpec;

public class TripleDES {

    public static String encryptText(String textValueToEncrypt, String secretKey, TripleDESMode tripleDESMode,
                                     AlgorithmParameterSpec algorithmParameterSpec) throws Exception {
        Cipher cipher = createCipher(Cipher.ENCRYPT_MODE, secretKey, tripleDESMode, algorithmParameterSpec);
        byte[] encryptedBytes = cipher.doFinal(CryptUtils.pureTextToByteArray(textValueToEncrypt));
        return CryptUtils.byteArrayToHex(encryptedBytes);
    }

    public static String decryptToText(String encryptedHex, String secretKey, TripleDESMode tripleDESMode,
                                       AlgorithmParameterSpec algorithmParameterSpec) throws Exception {
        Cipher cipher = createCipher(Cipher.DECRYPT_MODE, secretKey, tripleDESMode, algorithmParameterSpec);
        byte[] decryptedBytes = cipher.doFinal(CryptUtils.hexStringToByteArray(encryptedHex));
        return CryptUtils.byteArrayToPureText(decryptedBytes);
    }

    public static String encryptHex(String textValueToEncrypt, String secretKey, TripleDESMode tripleDESMode,
                                    AlgorithmParameterSpec algorithmParameterSpec) throws Exception {
        Cipher cipher = createCipher(Cipher.ENCRYPT_MODE, secretKey, tripleDESMode, algorithmParameterSpec);
        byte[] encryptedBytes = cipher.doFinal(CryptUtils.hexStringToByteArray(textValueToEncrypt));
        return CryptUtils.byteArrayToHex(encryptedBytes);
    }

    public static String decryptToHex(String encryptedHex, String secretKey, TripleDESMode tripleDESMode,
                                       AlgorithmParameterSpec algorithmParameterSpec) throws Exception {
        Cipher cipher = createCipher(Cipher.DECRYPT_MODE, secretKey, tripleDESMode, algorithmParameterSpec);
        byte[] decryptedBytes = cipher.doFinal(CryptUtils.hexStringToByteArray(encryptedHex));
        return CryptUtils.byteArrayToHex(decryptedBytes);
    }

    private static Cipher createCipher(Integer operationMode, String secretKey, TripleDESMode tripleDESMode,
                                       AlgorithmParameterSpec algorithmParameterSpec) throws Exception{
        byte[] keyAsBytes = CryptUtils.hexStringToByteArray(secretKey);
        KeySpec keySpec = new DESedeKeySpec(keyAsBytes);
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DESede");

        Cipher cipher = Cipher.getInstance(tripleDESMode.getMode());

        SecretKey key = keyFactory.generateSecret(keySpec);
        cipher.init(operationMode, key, algorithmParameterSpec);
        return cipher;
    }

}
