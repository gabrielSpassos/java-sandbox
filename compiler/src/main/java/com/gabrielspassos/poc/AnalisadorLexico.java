package main.java.com.gabrielspassos.poc;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AnalisadorLexico {

    private static final String ENCODING = "US-ASCII";
    private int linha = 1;
    private int coluna = 1;

    private static final List<String> SPECIAL_CHARS = Arrays.asList(".", ":", ";", "(", ")");
    private static final String DOIS_PONTOS = ":";

    public List<Token> analisar(String codeFileName) throws IOException {
        PushbackReader pushbackReader = getPushbackReader(codeFileName);
        List<Token> tokens = new ArrayList<>();

        char character;
        do {
            character = readChar(pushbackReader);
            if (character == ' ' || character == '\n' || character == '@') {
                continue;
            }
            Token token = getToken(character, pushbackReader);
            if (token != null) {
                tokens.add(token);
            }
        } while (character != '@');

        return tokens;
    }

    private PushbackReader getPushbackReader(String codeFileName) throws FileNotFoundException, UnsupportedEncodingException {
        return new PushbackReader(new BufferedReader(new InputStreamReader(new FileInputStream(codeFileName), ENCODING)));
    }

    private char readChar(PushbackReader pushbackReader) throws IOException {
        int intch = pushbackReader.read();
        char character = (char) intch;
        if(character == '\n') {
            linha++;
            coluna = 1;
        } else {
            coluna++;
        }

        if(intch != -1) {
            return character;
        } else {
            return '@';
        }
    }

    private void unreadChar(PushbackReader pushbackReader, char character) throws IOException {
        pushbackReader.unread(character);
        if(character == '\n') {
            linha--;
        } else {
            coluna--;
        }
    }

    private Token getToken(char character, PushbackReader pushbackReader) throws IOException {
        if(Character.isLetter(character)) {
            return handleIdentifierAndReservedWord(character, pushbackReader);
        }

        if (Tipo.SMAIS.getId().equals(String.valueOf(character))
                || Tipo.SMENOS.getId().equals(String.valueOf(character))) {
            return handlePlusAndMinusOperation(character, pushbackReader);
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
        char nextCharacter = readChar(pushbackReader);
        while (Character.isLetter(nextCharacter)) {
            id = id.concat(String.valueOf(nextCharacter));
            nextCharacter = readChar(pushbackReader);
        }
        unreadChar(pushbackReader, nextCharacter);
        Tipo tipo = Tipo.getTipoById(id);
        return new Token(tipo, id, linha, coluna);
    }

    private Token handlePlusAndMinusOperation(char character, PushbackReader pushbackReader) throws IOException {
        String operator = String.valueOf(character);
        char nextCharacter = readChar(pushbackReader);
        if (Character.isSpaceChar(nextCharacter)) {
            Tipo tipo = Tipo.getTipoById(operator);
            return new Token(tipo, operator, linha, coluna);
        }
        Token token = handleDigit(nextCharacter, pushbackReader);
        token.setLexema(operator.concat(token.getLexema()));
        return token;

    }

    private Token handleDigit(char character, PushbackReader pushbackReader) throws IOException {
        String num = String.valueOf(character);
        char nextCharacter = readChar(pushbackReader);
        while (Character.isDigit(nextCharacter) || Tipo.SPONTO.getId().equals(String.valueOf(nextCharacter))) {
            num = num.concat(String.valueOf(nextCharacter));
            nextCharacter = readChar(pushbackReader);
        }
        unreadChar(pushbackReader, nextCharacter);
        return new Token(Tipo.SNUMERO, num, linha, coluna);
    }

    private Token handleSpecialChars(char character, PushbackReader pushbackReader) throws IOException {
        String specialCharacter = String.valueOf(character);
        if (DOIS_PONTOS.equals(specialCharacter)) {
            char nextCharacter = readChar(pushbackReader);
            specialCharacter = specialCharacter.concat(String.valueOf(nextCharacter));
            Tipo tipoById = Tipo.getTipoById(specialCharacter);
            if (!Tipo.SATRIBUICAO.equals(tipoById)) {
                unreadChar(pushbackReader, nextCharacter);
            }
        }
        Tipo tipo = Tipo.getTipoById(specialCharacter);
        return new Token(tipo, specialCharacter, linha, coluna);
    }
}
