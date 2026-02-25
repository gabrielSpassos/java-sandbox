package com.gabrielspassos.patterns.structural.facade;

public class DatabaseFacade {

    public boolean generateReport(DatabaseType databaseType, ReportType reportType) {
        return switch (databaseType) {
            case MYSQL -> {
                var mysqlConnector = new MySqlConnector();
                yield switch (reportType) {
                    case PDF -> mysqlConnector.generatePDFReport();
                    case HTML -> mysqlConnector.generateHTMLReport();
                };
            }
            case ORACLE -> {
                var oracleConnector = new OracleConnector();
                yield switch (reportType) {
                    case PDF -> oracleConnector.generatePDFReport();
                    case HTML -> oracleConnector.generateHTMLReport();
                };
            }
        };
    }
}
