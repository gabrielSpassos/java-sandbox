package br.com.gabrielspassos.poc;

import br.com.gabrielspassos.poc.route.AnalisisRoute;
import br.com.gabrielspassos.poc.route.DecoderRoute;
import org.apache.camel.main.Main;

public class ReaderWriterPocApplication extends Main {

	public static void main(String[] args) throws Exception {
		ReaderWriterPocApplication main = new ReaderWriterPocApplication();
		main.addRouteBuilder(new DecoderRoute());
		main.addRouteBuilder(new AnalisisRoute());
		main.run(args);
	}
}
