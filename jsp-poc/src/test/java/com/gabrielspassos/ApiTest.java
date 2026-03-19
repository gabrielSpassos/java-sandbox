package com.gabrielspassos;

import org.apache.hc.client5.http.fluent.Request;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ApiTest {

    private static Thread serverThread;

    @BeforeAll
    static void startServer() {
        serverThread = new Thread(() -> {
            try {
                Main.main();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        serverThread.start();

        try {
            Thread.sleep(2000); // wait server
        } catch (InterruptedException ignored) {}
    }

    @Test
    void shouldReturnJson() throws Exception {
        String response = Request.get("http://localhost:8080/api?f1=hello&f2=world")
                .execute()
                .returnContent()
                .asString();

        assertTrue(response.contains("hello"));
        assertTrue(response.contains("world"));
        assertTrue(response.contains("Processed successfully"));
    }
}
