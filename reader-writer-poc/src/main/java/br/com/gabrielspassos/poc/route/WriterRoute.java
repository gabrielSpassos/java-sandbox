package br.com.gabrielspassos.poc.route;

import br.com.gabrielspassos.poc.config.PropertiesReader;
import org.apache.camel.builder.RouteBuilder;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class WriterRoute extends RouteBuilder {

    private PropertiesReader propertiesReader;

    public WriterRoute() {
        this.propertiesReader = PropertiesReader.getInstance();
    }

    @Override
    public void configure() throws Exception {
        from("direct:writer")
                .routeId("writer")
                .convertBodyTo(String.class)
                .to(buildOutputFilePath())
                .end();
    }

    private String buildOutputFilePath() {
        return new StringBuilder()
                .append("file://")
                .append(getOutputPath())
                .append("?fileName=")
                .append(getFileName())
                .append("&charset=")
                .append(getCharset())
                .toString();
    }

    private String getOutputPath() {
        return propertiesReader.getProperty("file.output.path");
    }

    private String getFileName() {
        return new StringBuilder()
                .append(getFileNameWithoutExtension())
                .append(".")
                .append(getCurrentDate())
                .append(getFileExtension())
                .toString();
    }

    private String getCurrentDate() {
        return LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    private String getFileNameWithoutExtension() {
        return propertiesReader.getProperty("file.output.filename");
    }

    private String getFileExtension() {
        return propertiesReader.getProperty("file.output.file-extension");
    }

    private String getCharset() {
        return propertiesReader.getProperty("file.charset");
    }
}
