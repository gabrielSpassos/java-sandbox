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

        while((intch = pushbackReader.read()) != -1) {
            System.out.println((char) intch);
        }

        return new ArrayList<>();
    }
}
