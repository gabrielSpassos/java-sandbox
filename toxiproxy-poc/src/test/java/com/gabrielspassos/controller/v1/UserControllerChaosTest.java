package com.gabrielspassos.controller.v1;

import com.gabrielspassos.BaseApplicationTest;
import eu.rekawek.toxiproxy.model.ToxicDirection;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
class UserControllerChaosTest extends BaseApplicationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldCreateUserEvenWithDbLatency() throws Exception {
        getDbProxy().toxics().latency("db-latency", ToxicDirection.DOWNSTREAM, 5000);

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
    }

}