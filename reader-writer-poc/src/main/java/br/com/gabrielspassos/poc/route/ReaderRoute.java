package br.com.gabrielspassos.poc.route;

import br.com.gabrielspassos.poc.config.PropertiesReader;
import org.apache.camel.builder.RouteBuilder;

public class ReaderRoute extends RouteBuilder {

    private PropertiesReader propertiesReader;

    public ReaderRoute() {
        propertiesReader = PropertiesReader.getInstance();
    }

    @Override
    public void configure() throws Exception {
        from(buildFilePath())
                .routeId("reader")
                .to("file://data/processed/?fileName=relatory.${date:now:yyyy-MM-dd}.dat&charset=utf-8")
                .convertBodyTo(String.class)
                .to("direct:decoder")
                .end();
    }

    private String buildFilePath() {
        return new StringBuilder()
                .append("file://")
                .append(getInputPath())
                .append("?fileName=")
                .append(getFileName())
                .append("&charset=utf-8&noop=true&delete=true")
                .toString();
    }

    private String getInputPath() {
        return propertiesReader.getApplicationProperties().getProperty("reader.input.path");
    }

    private String getFileName() {
        return propertiesReader.getApplicationProperties().getProperty("reader.filename");
    }
}
