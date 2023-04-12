package com.gabrielspassos.poc.mappers;

import com.gabrielspassos.poc.dto.BankingEmployeeDTO;
import com.gabrielspassos.poc.dto.EmployeeDTO;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;

public class PerformanceTest {

    private static FileClassObjectConverterMapper fileClassObjectConverterMapper;
    private static ObjectConverterMapper objectConverterMapper;

    @BeforeAll
    static void setup() {
        fileClassObjectConverterMapper = FileClassObjectConverterMapper.getMapper();
        objectConverterMapper = ObjectConverterMapper.getObjectConverterMapper();
    }

    @Test
    void shouldTestPerformance() {
        BankingEmployeeDTO bankingEmployeeDTO = new BankingEmployeeDTO("Felipe", 38, 848343L, false, 2.5);

        ZonedDateTime startWithFile = ZonedDateTime.now();
        EmployeeDTO convertedWithFileClassConverter = fileClassObjectConverterMapper.convert(bankingEmployeeDTO, EmployeeDTO.class);
        ZonedDateTime finishWithFile = ZonedDateTime.now();
        System.out.print("Time in milliseconds with class converter: ");
        System.out.println(ChronoUnit.MILLIS.between(startWithFile, finishWithFile));

        ZonedDateTime startWithObject = ZonedDateTime.now();
        EmployeeDTO convertedWithObjectConverter = objectConverterMapper.convert(bankingEmployeeDTO, EmployeeDTO.class);
        ZonedDateTime finishWithObject = ZonedDateTime.now();
        System.out.print("Time in milliseconds with object converter: ");
        System.out.println(ChronoUnit.MILLIS.between(startWithObject, finishWithObject));
    }

}
