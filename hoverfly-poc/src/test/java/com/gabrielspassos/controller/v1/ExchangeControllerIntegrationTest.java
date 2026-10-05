package com.gabrielspassos.controller.v1;

import com.gabrielspassos.BaseApplicationTest;
import com.gabrielspassos.controller.v1.response.UserResponse;
import io.specto.hoverfly.junit.core.Hoverfly;
import io.specto.hoverfly.junit5.HoverflyExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import static io.specto.hoverfly.junit.core.SimulationSource.dsl;
import static io.specto.hoverfly.junit.dsl.HoverflyDsl.service;
import static io.specto.hoverfly.junit.dsl.ResponseCreators.success;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@ExtendWith(HoverflyExtension.class)
class ExchangeControllerIntegrationTest extends BaseApplicationTest {

    @Autowired
    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void shouldFetchExchange(Hoverfly hoverfly) throws Exception {
        hoverfly.simulate(
                dsl(
                        service("cdn.jsdelivr.net")
                                .get("/npm/@fawazahmed0/currency-api@latest/v1/currencies/usd.json")
                                .willReturn(
                                        success(
                                                "{ \"date\": \"2026-10-05\", \"usd\": { \"brl\": 5.00095935 } }",
                                                "application/json"
                                        )
                                )
                )
        );

        var userId = createUser("it-test-fetch-exchange");

        var path = "/v1/users/%s/exchanges/usd/brl".formatted(userId);

        mockMvc.perform(post(path).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.date").value("2026-10-05"))
                .andExpect(jsonPath("$.usd").value("1"))
                .andExpect(jsonPath("$.brl").value("5.0"));
    }

    private String createUser(String name) throws Exception {
        MvcResult createResult = mockMvc.perform(post("/v1/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                            {
                                "name":"%s"
                            }
                        """.formatted(name)))
                .andExpect(status().isCreated())
                .andReturn();

        String responseBody = createResult.getResponse().getContentAsString();
        UserResponse response = objectMapper.readValue(responseBody, UserResponse.class);

        return response.id();
    }

}
