package com.gabrielspassos.poc.builders;

import com.gabrielspassos.poc.dto.AccountDTO;
import com.gabrielspassos.poc.dto.BankingEmployeeDTO;
import com.gabrielspassos.poc.dto.EmployeeDTO;
import com.gabrielspassos.poc.dto.SavingAccountDTO;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ClassAsStringConverterBuilderTest {

    private static ClassAsStringConverterBuilder classAsStringConverterBuilder;

    @BeforeAll
    static void setup() {
        classAsStringConverterBuilder = ClassAsStringConverterBuilder.getBuilder();
    }

    @Test
    void shouldReturnConverterClassAsString() {
        String expected =
                "package com.gabrielspassos.poc.loaders;\n" +
                "\n" +
                "import com.gabrielspassos.poc.dto.AccountDTO;\n" +
                "import com.gabrielspassos.poc.dto.SavingAccountDTO;\n" +
                "\n" +
                "public class ConverterFromAccountDTOToSavingAccountDTO implements InMemoryClass<AccountDTO, SavingAccountDTO> {\n" +
                "\tpublic SavingAccountDTO convert(AccountDTO input) {\n" +
                "\t\tSavingAccountDTO output = new SavingAccountDTO();\n" +
                "\t\toutput.setAgency(input.getAgency());\n" +
                "\t\toutput.setNumber(input.getNumber());\n" +
                "\t\toutput.setDigit(input.getDigit());\n" +
                "\t\treturn output;\n" +
                "\t}\n" +
                "}\n";
        AccountDTO account = new AccountDTO("0001", "12345", 6L);

        String converterClassAsString = classAsStringConverterBuilder.createConverterClassAsString(
                "ConverterFromAccountDTOToSavingAccountDTO", account, SavingAccountDTO.class);

        assertNotNull(converterClassAsString);
        assertEquals(expected, converterClassAsString);
    }

    @Test
    void shouldReturnConverterClassWithBooleanValuesAsString() {
        String expected =
                "package com.gabrielspassos.poc.loaders;\n" +
                "\n" +
                "import com.gabrielspassos.poc.dto.BankingEmployeeDTO;\n" +
                "import com.gabrielspassos.poc.dto.EmployeeDTO;\n" +
                "\n" +
                "public class ConverterFromBankingEmployeeDTOToEmployeeDTO implements InMemoryClass<BankingEmployeeDTO, EmployeeDTO> {\n" +
                "\tpublic EmployeeDTO convert(BankingEmployeeDTO input) {\n" +
                "\t\tEmployeeDTO output = new EmployeeDTO();\n" +
                "\t\toutput.setName(input.getName());\n" +
                "\t\toutput.setAge(input.getAge());\n" +
                "\t\toutput.setFirstName(input.getFirstName());\n" +
                "\t\toutput.setFirstName(input.getName());\n" +
                "\t\toutput.setContractNumber(input.getContractNumber());\n" +
                "\t\toutput.setContractActive(input.getContractActive());\n" +
                "\t\treturn output;\n" +
                "\t}\n" +
                "}\n";
        BankingEmployeeDTO bankingEmployeeDTO
                = new BankingEmployeeDTO("Felipe", 38, 848343L, false, 2.5);

        String converterClassAsString = classAsStringConverterBuilder.createConverterClassAsString(
                "ConverterFromBankingEmployeeDTOToEmployeeDTO", bankingEmployeeDTO, EmployeeDTO.class);

        assertNotNull(converterClassAsString);
        assertEquals(expected, converterClassAsString);
    }

    @Test
    void shouldReturnConverterClassName() {
        AccountDTO account = new AccountDTO("0001", "12345", 6L);

        String converterClassName
                = classAsStringConverterBuilder.getConverterClassName(account, SavingAccountDTO.class);

        assertNotNull(converterClassName);
        assertEquals("ConverterFromAccountDTOToSavingAccountDTO", converterClassName);
    }

}