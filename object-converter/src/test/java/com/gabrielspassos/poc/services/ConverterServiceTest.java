package com.gabrielspassos.poc.services;

import com.gabrielspassos.poc.exceptions.ErrorToInstantiateClassException;
import com.gabrielspassos.poc.exceptions.InvalidClassConstructorException;
import com.gabrielspassos.poc.exceptions.NoParametersException;
import com.gabrielspassos.poc.services.dto.AccountDTO;
import com.gabrielspassos.poc.services.dto.BankingEmployeeDTO;
import com.gabrielspassos.poc.services.dto.ClassWithoutDefaultConstructorDTO;
import com.gabrielspassos.poc.services.dto.EmployeeDTO;
import com.gabrielspassos.poc.services.dto.PersonDTO;
import com.gabrielspassos.poc.services.dto.SavingAccountDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ConverterServiceTest {

    private ConverterService converterService;

    @BeforeEach
    void setup() {
        this.converterService = ConverterService.getConverterService();
    }

    @Test
    void shouldConvertClass() {
        AccountDTO account = new AccountDTO("0001", "12345", 6L);

        SavingAccountDTO converted = converterService.convert(account, SavingAccountDTO.class);

        assertNotNull(converted);
        assertEquals("0001", converted.getAgency());
        assertEquals("12345", converted.getNumber());
        assertEquals(6L, converted.getDigit());
        assertNull(converted.getAmount());
    }

    @Test
    void shouldConvertExtendedClass() {
        PersonDTO personDTO = new PersonDTO("John", 38);

        EmployeeDTO converted = converterService.convert(personDTO, EmployeeDTO.class);

        assertNotNull(converted);
        assertEquals("John", converted.getName());
        assertEquals(38, converted.getAge());
        assertNull(converted.getContractNumber());
        assertNull(converted.getContractActive());
    }

    @Test
    void shouldConvertChildClassToParent() {
        EmployeeDTO employeeDTO = new EmployeeDTO("Maria", 24, 548954L, true);

        PersonDTO converted = converterService.convert(employeeDTO, PersonDTO.class);

        assertNotNull(converted);
        assertEquals("Maria", converted.getName());
        assertEquals(24, converted.getAge());
    }

    @Test
    void shouldConvertChildClassToParentWithMultipleExtensions() {
        BankingEmployeeDTO bankingEmployeeDTO = new BankingEmployeeDTO("Felipe", 38, 848343L, false, 2.5);

        EmployeeDTO converted = converterService.convert(bankingEmployeeDTO, EmployeeDTO.class);

        assertNotNull(converted);
        assertEquals("Felipe", converted.getName());
        assertEquals(38, converted.getAge());
        assertEquals(848343L, converted.getContractNumber());
        assertFalse(converted.getContractActive());
    }

    @Test
    void shouldThrowErrorToInvalidClassWithoutDefaultConstructor() {
        AccountDTO account = new AccountDTO("0001", "12345", 6L);
        var expectedMessage = "com.gabrielspassos.poc.services.dto.ClassWithoutDefaultConstructorDTO doesn't contains a valid default constructor";

        InvalidClassConstructorException exception = assertThrowsExactly(InvalidClassConstructorException.class,
                () -> converterService.convert(account, ClassWithoutDefaultConstructorDTO.class));

        assertEquals(expectedMessage, exception.getErrorMessage());
    }

    @Test
    void shouldThrowErrorOnInvalidInputObject() {
        NoParametersException exception = assertThrowsExactly(NoParametersException.class,
                () -> converterService.convert(null, ClassWithoutDefaultConstructorDTO.class));

        assertEquals("Can not use null value", exception.getErrorMessage());
    }

    @Test
    void shouldThrowErrorOnInvalidInputClass() {
        AccountDTO account = new AccountDTO("0001", "12345", 6L);

        NoParametersException exception = assertThrowsExactly(NoParametersException.class,
                () -> converterService.convert(account, null));

        assertEquals("Can not use null value", exception.getErrorMessage());
    }

}