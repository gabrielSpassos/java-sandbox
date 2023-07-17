package com.gabrielspassos.poc.unsafe;

import com.gabrielspassos.poc.dto.AccountDTO;
import com.gabrielspassos.poc.dto.AnimalDTO;
import com.gabrielspassos.poc.dto.BankingEmployeeDTO;
import com.gabrielspassos.poc.dto.ClassWithoutDefaultConstructorDTO;
import com.gabrielspassos.poc.dto.EmployeeDTO;
import com.gabrielspassos.poc.dto.PersonDTO;
import com.gabrielspassos.poc.dto.SavingAccountDTO;
import com.gabrielspassos.poc.unsafe.exceptions.NoParametersException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

class UnsafeObjectConverterMapperTest {

    private static UnsafeObjectConverterMapper unsafeObjectConverterMapper;

    @BeforeAll
    static void setup() {
        unsafeObjectConverterMapper = UnsafeObjectConverterMapper.getUnsafeObjectConverterMapper();
    }

    @Test
    void shouldConvertClass() {
        AccountDTO account = new AccountDTO("0001", "12345", 6L);

        SavingAccountDTO converted = unsafeObjectConverterMapper.convert(account, SavingAccountDTO.class);

        assertNotNull(converted);
        assertEquals("0001", converted.getAgency());
        assertEquals("12345", converted.getNumber());
        assertEquals(6L, converted.getDigit());
        assertNull(converted.getAmount());
    }

    @Test
    void shouldConvertExtendedClass() {
        PersonDTO personDTO = new PersonDTO("John", 38);

        EmployeeDTO converted = unsafeObjectConverterMapper.convert(personDTO, EmployeeDTO.class);

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

        PersonDTO converted = unsafeObjectConverterMapper.convert(employeeDTO, PersonDTO.class);

        assertNotNull(converted);
        assertEquals("Maria", converted.getName());
        assertEquals(24, converted.getAge());
    }

    @Test
    void shouldConvertChildClassToParentWithMultipleExtensions() {
        BankingEmployeeDTO bankingEmployeeDTO = new BankingEmployeeDTO("Felipe", 38, 848343L, false, 2.5);

        EmployeeDTO converted = unsafeObjectConverterMapper.convert(bankingEmployeeDTO, EmployeeDTO.class);

        assertNotNull(converted);
        assertEquals("Felipe", converted.getName());
        assertEquals(38, converted.getAge());
        assertEquals(848343L, converted.getContractNumber());
        assertFalse(converted.getContractActive());
    }

    @Test
    void shouldConvertObjectWithAnnotation() {
        PersonDTO personDTO = new PersonDTO("John", 38);

        AnimalDTO converted = unsafeObjectConverterMapper.convert(personDTO, AnimalDTO.class);

        assertNotNull(converted);
        assertEquals("John", converted.getSpecies());
    }

    @Test
    void shouldThrowErrorOnInvalidInputObject() {
        NoParametersException exception = assertThrowsExactly(NoParametersException.class,
                () -> unsafeObjectConverterMapper.convert(null, ClassWithoutDefaultConstructorDTO.class));

        assertEquals("Can not use null value", exception.getErrorMessage());
    }

    @Test
    void shouldThrowErrorOnInvalidInputClass() {
        AccountDTO account = new AccountDTO("0001", "12345", 6L);

        NoParametersException exception = assertThrowsExactly(NoParametersException.class,
                () -> unsafeObjectConverterMapper.convert(account, null));

        assertEquals("Can not use null value", exception.getErrorMessage());
    }

}