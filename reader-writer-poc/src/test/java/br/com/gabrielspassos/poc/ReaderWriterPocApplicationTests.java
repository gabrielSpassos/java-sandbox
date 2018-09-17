package br.com.gabrielspassos.poc;

import org.junit.Test;


public class ReaderWriterPocApplicationTests {

	@Test
	public void mustSeparateString() {
		String msg = "[1-10-100,2-30-2.50,3-40-3.10]";

		String[] result = msg.split("\\[|,|]");
        for (int i = 0; i < result.length; i++) {
            System.out.println(result[i]);
        }

	}
}
