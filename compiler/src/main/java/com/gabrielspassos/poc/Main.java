package main.java.com.gabrielspassos.poc;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        AnalisadorLexico analisadorLexico = new AnalisadorLexico();
        try {
            List<Token> tokens = analisadorLexico.analisar("teste1.lpd");

            System.out.println("Número de tokens: " + tokens.size());

            tokens.forEach(System.out::println);

        } catch (Exception e) {
            System.out.println("------ Error -------");
            e.printStackTrace();
        }
    }
}
