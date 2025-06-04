package com.gabrielspassos;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Testcontainers
class MySqlConnectorTest {

    @Container
    private static MySQLContainer mySQLContainer = new MySQLContainer<>(DockerImageName.parse("arm64v8/mysql")
            .asCompatibleSubstituteFor("mysql"))
            .withUsername("root")
            .withPassword("")
            .withExposedPorts(3306)
            .withInitScript("schema.sql");

    @BeforeAll
    public static void setUp() {
        mySQLContainer.start();
    }

    @Test
    void testMySQLContainerIsRunning() {
        assertTrue(mySQLContainer.isRunning());
    }

    @Test
    void shouldInsertAndGet() {
        String address = mySQLContainer.getHost();
        String port = mySQLContainer.getFirstMappedPort().toString();
        String schema = mySQLContainer.getDatabaseName();
        String username = mySQLContainer.getUsername();
        String password = mySQLContainer.getPassword();

        MySqlConnector underTest = new MySqlConnector(address, port, schema, username, password);

        boolean insertResult = underTest.insert("foo", "bar");
        assertTrue(insertResult);

        String getResult = underTest.get("foo");
        assertEquals("bar", getResult);
    }
}