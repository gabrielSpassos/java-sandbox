package com.gabrielspassos;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.Network;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.postgresql.PostgreSQLContainer;

@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BaseApplicationTest {

    private static final Network network = Network.newNetwork();

    private static final PostgreSQLContainer postgresContainer = new PostgreSQLContainer("postgres:18-alpine")
            .withDatabaseName("chaos")
            .withUsername("user")
            .withPassword("pass")
            .withInitScript("schema.sql")
            .withNetwork(network)
            .withNetworkAliases("postgres")
            .withExposedPorts(5432);

    static {
        postgresContainer.start();
    }

    @DynamicPropertySource
    static void overrideProps(DynamicPropertyRegistry registry) {
        registry.add(
                "spring.datasource.url",
                () -> String.format(
                        "jdbc:postgresql://%s:%d/%s",
                        postgresContainer.getHost(),
                        postgresContainer.getFirstMappedPort(),
                        postgresContainer.getDatabaseName()
                )
        );

        registry.add(
                "spring.datasource.username",
                postgresContainer::getUsername
        );

        registry.add(
                "spring.datasource.password",
                postgresContainer::getPassword
        );
    }

}
