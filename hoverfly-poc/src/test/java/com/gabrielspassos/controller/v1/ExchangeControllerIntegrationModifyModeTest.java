package com.gabrielspassos.controller.v1;


import com.gabrielspassos.BaseApplicationTest;
import com.gabrielspassos.controller.v1.response.UserResponse;
import io.specto.hoverfly.junit.core.Hoverfly;
import io.specto.hoverfly.junit.core.config.LocalHoverflyConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import java.time.LocalDate;

import static io.specto.hoverfly.junit.core.HoverflyConfig.localConfigs;
import static io.specto.hoverfly.junit.core.HoverflyMode.MODIFY;
import static io.specto.hoverfly.junit.core.HoverflyMode.SIMULATE;
import static io.specto.hoverfly.junit.core.SimulationSource.dsl;
import static io.specto.hoverfly.junit.dsl.HoverflyDsl.service;
import static io.specto.hoverfly.junit.dsl.ResponseCreators.noContent;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
class ExchangeControllerIntegrationModifyModeTest extends BaseApplicationTest {

    @Autowired
    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void shouldFetchExchangeWithSimulateHoverfly() throws Exception {
        LocalHoverflyConfig modifyConfig = (LocalHoverflyConfig) localConfigs().proxyPort(8500);
        modifyConfig.localMiddleware(
                "python3",
                "modify_response.py"
        );

        LocalHoverflyConfig simulateConfig = (LocalHoverflyConfig) localConfigs().proxyPort(8501);

        try (Hoverfly modifyHoverfly = new Hoverfly(modifyConfig, MODIFY);
             Hoverfly simulateHoverfly = new Hoverfly(simulateConfig, SIMULATE)) {

            modifyHoverfly.start();
            simulateHoverfly.start();

            var userId = createUser("it-test-fetch-exchange-with-modify-mode");
            var notificationRequestBody =
                    "{\"userId\":\"%s\",\"eventType\":\"SELL\",\"exchangeValue\":10.15}".formatted(userId);

            simulateHoverfly.simulate(
                    dsl(
                            service("http://localhost:9090")
                                    .post("/v1/notify")
                                    .body(notificationRequestBody)
                                    .willReturn(noContent())
                    )
            );

            var path = "/v1/users/%s/exchanges/usd/brl".formatted(userId);
            var today = LocalDate.now().toString();

            mockMvc.perform(post(path).contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.date").value(today))
                    .andExpect(jsonPath("$.usd").value("1"))
                    .andExpect(jsonPath("$.brl").value("10.15"));
        }

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

