package br.com.gabrielspassos.poc;

import br.com.gabrielspassos.poc.model.Result;
import br.com.gabrielspassos.poc.route.WriterRoute;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.file.GenericFile;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class WriterRouteTest extends CamelTestSupport {

    @Override
    public RouteBuilder createRouteBuilder() {
        return new WriterRoute();
    }

    @Test
    public void mustWriteFile() {
        template.sendBody(
                "direct:writer",
                buildResult()
        );

        Exchange exchange = consumer.receive("file://data/out/?fileName=result.${date:now:yyyy-MM-dd}.done.dat&charset=utf-8");
        String expectedFileOutputFileName = String.format("result.%s.done.dat", getCurrentDate());
        GenericFile file = (GenericFile) exchange.getIn().getBody();

        assertEquals(expectedFileOutputFileName, file.getFileName());
        assertEquals("data/out", file.getEndpointPath());
    }

    private Result buildResult() {
        Result result = new Result();
        result.setCustumersNumber(5);
        result.setIdMostExpensiveSale(1L);
        result.setSellersNumber(2);
        result.setWorstSalesmanName("Gabriel");
        return result;
    }

    private String getCurrentDate() {
        return LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }
}
