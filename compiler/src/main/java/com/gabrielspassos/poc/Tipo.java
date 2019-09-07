package main.java.com.gabrielspassos.poc;

public enum Tipo {

    SPROGRAMA,
    SVAR,
    SDOISPONTOS,
    SINICIO,
    SFIM,
    SATRIBUICAO,
    STIPO,
    SESCREVA,
    SINTEIRO,
    SPONTO_E_VIRGULA,
    SPONTO,
    SMAIS,
    SMENOS,
    SMULTIPLICACAO,
    SNUMERO,
    SIDENTIFICADOR,
    SABRE_PARENTESIS,
    SFECHA_PARENTESIS,
    SERRO;

    public static Tipo getTipoById(String id) {
        switch (id) {
            case "programa":
                return SPROGRAMA;
            case "var":
                return SVAR;
            case ":":
                return SDOISPONTOS;
            case "inicio":
                return SINICIO;
            case "fim":
                return SFIM;
            case ":=":
                return SATRIBUICAO;
            case "inteiro":
                return SINTEIRO;
            case ";":
                return SPONTO_E_VIRGULA;
            case ".":
                return SPONTO;
            case "escreva":
                return SESCREVA;
            default:
                return SIDENTIFICADOR;
        }
    }

}
