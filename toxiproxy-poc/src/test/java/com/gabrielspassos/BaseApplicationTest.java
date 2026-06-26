package com.gabrielspassos;

import eu.rekawek.toxiproxy.Proxy;
import eu.rekawek.toxiproxy.ToxiproxyClient;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.Network;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.postgresql.PostgreSQLContainer;
import org.testcontainers.toxiproxy.ToxiproxyContainer;
import org.testcontainers.utility.DockerImageName;

import java.io.IOException;

@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BaseApplicationTest {

    private static final Network network = Network.newNetwork();

    static PostgreSQLContainer postgresContainer = new PostgreSQLContainer("postgres:18-alpine")
            .withDatabaseName("chaos")
            .withUsername("user")
            .withPassword("pass")
            .withInitScript("schema.sql")
            .withNetwork(network)
            .withNetworkAliases("postgres")
            .withExposedPorts(5432);

    static ToxiproxyContainer toxiproxyContainer = new ToxiproxyContainer("ghcr.io/shopify/toxiproxy:2.12.0")
            .withNetwork(network);

    private static Proxy dbProxy;

    static {
        postgresContainer.start();
        toxiproxyContainer.start();
    }

    @BeforeAll
    static void setupProxy() throws IOException {
        final ToxiproxyClient toxiproxyClient
                = new ToxiproxyClient(toxiproxyContainer.getHost(), toxiproxyContainer.getControlPort());
        dbProxy = toxiproxyClient.createProxy("postgres", "0.0.0.0:8666", "postgres:5432");
    }

    @DynamicPropertySource
    static void overrideProps(DynamicPropertyRegistry registry) throws IOException {
        registry.add(
                "spring.datasource.url",
                () -> String.format(
                        "jdbc:postgresql://%s:%d/%s",
                        toxiproxyContainer.getHost(),
                        toxiproxyContainer.getMappedPort(8666),
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

    @AfterEach
    void cleanup() throws IOException {
        dbProxy.toxics()
                .getAll()
                .forEach(toxic -> {
                    try {
                        toxic.remove();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
    }
}
