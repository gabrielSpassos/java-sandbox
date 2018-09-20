package br.com.gabrielspassos.poc;

import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class ReaderWriterPocApplicationTests {

	@Test
	public void mustSeparateString() {
		String msg = "001ç1234567891234çDiegoç50000 001ç3245678865434çRenatoç40000.99\n" +
				"002ç2345675434544345çJose da SilvaçRural 002ç2345675433444345çEduardo PereiraçRural\n" +
				"003ç10ç[1-10-100,2-30-2.50,3-40-3.10]çDiego\n" +
				"003ç08ç[1-34-10,2-33-1.50,3-40-0.10]çRenato";

		Pattern salesmanPattern = Pattern.compile("001ç([0-9]+)ç([ a-zA-Z á]+)ç([-+]?[0-9]*\\.?[0-9]*)");
		Matcher salesmanMatcher = salesmanPattern.matcher(msg);

		// if we find a match, get the group
		if (salesmanMatcher.find())
		{
			int count = salesmanMatcher.groupCount();
			String cpf = salesmanMatcher.group(1);
			String name = salesmanMatcher.group(2);
			String salary = salesmanMatcher.group(3);
			System.out.println(count);
			System.out.println(cpf + name + salary);
		}
	}

}
