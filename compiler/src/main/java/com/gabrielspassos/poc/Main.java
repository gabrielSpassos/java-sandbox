package main.java.com.gabrielspassos.poc;

import java.util.Arrays;
import java.util.List;

public class Main {

    private static final String DEFAULT_FILE = "teste1.lpd";

    public static void main(String[] args) {
        System.out.println("-----------Analise Lexica-----------");
        lexicalAnalise(args);
        System.out.println("-----------Analise Sintatica-----------");
        syntacticAnalise(args);
    }

    private static void lexicalAnalise(String[] args) {
        try {
            String fileName = getFileName(args);
            AnalisadorLexico analisadorLexico = new AnalisadorLexico(fileName);
            System.out.println("Analisando léxicamente o arquivo " + fileName);
            List<Token> tokens = analisadorLexico.analise(fileName);

            System.out.println("Número de tokens: " + tokens.size());

            tokens.forEach(System.out::println);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void syntacticAnalise(String[] args) {
        try {
            String fileName = getFileName(args);
            ParserPreditivoRecursivo ppr = new ParserPreditivoRecursivo(fileName);
            if(ppr.parse()) {
                System.out.println("Aceito sintaticamente");
            }
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
