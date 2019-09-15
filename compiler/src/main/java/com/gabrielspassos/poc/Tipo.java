package main.java.com.gabrielspassos.poc;

import java.util.stream.Stream;

public enum Tipo {

    SPROGRAMA("programa"),
    SVAR("var"),
    SDOISPONTOS(":"),
    SINICIO("inicio"),
    SFIM("fim"),
    SATRIBUICAO(":="),
    SESCREVA("escreva"),
    SINTEIRO("inteiro"),
    SPONTO_E_VIRGULA(";"),
    SPONTO("."),
    SMAIS("+"),
    SMENOS("-"),
    SMULTIPLICACAO("*"),
    SNUMERO(null),
    SIDENTIFICADOR(null),
    SABRE_PARENTESIS("("),
    SFECHA_PARENTESIS(")"),
    SERRO(null);

    Tipo(String id) {
        this.id = id;
    }

    private String id;

    public static Tipo getTipoById(String id) {
        return Stream.of(Tipo.values())
                .filter(tipo -> id.equals(tipo.id))
                .findFirst()
                .orElse(Tipo.SIDENTIFICADOR);
    }

    public String getId() {
        return id;
    }
}
