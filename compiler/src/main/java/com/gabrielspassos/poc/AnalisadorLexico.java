package main.java.com.gabrielspassos.poc;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AnalisadorLexico {

    private static final String ENCODING = "US-ASCII";

    private static final List<String> SPECIAL_CHARS = Arrays.asList(".", ":", ";", "(", ")");
    private static final String DOIS_PONTOS = ":";

    public List<Token> analisar(String codeFileName) throws IOException {
        PushbackReader pushbackReader = getPushbackReader(codeFileName);
        List<Token> tokens = new ArrayList<>();
        int intch;
        int linha = 1;
        int coluna = 1;

        while((intch = pushbackReader.read()) != -1) {
            char character = (char) intch;
            if(character != ' ' && character != '\n') {
                Token token = getToken(character, pushbackReader);
                if (token != null) {
                    tokens.add(token);
                }
            }
        }

        return tokens;
    }

    private PushbackReader getPushbackReader(String codeFileName) throws FileNotFoundException, UnsupportedEncodingException {
        return new PushbackReader(new BufferedReader(new InputStreamReader(new FileInputStream(codeFileName), ENCODING)));
    }

    private Token getToken(char character, PushbackReader pushbackReader) throws IOException {
        if(Character.isLetter(character)) {
            return handleIdentifierAndReservedWord(character, pushbackReader);
        }

        if(Character.isDigit(character)) {
            return handleDigit(character, pushbackReader);
        }

        if(SPECIAL_CHARS.contains(String.valueOf(character))){
            return handleSpecialChars(character, pushbackReader);
        }

        return new Token(Tipo.SERRO, null);
    }

    private Token handleIdentifierAndReservedWord(char character, PushbackReader pushbackReader) throws IOException {
        String id = String.valueOf(character);
        char nextCharacter = (char) pushbackReader.read();
        while (Character.isLetter(nextCharacter)) {
            id = id.concat(String.valueOf(nextCharacter));
            nextCharacter = (char) pushbackReader.read();
        }
        pushbackReader.unread((int) nextCharacter);
        Tipo tipo = Tipo.getTipoById(id);
        return new Token(tipo, id);
    }

    private Token handleDigit(char character, PushbackReader pushbackReader) throws IOException {
        String num = String.valueOf(character);
        char nextCharacter = (char) pushbackReader.read();
        while (Character.isDigit(nextCharacter)) {
            num = num.concat(String.valueOf(nextCharacter));
            nextCharacter = (char) pushbackReader.read();
        }
        pushbackReader.unread((int) nextCharacter);
        return new Token(Tipo.SNUMERO, num);
    }

    private Token handleSpecialChars(char character, PushbackReader pushbackReader) throws IOException {
        String specialCharacter = String.valueOf(character);
        if (DOIS_PONTOS.equals(specialCharacter)) {
            char nextCharacter = (char) pushbackReader.read();
            specialCharacter = specialCharacter.concat(String.valueOf(nextCharacter));
            Tipo tipoById = Tipo.getTipoById(specialCharacter);
            if (!Tipo.SATRIBUICAO.equals(tipoById)) {
                pushbackReader.unread(nextCharacter);
            }
        }
        Tipo tipo = Tipo.getTipoById(specialCharacter);
        return new Token(tipo, specialCharacter);
    }
}
