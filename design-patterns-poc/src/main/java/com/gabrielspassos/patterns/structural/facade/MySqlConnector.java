package com.gabrielspassos.patterns.structural.facade;

public class MySqlConnector {

    public boolean generatePDFReport() {
        IO.println("Created MYSQL PDF Report");
        return true;
    }

    public boolean generateHTMLReport() {
        IO.println("Created MYSQL HTML Report");
        return true;
    }

}
