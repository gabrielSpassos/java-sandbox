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

//    @Container
//    private final static MySQLContainer mySQLContainer = new MySQLContainer<>(DockerImageName.parse("arm64v8/mysql")
//            .asCompatibleSubstituteFor("mysql"));

    @Container
    public static MySQLContainer mySQLContainer = new MySQLContainer<>(DockerImageName.parse("arm64v8/mysql").asCompatibleSubstituteFor("mysql"))
            .withExposedPorts(3306)
            .withInitScript("schema.sql")
            .withConnectTimeoutSeconds(3600)
            .withStartupTimeoutSeconds(3600)
            .withStartupAttempts(10);

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
//        String schema = mySQLContainer.getDatabaseName();
//        String username = mySQLContainer.getUsername();
//        String password = mySQLContainer.getPassword();
        String schema = "test";
        String username = "test";
        String password = "test";

        MySqlConnector underTest = new MySqlConnector(address, port, schema, username, password);

        boolean insertResult = underTest.insert("foo", "bar");
        assertTrue(insertResult);

        String getResult = underTest.get("foo");
        assertEquals("bar", getResult);
    }
}