package main.java.com.gabrielspassos.poc;

import java.util.List;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        AnalisadorLexico analisadorLexico = new AnalisadorLexico();
        try {
            List<Token> tokens = analisadorLexico.analisar("teste1.lpd");

            System.out.println("Número de tokens: " + tokens.size());

            List t = tokens.stream()
                    .peek(System.out::println)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
