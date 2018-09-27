package br.com.gabrielspassos.poc.processor;

import br.com.gabrielspassos.poc.config.PropertiesReader;
import org.apache.camel.Processor;

public class ProcessorFactory {

    private static final PropertiesReader propertiesReader;

    static {
        propertiesReader = PropertiesReader.getInstance();
    }

    public static Processor createSalesmanProcessor() {
        if (FileType.contains(propertiesReader.getProperty("flat-file.input.type"))) {
            return new SalesmanFlatFileProcessor();
        }
        throw new IllegalStateException("File type doesn't exist.");
    }

    public static Processor createCustomerProcessor() {
        if (FileType.contains(propertiesReader.getProperty("flat-file.input.type"))) {
            return new CustomerFlatFileProcessor();
        }
        throw new IllegalStateException("File type doesn't exist.");
    }

    public static Processor createSaleProcessor() {
        if (FileType.contains(propertiesReader.getProperty("flat-file.input.type"))) {
            return new SaleFlatFileProcessor();
        }
        throw new IllegalStateException("File type doesn't exist.");
    }

}

