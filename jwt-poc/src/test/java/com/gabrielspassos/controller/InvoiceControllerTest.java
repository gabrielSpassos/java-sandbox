package com.gabrielspassos.controller;

import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class InvoiceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldRejectRequestWithoutJwt() throws Exception {
        mockMvc.perform(get("/v1/invoices"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void shouldRejectMalformedJwt() throws Exception {
        mockMvc.perform(get("/v1/invoices")
                        .header(
                                "Authorization",
                                "Bearer invalid-token"
                        ))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void shouldRejectEmptyBearerToken() throws Exception {
        mockMvc.perform(get("/v1/invoices")
                        .header(
                                "Authorization",
                                "Bearer "
                        ))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void adminShouldReadInvoices() throws Exception {
        String token = loginAndGetToken("admin", "password");

        mockMvc.perform(get("/v1/invoices")
                        .header(
                                "Authorization",
                                "Bearer " + token
                        ))
                .andExpect(status().isOk())
                .andExpect(content().string("Invoice Data"));
    }

    @Test
    void adminShouldCreateInvoice() throws Exception {
        String token = loginAndGetToken("admin", "password");

        mockMvc.perform(post("/v1/invoices")
                        .header(
                                "Authorization",
                                "Bearer " + token
                        ))
                .andExpect(status().isOk())
                .andExpect(content().string("Invoice Created"));
    }

    @Test
    void adminShouldDeleteInvoice() throws Exception {
        String token = loginAndGetToken("admin", "password");

        mockMvc.perform(delete("/v1/invoices")
                        .header(
                                "Authorization",
                                "Bearer " + token
                        ))
                .andExpect(status().isOk())
                .andExpect(content().string("Invoice Deleted"));
    }

    @Test
    void analystShouldReadInvoices() throws Exception {
        String token = loginAndGetToken("analyst", "password");

        mockMvc.perform(get("/v1/invoices")
                        .header(
                                "Authorization",
                                "Bearer " + token
                        ))
                .andExpect(status().isOk());
    }

    @Test
    void analystShouldNotCreateInvoice() throws Exception {
        String token = loginAndGetToken("analyst", "password");

        mockMvc.perform(post("/v1/invoices")
                        .header(
                                "Authorization",
                                "Bearer " + token
                        ))
                .andExpect(status().isForbidden());
    }

    @Test
    void analystShouldNotDeleteInvoice() throws Exception {
        String token = loginAndGetToken("analyst", "password");

        mockMvc.perform(delete("/v1/invoices")
                        .header(
                                "Authorization",
                                "Bearer " + token
                        ))
                .andExpect(status().isForbidden());
    }

    @Test
    void shouldRejectUnsupportedMethod() throws Exception {
        String token = loginAndGetToken("admin", "password");

        mockMvc.perform(put("/v1/invoices")
                        .header(
                                "Authorization",
                                "Bearer " + token
                        ))
                .andExpect(status().isMethodNotAllowed());
    }

    private String loginAndGetToken(String username, String password) throws Exception {
        String response = mockMvc.perform(post("/v1/auth/login")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("""
                                    {
                                        "username":"%s",
                                        "password":"%s"
                                    }
                                """.formatted(username, password)))
                        .andExpect(status().isOk())
                        .andReturn()
                        .getResponse()
                        .getContentAsString();

        return JsonPath.read(response, "$.token");
    }

}