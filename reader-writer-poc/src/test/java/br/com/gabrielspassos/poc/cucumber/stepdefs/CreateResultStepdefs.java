package br.com.gabrielspassos.poc.cucumber.stepdefs;

import cucumber.api.java8.En;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Assert;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CreateResultStepdefs extends CamelTestSupport implements En {


    public CreateResultStepdefs() {

        Given("^a relatory.dat file on data/in folder$", () -> {
            try{
                File file = new File("data/in/relatory.dat");
                BufferedWriter writer = new BufferedWriter(new FileWriter(file));
                writer.write(Resources.MESSAGE);
                writer.close();
            }catch(Exception e){
                Assert.fail();
            }
        });

        Then("^must create a result with correct information$", () -> {
            Thread.sleep(5000);
            String file = String.format("data\\out\\result.%s.done.dat", getCurrentDate());
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String currentLine = reader.readLine();
            reader.close();

            assertEquals(Resources.EXPECTED_RESULT, currentLine);
        });
    }

    private String getCurrentDate() {
        return LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    private class Resources {

        static final String MESSAGE =
                "001ç1234567891234çDiegoç50000\n" +
                "001ç3245678865434çRenatoç40000.99\n" +
                "002ç2345675434544345çJose da SilvaçRural\n" +
                "002ç2345675433444345çEduardo PereiraçRural\n" +
                "003ç10ç[1-10-100,2-30-2.50,3-40-3.10]çDiego\n" +
                "003ç08ç[1-34-10,2-33-1.50,3-40-0.10]çRenato";

        static final String EXPECTED_RESULT = "Result{custumersNumber=2, sellersNumber=2, idMostExpensiveSale=10, worstSalesmanName='Renato'}";
    }
}
