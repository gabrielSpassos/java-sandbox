package br.com.gabrielspassos.poc;

import br.com.gabrielspassos.poc.route.AnalyzesRoute;
import br.com.gabrielspassos.poc.route.DecoderRoute;
import org.apache.camel.main.Main;

public class ReaderWriterPocApplication extends Main {

	public static void main(String[] args) throws Exception {
		ReaderWriterPocApplication main = new ReaderWriterPocApplication();
		main.addRouteBuilder(new DecoderRoute());
		main.addRouteBuilder(new AnalyzesRoute());
		main.run(args);
	}
}
