package main.java.com.gabrielspassos.poc;

import java.util.Arrays;

public class MainParser {

    private static final String DEFAULT_FILE = "teste1.lpd";

    public static void main(String[] args) {
        try {
            String fileName = getFileName(args);
            ParserPreditivoRecursivo ppr = new ParserPreditivoRecursivo(fileName);
            if(ppr.parse()) {
                System.out.println("Aceito sintaticamente");
            };
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private static String getFileName(String[] args) {
        return Arrays.stream(args)
                .findFirst()
                .orElse(DEFAULT_FILE);
    }
}
