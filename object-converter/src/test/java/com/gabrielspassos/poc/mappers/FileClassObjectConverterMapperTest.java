package com.gabrielspassos.poc.mappers;

import com.gabrielspassos.poc.dto.AccountDTO;
import com.gabrielspassos.poc.dto.AnimalDTO;
import com.gabrielspassos.poc.dto.BankingEmployeeDTO;
import com.gabrielspassos.poc.dto.EmployeeDTO;
import com.gabrielspassos.poc.dto.PersonDTO;
import com.gabrielspassos.poc.dto.SavingAccountDTO;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

class FileClassObjectConverterMapperTest {

    private static FileClassObjectConverterMapper fileClassObjectConverterMapper;

    @BeforeAll
    static void setup() {
        fileClassObjectConverterMapper = FileClassObjectConverterMapper.getMapper();
    }

    @Test
    void shouldReturnConverterClassAsString() {
        String expected =
                "import com.gabrielspassos.poc.dto.AccountDTO;\n" +
                "import com.gabrielspassos.poc.dto.SavingAccountDTO;\n" +
                "\n" +
                "public class ConverterFromAccountDTOToSavingAccountDTO {\n" +
                "\tpublic SavingAccountDTO convert(AccountDTO input) {\n" +
                "\t\tSavingAccountDTO output = new SavingAccountDTO();\n" +
                "\t\toutput.setAgency(input.getAgency());\n" +
                "\t\toutput.setNumber(input.getNumber());\n" +
                "\t\toutput.setDigit(input.getDigit());\n" +
                "\t\treturn output;\n" +
                "\t}\n" +
                "}\n";
        AccountDTO account = new AccountDTO("0001", "12345", 6L);

        String converterClassAsString = fileClassObjectConverterMapper.createConverterClassAsString(
                "ConverterFromAccountDTOToSavingAccountDTO", account, SavingAccountDTO.class);

        assertNotNull(converterClassAsString);
        assertEquals(expected, converterClassAsString);
    }

    @Test
    void shouldReturnConverterClassName() {
        AccountDTO account = new AccountDTO("0001", "12345", 6L);

        String converterClassName
                = fileClassObjectConverterMapper.getConverterClassName(account, SavingAccountDTO.class);

        assertNotNull(converterClassName);
        assertEquals("ConverterFromAccountDTOToSavingAccountDTO", converterClassName);
    }

    @Test
    void shouldConvert() {
        AccountDTO account = new AccountDTO("0001", "12345", 6L);

        SavingAccountDTO converted = fileClassObjectConverterMapper.convert(account, SavingAccountDTO.class);

        assertNotNull(converted);
        assertEquals("0001", converted.getAgency());
        assertEquals("12345", converted.getNumber());
        assertEquals(6L, converted.getDigit());
        assertNull(converted.getAmount());
    }

    @Test
    void shouldConvertExtendedClass() {
        PersonDTO personDTO = new PersonDTO("John", 38);

        EmployeeDTO converted = fileClassObjectConverterMapper.convert(personDTO, EmployeeDTO.class);

        assertNotNull(converted);
        assertEquals("John", converted.getFirstName());
        assertEquals("John", converted.getName());
        assertEquals(38, converted.getAge());
        assertNull(converted.getContractNumber());
        assertNull(converted.getContractActive());
    }

    @Test
    void shouldConvertChildClassToParent() {
        EmployeeDTO employeeDTO = new EmployeeDTO("Maria", 24, 548954L, true);

        PersonDTO converted = fileClassObjectConverterMapper.convert(employeeDTO, PersonDTO.class);

        assertNotNull(converted);
        assertEquals("Maria", converted.getName());
        assertEquals(24, converted.getAge());
    }

    @Test
    void shouldConvertChildClassToParentWithMultipleExtensions() {
        BankingEmployeeDTO bankingEmployeeDTO = new BankingEmployeeDTO("Felipe", 38, 848343L, false, 2.5);

        EmployeeDTO converted = fileClassObjectConverterMapper.convert(bankingEmployeeDTO, EmployeeDTO.class);

        assertNotNull(converted);
        assertEquals("Felipe", converted.getName());
        assertEquals(38, converted.getAge());
        assertEquals(848343L, converted.getContractNumber());
        assertFalse(converted.getContractActive());
    }

    @Test
    void shouldConvertObjectWithAnnotation() {
        PersonDTO personDTO = new PersonDTO("John", 38);

        AnimalDTO converted = fileClassObjectConverterMapper.convert(personDTO, AnimalDTO.class);

        assertNotNull(converted);
        assertEquals("John", converted.getSpecies());
    }

}