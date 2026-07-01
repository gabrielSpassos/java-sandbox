package com.gabrielspassos.controller.v1;

import com.gabrielspassos.BaseApplicationTest;
import com.gabrielspassos.controller.v1.response.UserResponse;
import eu.rekawek.toxiproxy.model.ToxicDirection;
import eu.rekawek.toxiproxy.model.toxic.Timeout;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
class ExchangeControllerChaosTest extends BaseApplicationTest {

    @Autowired
    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @DynamicPropertySource
    static void overrideProps(DynamicPropertyRegistry registry) {
        registry.add("exchange.api.url", BaseApplicationTest::getExchangeApiUrl);
    }

    @Disabled
    @Test
    void shouldOpenCircuitBreakAndRouteToFallback() throws Exception {
        var username = "chaos-test-circuit-break-opens";
        var userId = validateExchangeWorking(username);
        var path = "/v1/users/%s/exchanges/usd/brl".formatted(userId);

//        proxy.toxics()
//                .latency(
//                        "slow-api",
//                        ToxicDirection.DOWNSTREAM,
//                        5000);
//        Timeout timeout = getDbProxy().toxics().timeout("timeout", ToxicDirection.DOWNSTREAM, 5000);

        mockMvc.perform(post(path).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.date").value("2026-06-30"))
                .andExpect(jsonPath("$.usd").value("1"))
                .andExpect(jsonPath("$.brl").value("5.18"));

        //timeout.remove();

        validateExchangeWorking(username);
    }

    private String validateExchangeWorking(String username) throws Exception {
        var userId = createUser(username);

        var path = "/v1/users/%s/exchanges/usd/brl".formatted(userId);

        mockMvc.perform(post(path).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.date").isString())
                .andExpect(jsonPath("$.usd").value("1"))
                .andExpect(jsonPath("$.brl").isNumber());

        return userId;
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