package com.gabrielspassos.poc.mappers;

import com.gabrielspassos.poc.dto.AccountDTO;
import com.gabrielspassos.poc.dto.AnimalDTO;
import com.gabrielspassos.poc.dto.BankingEmployeeDTO;
import com.gabrielspassos.poc.dto.EmployeeDTO;
import com.gabrielspassos.poc.dto.PersonDTO;
import com.gabrielspassos.poc.dto.SavingAccountDTO;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryClassObjectConverterMapperTest {

    private static InMemoryClassObjectConverterMapper inMemoryClassObjectConverterMapper;

    @BeforeAll
    static void setup() {
        inMemoryClassObjectConverterMapper = InMemoryClassObjectConverterMapper.getMapper();
    }

    @Test
    void shouldConvert() {
        AccountDTO account = new AccountDTO("0001", "12345", 6L);

        SavingAccountDTO converted = inMemoryClassObjectConverterMapper.convert(account, SavingAccountDTO.class);

        assertNotNull(converted);
        assertEquals("0001", converted.getAgency());
        assertEquals("12345", converted.getNumber());
        assertEquals(6L, converted.getDigit());
        assertNull(converted.getAmount());
    }

    @Test
    void shouldConvertExtendedClass() {
        PersonDTO personDTO = new PersonDTO("John", 38);

        EmployeeDTO converted = inMemoryClassObjectConverterMapper.convert(personDTO, EmployeeDTO.class);

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

        PersonDTO converted = inMemoryClassObjectConverterMapper.convert(employeeDTO, PersonDTO.class);

        assertNotNull(converted);
        assertEquals("Maria", converted.getName());
        assertEquals(24, converted.getAge());
    }

    @Test
    void shouldConvertChildClassToParentWithMultipleExtensions() {
        BankingEmployeeDTO bankingEmployeeDTO = new BankingEmployeeDTO("Felipe", 38, 848343L, false, 2.5);

        EmployeeDTO converted = inMemoryClassObjectConverterMapper.convert(bankingEmployeeDTO, EmployeeDTO.class);

        assertNotNull(converted);
        assertEquals("Felipe", converted.getName());
        assertEquals(38, converted.getAge());
        assertEquals(848343L, converted.getContractNumber());
        assertFalse(converted.getContractActive());
    }

    @Test
    void shouldConvertObjectWithAnnotation() {
        PersonDTO personDTO = new PersonDTO("John", 38);

        AnimalDTO converted = inMemoryClassObjectConverterMapper.convert(personDTO, AnimalDTO.class);

        assertNotNull(converted);
        assertEquals("John", converted.getSpecies());
    }

}