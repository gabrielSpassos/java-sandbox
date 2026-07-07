package com.gabrielspassos.controller.v1;

import com.gabrielspassos.BaseApplicationTest;
import com.gabrielspassos.controller.v1.response.UserResponse;
import okhttp3.mockwebserver.MockResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

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
        registry.add("exchange.api.url", ExchangeControllerChaosTest::getExchangeApiUrl);
    }

    @Test
    void shouldOpenCircuitBreakAndRouteToFallback() throws Exception {
        var username = "chaos-test-circuit-break-opens";
        var userId = createUser(username);
        validateExchangeWorking(userId);
        var path = "/v1/users/%s/exchanges/usd/brl".formatted(userId);

        var mockResponse = new MockResponse()
                .setBodyDelay(6, TimeUnit.SECONDS)
                .setBody("""
                        {
                          "date":"2026-07-07",
                          "usd":{
                             "brl":5.20
                          }
                        }
                        """)
                .addHeader("Content-Type", "application/json");
        getMockServer().enqueue(mockResponse);
        getMockServer().enqueue(mockResponse);
        getMockServer().enqueue(mockResponse);
        getMockServer().enqueue(mockResponse);
        getMockServer().enqueue(mockResponse);

        mockMvc.perform(post(path).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.date").value("2026-06-30"))
                .andExpect(jsonPath("$.usd").value("1"))
                .andExpect(jsonPath("$.brl").value("5.18"));

        validateExchangeWorking(userId);
    }

    private void validateExchangeWorking(String userId) throws Exception {
        var path = "/v1/users/%s/exchanges/usd/brl".formatted(userId);

        enqueueExchangeResponse();
        mockMvc.perform(post(path).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.date").isString())
                .andExpect(jsonPath("$.usd").value("1"))
                .andExpect(jsonPath("$.brl").isNumber());
    }

    private void enqueueExchangeResponse() {
        getMockServer().enqueue(new MockResponse()
                .setBody("""
                        {
                          "date":"2026-06-30",
                          "usd":{
                             "brl":5.18
                          }
                        }
                        """)
                .addHeader("Content-Type", "application/json")
        );
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

    public static String getExchangeApiUrl() {
        return "http://" + getMockServer().getHostName() + ":" + getMockServer().getPort();
    }

}