package com.gabrielspassos;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.images.PullPolicy;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Testcontainers
class MySqlConnectorTest {

    private MySqlConnector underTest;

    @Container
    public MySQLContainer mysql = (MySQLContainer) new MySQLContainer("mysql:latest")
            .withImagePullPolicy(PullPolicy.defaultPolicy());

    @BeforeEach
    public void setUp() {
        mysql.start();
        String address = mysql.getHost();
        String port = mysql.getFirstMappedPort().toString();
        String schema = mysql.getDatabaseName();
        String username = mysql.getUsername();
        String password = mysql.getPassword();

        underTest = new MySqlConnector(address, port, schema, username, password);
    }

    @Test
    void shouldInsertAndGet() {
        boolean insertResult = underTest.insert("foo", "bar");
        assertTrue(insertResult);

        String getResult = underTest.get("foo");
        assertEquals("bar", getResult);
    }
}