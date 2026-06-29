package com.gabrielspassos.controller.v1;

import com.gabrielspassos.BaseApplicationTest;
import eu.rekawek.toxiproxy.model.ToxicDirection;
import eu.rekawek.toxiproxy.model.toxic.Bandwidth;
import eu.rekawek.toxiproxy.model.toxic.Latency;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
class UserControllerChaosTest extends BaseApplicationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldCreateUserEvenWithDbLatency() throws Exception {
        Latency latency = getDbProxy().toxics().latency("db-latency", ToxicDirection.DOWNSTREAM, 5000);

        long start = System.currentTimeMillis();
        mockMvc.perform(post("/v1/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                            {
                                "name":"chaos-test-user-with-latency"
                            }
                        """))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").isString())
                .andExpect(jsonPath("$.name").value("chaos-test-user-with-latency"))
                .andExpect(jsonPath("$.createdAt").isString());
        long withLatencyTimeTaken = System.currentTimeMillis() - start;

        latency.remove();

        long startWithoutLatency = System.currentTimeMillis();
        mockMvc.perform(post("/v1/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                            {
                                "name":"chaos-test-user-without-latency"
                            }
                        """))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").isString())
                .andExpect(jsonPath("$.name").value("chaos-test-user-without-latency"))
                .andExpect(jsonPath("$.createdAt").isString());
        long withoutLatencyTimeTaken = System.currentTimeMillis() - startWithoutLatency;

        assertTrue(withLatencyTimeTaken > withoutLatencyTimeTaken);
    }

    @Test
    void shouldFailToCreateUserWithConnectionFailure() throws Exception {
        getDbProxy().disable();

        mockMvc.perform(post("/v1/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                            {
                                "name":"chaos-test-user-with-connection-disabled"
                            }
                        """))
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.message").value("Unexpected error"))
                .andExpect(jsonPath("$.code").value("UNEXPECTED_ERROR"));

        getDbProxy().enable();

        mockMvc.perform(post("/v1/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                            {
                                "name":"chaos-test-user-with-connection-enabled"
                            }
                        """))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").isString())
                .andExpect(jsonPath("$.name").value("chaos-test-user-with-connection-enabled"))
                .andExpect(jsonPath("$.createdAt").isString());
    }

    @Test
    void shouldCreateUserWithSlownessAtNetwork() throws Exception {
        Bandwidth bandwidth = getDbProxy().toxics()
                .bandwidth("slow-network", ToxicDirection.DOWNSTREAM, 100);//100 kb/s

        long start = System.currentTimeMillis();
        mockMvc.perform(post("/v1/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                            {
                                "name":"chaos-test-user-with-slow-network"
                            }
                        """))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").isString())
                .andExpect(jsonPath("$.name").value("chaos-test-user-with-slow-network"))
                .andExpect(jsonPath("$.createdAt").isString());
        long withSlownessTimeTaken = System.currentTimeMillis() - start;

        bandwidth.remove();

        long startWithoutLatency = System.currentTimeMillis();
        mockMvc.perform(post("/v1/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                            {
                                "name":"chaos-test-user-without-slow-network"
                            }
                        """))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").isString())
                .andExpect(jsonPath("$.name").value("chaos-test-user-without-slow-network"))
                .andExpect(jsonPath("$.createdAt").isString());
        long withoutSlownessTimeTaken = System.currentTimeMillis() - startWithoutLatency;

        assertTrue(withSlownessTimeTaken > withoutSlownessTimeTaken);
    }

}