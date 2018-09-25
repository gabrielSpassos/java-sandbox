package br.com.gabrielspassos.poc.route;

import org.apache.camel.builder.RouteBuilder;

public class WriterRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("direct:writer")
                .routeId("writer")
                .convertBodyTo(String.class)
                .to("file://data/out/?fileName=result.${date:now:yyyy-MM-dd}.done.dat&charset=utf-8");
    }
}
