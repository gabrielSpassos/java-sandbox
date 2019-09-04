package main.java.com.gabrielspassos.poc;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class AnalisadorLexico {

    PushbackReader pushbackReader;
    private static final String ENCODING = "US-ASCII";

    public List<Token> analisar(String codeFileName) throws IOException {
        pushbackReader = new PushbackReader(new BufferedReader(new InputStreamReader(new FileInputStream(codeFileName), ENCODING)));
        int intch;
        int linha = 1;
        int coluna = 1;

        while((intch = pushbackReader.read()) != -1) {
            char ch = (char) intch;
            System.out.println(ch);

            if(Character.isLetter(ch)) {

            }

            if(Character.isDigit(ch)) {

            }

            if(ch == '\n') {
                linha++;
                coluna = 1;
            }
        }

        return new ArrayList<>();
    }
}
