package main.java.com.gabrielspassos.poc;

import java.io.IOException;

public class ParserPreditivoRecursivo extends Parser {

    public ParserPreditivoRecursivo(String codeFileName) throws IOException {
        super(codeFileName);
    }

    @Override
    public Boolean parse() {
        return analisaPrograma();
    }

    private Boolean analisaPrograma() {
        getAnalyzedToken();

        if(token.getTipo().equals(Tipo.SPROGRAMA)) {
            getAnalyzedToken();
            if(token.getTipo().equals(Tipo.SIDENTIFICADOR)) {
                tabelaSimbolos.getTabela().put(token.getLexema(), token);

                getAnalyzedToken();

                if (token.getTipo().equals(Tipo.SPONTO_E_VIRGULA)) {
                    if (analisaBloco()) {
                        getAnalyzedToken();
                        if(token.getTipo().equals(Tipo.SPONTO)) {
                            return true;
                        } else {
                            return error("Faltando ponto");
                        }
                    } else {
                        return error("Faltando bloco");
                    }
                } else {
                    return error("Faltando ponto virgula");
                }
            } else {
                return error("Nao foi encontrado nome do programa");
            }
        } else {
            return error("Nao foi encontrado programa principal");
        }
    }

    private Boolean analisaBloco() {
        return true;
    }
}
