package main.java.com.gabrielspassos.poc;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AnalisadorLexico {

    private static final String ENCODING = "US-ASCII";
    private int linha = 1;
    private int coluna = 0;

    private static final List<String> SPECIAL_CHARS = Arrays.asList(".", ":", ";", "(", ")");
    private static final String DOIS_PONTOS = ":";

    public List<Token> analise(String codeFileName) throws IOException {
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
            coluna = 0;
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
            int col = coluna;
            return handleIdentifierAndReservedWord(character, pushbackReader, col);
        }

        if (Tipo.SMAIS.getId().equals(String.valueOf(character))
                || Tipo.SMENOS.getId().equals(String.valueOf(character))) {
            int col = coluna;
            return handlePlusAndMinusOperation(character, pushbackReader, col);
        }

        if(Character.isDigit(character)) {
            int col = coluna;
            return handleDigit(character, pushbackReader, col);
        }

        if(SPECIAL_CHARS.contains(String.valueOf(character))){
            int col = coluna;
            return handleSpecialChars(character, pushbackReader, col);
        }

        return new Token(Tipo.SERRO, null);
    }

    private Token handleIdentifierAndReservedWord(char character, PushbackReader pushbackReader, int coluna) throws IOException {
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

    private Token handlePlusAndMinusOperation(char character, PushbackReader pushbackReader, int coluna) throws IOException {
        String operator = String.valueOf(character);
        char nextCharacter = readChar(pushbackReader);
        if (Character.isSpaceChar(nextCharacter)) {
            Tipo tipo = Tipo.getTipoById(operator);
            return new Token(tipo, operator, linha, coluna);
        }
        Token token = handleDigit(nextCharacter, pushbackReader, coluna);
        token.setLexema(operator.concat(token.getLexema()));
        return token;

    }

    private Token handleDigit(char character, PushbackReader pushbackReader, int coluna) throws IOException {
        String num = String.valueOf(character);
        char nextCharacter = readChar(pushbackReader);
        while (Character.isDigit(nextCharacter) || Tipo.SPONTO.getId().equals(String.valueOf(nextCharacter))) {
            if (isInvalidFloatNumber(String.valueOf(nextCharacter), pushbackReader)) {
                return new Token(Tipo.SERRO, null, linha, coluna);
            }
            num = num.concat(String.valueOf(nextCharacter));
            nextCharacter = readChar(pushbackReader);
        }
        unreadChar(pushbackReader, nextCharacter);
        return new Token(Tipo.SNUMERO, num, linha, coluna);
    }

    private Token handleSpecialChars(char character, PushbackReader pushbackReader, int coluna) throws IOException {
        String specialCharacter = String.valueOf(character);
        if (DOIS_PONTOS.equals(specialCharacter)) {
            char nextCharacter = readChar(pushbackReader);
            String ch = specialCharacter.concat(String.valueOf(nextCharacter));
            Tipo tipoById = Tipo.getTipoById(ch);
            if (Tipo.SATRIBUICAO.equals(tipoById)) {
                return new Token(tipoById, ch, linha, coluna);
            }
            unreadChar(pushbackReader, nextCharacter);
        }
        Tipo tipo = Tipo.getTipoById(specialCharacter);
        return new Token(tipo, specialCharacter, linha, coluna);
    }

    private Boolean isInvalidFloatNumber(String character, PushbackReader pushbackReader) throws IOException {
        if (Tipo.SPONTO.getId().equals(character)) {
            char nextCharacter = readChar(pushbackReader);
            if(Tipo.SPONTO.getId().equals(String.valueOf(nextCharacter))) {
                return true;
            }
            unreadChar(pushbackReader, nextCharacter);
            return false;
        }
        return false;
    }
}
