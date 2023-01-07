package com.gabrielspassos.services;

import java.util.HexFormat;

public class HexService {

    public void test() {
        String hex = HexFormat.of().toHexDigits(1994);
        int numeral = HexFormat.fromHexDigits("7CA");
        System.out.println("Hex: " + hex);
        System.out.println("Numeral: " + numeral);
    }
}
