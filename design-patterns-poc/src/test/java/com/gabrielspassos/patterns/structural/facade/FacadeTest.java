package com.gabrielspassos.patterns.structural.facade;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class FacadeTest {

    @Test
    void shouldCreateMysqlPDFReport() {
        var databaseFacade = new DatabaseFacade();

        var result = databaseFacade.generateReport(DatabaseType.MYSQL, ReportType.PDF);

        assertTrue(result);
    }

    @Test
    void shouldCreateMysqlHTMLReport() {
        var databaseFacade = new DatabaseFacade();

        var result = databaseFacade.generateReport(DatabaseType.MYSQL, ReportType.HTML);

        assertTrue(result);
    }

    @Test
    void shouldCreateOraclePDFReport() {
        var databaseFacade = new DatabaseFacade();

        var result = databaseFacade.generateReport(DatabaseType.ORACLE, ReportType.PDF);

        assertTrue(result);
    }

    @Test
    void shouldCreateOracleHTMLReport() {
        var databaseFacade = new DatabaseFacade();

        var result = databaseFacade.generateReport(DatabaseType.ORACLE, ReportType.HTML);

        assertTrue(result);
    }
}
