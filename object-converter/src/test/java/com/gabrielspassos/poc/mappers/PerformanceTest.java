package com.gabrielspassos.poc.mappers;

import com.gabrielspassos.poc.dto.BankingEmployeeDTO;
import com.gabrielspassos.poc.dto.EmployeeDTO;
import com.gabrielspassos.poc.memory.InMemoryClassObjectConverterMapper;
import com.gabrielspassos.poc.reflections.ObjectConverterMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Random;
import java.util.UUID;

public class PerformanceTest {

    private static InMemoryClassObjectConverterMapper inMemoryClassObjectConverterMapper;
    private static ObjectConverterMapper objectConverterMapper;
    private static Random random;

    private static final int MAX_AGE = 85;
    private static final int MIN_AGE = 16;
    private static final double MAX_BONUS = 4.9;
    private static final double MIN_BONUS = 0;

    @BeforeAll
    static void setup() {
        inMemoryClassObjectConverterMapper = InMemoryClassObjectConverterMapper.getMapper();
        objectConverterMapper = ObjectConverterMapper.getObjectConverterMapper();
        random = new Random();
    }

    @Test
    void shouldTestPerformance() {
        int iterations = 10000;
        ZonedDateTime startWithObject = ZonedDateTime.now();
        for (int i = 0; i < iterations; i++) {
            BankingEmployeeDTO bankingEmployeeDTO = buildBankingEmployee(i);
            EmployeeDTO convertedWithObjectConverter = objectConverterMapper.convert(bankingEmployeeDTO, EmployeeDTO.class);
        }
        ZonedDateTime finishWithObject = ZonedDateTime.now();
        System.out.print("Time in milliseconds with object converter: ");
        System.out.println(ChronoUnit.MILLIS.between(startWithObject, finishWithObject));

        ZonedDateTime startInMemory = ZonedDateTime.now();
        for (int i = 0; i < iterations; i++) {
            BankingEmployeeDTO bankingEmployeeDTO = buildBankingEmployee(i);
            EmployeeDTO convertedInMemoryClassConverter = inMemoryClassObjectConverterMapper.convert(bankingEmployeeDTO, EmployeeDTO.class);
        }
        ZonedDateTime finishInMemory = ZonedDateTime.now();
        System.out.print("Time in milliseconds with in memory converter: ");
        System.out.println(ChronoUnit.MILLIS.between(startInMemory, finishInMemory));
    }

    private BankingEmployeeDTO buildBankingEmployee(int interactionNumber) {
        boolean isContractActive = interactionNumber % 2 == 0;
        int age = random.nextInt(MAX_AGE - MIN_AGE) + MIN_AGE;
        double bonus = MIN_BONUS + (random.nextDouble() * (MAX_BONUS - MIN_BONUS));

        return new BankingEmployeeDTO(UUID.randomUUID().toString(), age, (long) interactionNumber, isContractActive, bonus);
    }

}
