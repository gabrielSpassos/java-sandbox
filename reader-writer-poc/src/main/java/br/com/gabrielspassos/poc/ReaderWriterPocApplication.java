package br.com.gabrielspassos.poc;

import br.com.gabrielspassos.poc.route.AnalyzesRoute;
import br.com.gabrielspassos.poc.route.DecoderRoute;
import br.com.gabrielspassos.poc.route.ReaderRoute;
import br.com.gabrielspassos.poc.route.WriterRoute;
import org.apache.camel.main.Main;

public class ReaderWriterPocApplication extends Main {

	public static void main(String[] args) throws Exception {
		ReaderWriterPocApplication main = new ReaderWriterPocApplication();
		main.addRouteBuilder(new ReaderRoute());
		main.addRouteBuilder(new DecoderRoute());
		main.addRouteBuilder(new AnalyzesRoute());
		main.addRouteBuilder(new WriterRoute());
		main.run(args);
	}
}
