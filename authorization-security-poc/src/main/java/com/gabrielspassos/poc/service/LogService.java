package com.gabrielspassos.poc.service;

import com.gabrielspassos.poc.enumerator.TripleDESMode;

public class LogService {

    public static void logPanEncryptInfo(String encryptedPan, String cardNumber,
                                         TripleDESMode tripleDESMode, String secretKey) {
        System.out.println("Encriptado Pan com Sucesso!");
        System.out.println("Chave: " + secretKey);
        System.out.println("Modo: " + tripleDESMode.getMode());
        System.out.println("Nº Cartão: " + cardNumber);
        System.out.println("Pan Encriptado: " + encryptedPan);
        System.out.println("----------------------------------------------");
    }

    public static void logPanDecryptInfo(String encryptedPan, String cardNumber,
                                         TripleDESMode tripleDESMode, String secretKey) {
        System.out.println("Decriptado Pan com Sucesso!");
        System.out.println("Chave: " + secretKey);
        System.out.println("Modo: " + tripleDESMode.getMode());
        System.out.println("Pan Encriptado: " + encryptedPan);
        System.out.println("Nº Cartão: " + cardNumber);
        System.out.println("----------------------------------------------");
    }

    public static void logPinPanBlockInfo(String encryptedPinPanBlock, String xorPinPanBlock, String cardTrailOrNumber,
                                          String cardPassword, TripleDESMode tripleDESMode, String secretKey) {
        System.out.println("Encriptado Pin Pan Block com Sucesso!");
        System.out.println("Chave: " + secretKey);
        System.out.println("Modo: " + tripleDESMode.getMode());
        System.out.println("Trilha/Nº Cartão: " + cardTrailOrNumber);
        System.out.println("Senha Cartão: " + cardPassword);
        System.out.println("XOR Pin Pan Block: " + xorPinPanBlock);
        System.out.println("Pin Pan Block Encriptado: " + encryptedPinPanBlock);
        System.out.println("----------------------------------------------");
    }

    public static void logPinPanBlockDecryptInfo(String encryptedPinPanBlock, String xorPinPanBlock, String cardTrailOrNumber,
                                                 String cardPassword, TripleDESMode tripleDESMode, String secretKey) {
        System.out.println("Decriptado Pin Pan Block com Sucesso!");
        System.out.println("Chave: " + secretKey);
        System.out.println("Modo: " + tripleDESMode.getMode());
        System.out.println("Trilha/Nº Cartão: " + cardTrailOrNumber);
        System.out.println("Pin Pan Block Encriptado: " + encryptedPinPanBlock);
        System.out.println("XOR Pin Pan Block: " + xorPinPanBlock);
        System.out.println("Senha Cartão: " + cardPassword);
        System.out.println("----------------------------------------------");
    }
}
