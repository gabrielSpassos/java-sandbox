package com.gabrielspassos.poc.mappers;

import com.gabrielspassos.poc.dto.BankingEmployeeDTO;
import com.gabrielspassos.poc.dto.EmployeeDTO;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;

public class PerformanceTest {

    private static InMemoryClassObjectConverterMapper inMemoryClassObjectConverterMapper;
    private static FileClassObjectConverterMapper fileClassObjectConverterMapper;
    private static ObjectConverterMapper objectConverterMapper;

    @BeforeAll
    static void setup() {
        inMemoryClassObjectConverterMapper = InMemoryClassObjectConverterMapper.getMapper();
        fileClassObjectConverterMapper = FileClassObjectConverterMapper.getMapper();
        objectConverterMapper = ObjectConverterMapper.getObjectConverterMapper();
    }

    @Test
    void shouldTestPerformance() {
        int iterations = 10000;
        BankingEmployeeDTO bankingEmployeeDTO = new BankingEmployeeDTO("Felipe", 38, 848343L, false, 2.5);

        ZonedDateTime startWithObject = ZonedDateTime.now();
        for (int i = 0; i < iterations; i++) {
            EmployeeDTO convertedWithObjectConverter = objectConverterMapper.convert(bankingEmployeeDTO, EmployeeDTO.class);
        }
        ZonedDateTime finishWithObject = ZonedDateTime.now();
        System.out.print("Time in milliseconds with object converter: ");
        System.out.println(ChronoUnit.MILLIS.between(startWithObject, finishWithObject));

        ZonedDateTime startInMemory = ZonedDateTime.now();
        for (int i = 0; i < iterations; i++) {
            EmployeeDTO convertedInMemoryClassConverter = inMemoryClassObjectConverterMapper.convert(bankingEmployeeDTO, EmployeeDTO.class);
        }
        ZonedDateTime finishInMemory = ZonedDateTime.now();
        System.out.print("Time in milliseconds with in memory converter: ");
        System.out.println(ChronoUnit.MILLIS.between(startInMemory, finishInMemory));

        ZonedDateTime startWithFile = ZonedDateTime.now();
        for (int i = 0; i < iterations; i++) {
            EmployeeDTO convertedWithFileClassConverter = fileClassObjectConverterMapper.convert(bankingEmployeeDTO, EmployeeDTO.class);
        }
        ZonedDateTime finishWithFile = ZonedDateTime.now();
        System.out.print("Time in milliseconds with class converter: ");
        System.out.println(ChronoUnit.MILLIS.between(startWithFile, finishWithFile));
    }

}
