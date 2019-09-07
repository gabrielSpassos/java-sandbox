package main.java.com.gabrielspassos.poc;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class AnalisadorLexico {

    private static final String ENCODING = "US-ASCII";

    public List<Token> analisar(String codeFileName) throws IOException {
        PushbackReader pushbackReader = getPushbackReader(codeFileName);
        List<Token> tokens = new ArrayList<>();
        int intch;
        int linha = 1;
        int coluna = 1;

        while((intch = pushbackReader.read()) != -1) {
            char character = (char) intch;
            System.out.println("Character " + character);
            Token token = getToken(character);
            tokens.add(token);
        }

        return tokens;
    }

    private PushbackReader getPushbackReader(String codeFileName) throws FileNotFoundException, UnsupportedEncodingException {
        return new PushbackReader(new BufferedReader(new InputStreamReader(new FileInputStream(codeFileName), ENCODING)));
    }

    private Token getToken(char character) {
        if(Character.isLetter(character)) {
            return handleIdentifierAndReservedWord(character);
        }

        if(Character.isDigit(character)) {
            return handleDigit(character);
        }

        return new Token(Tipo.SERRO, null);
    }

    private Token handleDigit(char character) {
        String num = String.valueOf(character);
        while (Character.isDigit(character)) {
            num = num.concat(String.valueOf(character));
        }
        return new Token(Tipo.SNUMERO, num);
    }

    private Token handleIdentifierAndReservedWord(char character) {
        String id = String.valueOf(character);
        Tipo tipo = Tipo.getTipoById(id);
        return new Token(tipo, id);
    }
}
