package com.gabrielspassos;

import com.gabrielspassos.dto.DataDTO;
import com.gabrielspassos.dto.NestedData1DTO;
import com.gabrielspassos.dto.NestedData2DTO;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Testcontainers
@SpringBootTest
public class BaseIntegrationTests {

    @Container
    static PostgreSQLContainer<?> postgres =
            new PostgreSQLContainer<>("postgres:16-alpine")
                    .withDatabaseName("testdb")
                    .withUsername("test")
                    .withPassword("test");

    @DynamicPropertySource
    static void registerDatasourceProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);
    }

    protected DataDTO createDataDTO() {
        NestedData1DTO nestedData1 = new NestedData1DTO();
        nestedData1.setId(UUID.randomUUID().toString());
        nestedData1.setValue("Some Value");

        NestedData2DTO nestedData2A = new NestedData2DTO();
        nestedData2A.setKey(UUID.randomUUID().toString());
        nestedData2A.setAmount(BigDecimal.ONE);

        NestedData2DTO nestedData2B = new NestedData2DTO();
        nestedData2B.setKey(UUID.randomUUID().toString());
        nestedData2B.setAmount(BigDecimal.ONE);

        DataDTO dataDTO = new DataDTO();
        dataDTO.setUserId(UUID.randomUUID().toString());
        dataDTO.setAmount(BigDecimal.TEN);
        dataDTO.setIsActive(true);
        dataDTO.setAccountIds(List.of(UUID.randomUUID().toString(), UUID.randomUUID().toString()));
        dataDTO.setNestedData1(nestedData1);
        dataDTO.setNestedData2List(List.of(nestedData2A, nestedData2B));
        return dataDTO;
    }
}
