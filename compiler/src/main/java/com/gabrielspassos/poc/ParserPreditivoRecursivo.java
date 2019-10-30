package main.java.com.gabrielspassos.poc;

import java.io.IOException;

public class ParserPreditivoRecursivo extends Parser {

    public ParserPreditivoRecursivo(String codeFileName) throws IOException {
        super(codeFileName);
    }

    @Override
    public void parse() {
        analisaPrograma();
    }

    private Boolean analisaPrograma() {
        getAnalizedToken();

        if(getToken().getTipo().equals(Tipo.SPROGRAMA)) {
            getAnalizedToken();
            if(getToken().getTipo().equals(Tipo.SIDENTIFICADOR)) {
                getTabelaSimbolos().getTabela().put(getToken().getLexema(), getToken());

                getAnalizedToken();

                if (getToken().getTipo().equals(Tipo.SPONTO_E_VIRGULA)) {
                    if (analisaBloco()) {
                        getAnalizedToken();
                        if(getToken().getTipo().equals(Tipo.SPONTO)) {
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
