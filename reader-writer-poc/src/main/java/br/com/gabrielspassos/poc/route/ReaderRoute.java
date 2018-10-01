package br.com.gabrielspassos.poc.route;

import br.com.gabrielspassos.poc.config.PropertiesReader;
import org.apache.camel.builder.RouteBuilder;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ReaderRoute extends RouteBuilder {

    private PropertiesReader propertiesReader;

    public ReaderRoute() {
        this.propertiesReader = PropertiesReader.getInstance();
    }

    @Override
    public void configure() throws Exception {
        onException(Exception.class)
                .maximumRedeliveries(3)
                .handled(true)
                .log("Error reading file");

        from(buildInputFilePath())
                .routeId("reader")
                .to(buildProcessedFilePath())
                .convertBodyTo(String.class)
                .log("Input Message ${body}")
                .to("direct:decoder")
                .end();
    }

    private String buildInputFilePath() {
        return new StringBuilder()
                .append("file://")
                .append(getInputPath())
                .append("?fileName=")
                .append(getInputFileName())
                .append("&charset=")
                .append(getCharset())
                .append("&noop=")
                .append(getNoop())
                .append("&delete=")
                .append(getDelete())
                .toString();
    }

    private String buildProcessedFilePath() {
        return new StringBuilder()
                .append("file://")
                .append(getProcessedPath())
                .append("?fileName=")
                .append(getProcessedFileName())
                .append("&charset=")
                .append(getCharset())
                .toString();
    }

    private String getInputPath() {
        return propertiesReader.getProperty("file.input.path");
    }

    private String getProcessedPath() {
        return propertiesReader.getProperty("file.processed.path");
    }

    private String getInputFileName() {
        return getInputFileNameWithoutExtension().concat(getFileExtension());
    }

    private String getProcessedFileName() {
        return new StringBuilder()
                .append(getProcessedFileNameWithoutExtension())
                .append(".")
                .append(getCurrentDate())
                .append(getFileExtension())
                .toString();
    }

    private String getInputFileNameWithoutExtension() {
        return propertiesReader.getProperty("file.input.filename");
    }

    private String getProcessedFileNameWithoutExtension() {
        return propertiesReader.getProperty("file.processed.filename");
    }

    private String getFileExtension() {
        return propertiesReader.getProperty("file.file-extension");
    }

    private String getCharset() {
        return propertiesReader.getProperty("file.charset");
    }

    private String getNoop() {
        return propertiesReader.getProperty("file.noop");
    }

    private String getDelete() {
        return propertiesReader.getProperty("file.delete");
    }

    private String getCurrentDate() {
        return LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

}
