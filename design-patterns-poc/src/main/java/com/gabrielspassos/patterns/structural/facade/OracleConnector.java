package com.gabrielspassos.patterns.structural.facade;

public class OracleConnector {

    public boolean generatePDFReport() {
        IO.println("Created Oracle PDF Report");
        return true;
    }

    public boolean generateHTMLReport() {
        IO.println("Created Oracle HTML Report");
        return true;
    }
}
