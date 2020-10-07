package com.gabrielspassos.poc.service;

import com.gabrielspassos.poc.enumerator.TripleDESMode;

public class LogService {

    public static void logPanBlockInfo(String encryptedPanBlock, String panBlock, String cardTrailOrNumber,
                                       TripleDESMode tripleDESMode, String secretKey) {
        System.out.println("Encriptado Pan Block com Sucesso!");
        System.out.println("Chave: " + secretKey);
        System.out.println("Modo: " + tripleDESMode.getMode());
        System.out.println("Trilha/Nº Cartão: " + cardTrailOrNumber);
        System.out.println("Pan Block: " + panBlock);
        System.out.println("Pan Block Encriptado: " + encryptedPanBlock);
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
}
