package br.com.gabrielspassos.poc;

import br.com.gabrielspassos.poc.route.ReaderRoute;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Test;

public class ReaderRouteTest extends CamelTestSupport {

    @Override
    public RouteBuilder createRouteBuilder() {
        return new ReaderRoute();
    }

    @Test
    public void mustPutInformationAtExchangeBody() {
        template.sendBody(
                "file://test/data/in/?fileName=relatory.dat&charset=utf-8&noop=true&delete=true",
                Resources.MESSAGE
        );

        Exchange exchange = consumer.receive("direct:decoder");
        String body = exchange.getIn().getBody(String.class);
        assertNotNull(body);
    }

    private class Resources {
        static final String MESSAGE =
                "001ç1234567891234çDiegoç50000\n" +
                        "003ç08ç[1-34-10,2-33-1.50,3-40-0.10]çRenato\n" +
                        "001ç3245678865434çRenatoç40000.99\n" +
                        "002ç2345675434544345çJose da SilvaçRural\n" +
                        "001ç3245678865434çRenatoç40000.99\n" +
                        "002ç2345675433444345çEduardo PereiraçRural\n" +
                        "003ç10ç[1-10-100,2-30-2.50,3-40-3.10]çDiego\n" +
                        "003ç08ç[1-34-10,2-33-1.50,3-40-0.10]çRenato";
    }
}
