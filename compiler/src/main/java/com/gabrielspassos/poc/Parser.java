package main.java.com.gabrielspassos.poc;

import java.io.IOException;

public abstract class Parser {

    private TabelaSimbolos tabelaSimbolos;
    private AnalisadorLexico analisadorLexico;
    private Token token;

    public Parser(String codeFileName) throws IOException {
        this.tabelaSimbolos = new TabelaSimbolos();
        this.analisadorLexico = new AnalisadorLexico(codeFileName);
    }

    public void getAnalizedToken() {
        token = analisadorLexico.getToken();
    }

    public abstract Boolean parse();

    public TabelaSimbolos getTabelaSimbolos() {
        return tabelaSimbolos;
    }

    public Boolean error(String message) {
        System.out.println(message + " " + token.getTipo() + " " + token.getColuna());
        return false;
    }

    public void setTabelaSimbolos(TabelaSimbolos tabelaSimbolos) {
        this.tabelaSimbolos = tabelaSimbolos;
    }

    public AnalisadorLexico getAnalisadorLexico() {
        return analisadorLexico;
    }

    public void setAnalisadorLexico(AnalisadorLexico analisadorLexico) {
        this.analisadorLexico = analisadorLexico;
    }

    public Token getToken() {
        return token;
    }

    public void setToken(Token token) {
        this.token = token;
    }
}
