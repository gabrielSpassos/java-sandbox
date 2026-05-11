package com.gabrielspassos.v2;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class AuthorizationRoleApplicationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldRejectUnauthenticatedRequest() throws Exception {
        mockMvc.perform(get("/v2/invoices"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void shouldRejectInvalidCredentials() throws Exception {
        mockMvc.perform(get("/v2/invoices")
                        .with(httpBasic("admin", "wrong-password")))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void shouldRejectUnknownUser() throws Exception {
        mockMvc.perform(get("/v2/invoices")
                        .with(httpBasic("ghost", "password")))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void analystShouldReadInvoice() throws Exception {
        mockMvc.perform(get("/v2/invoices")
                        .with(httpBasic("analyst", "password")))
                .andExpect(status().isOk())
                .andExpect(content().string("Invoice Data"));
    }

    @Test
    void adminShouldReadInvoice() throws Exception {
        mockMvc.perform(get("/v2/invoices")
                        .with(httpBasic("admin", "password")))
                .andExpect(status().isOk())
                .andExpect(content().string("Invoice Data"))
                .andExpect(content().contentTypeCompatibleWith("text/plain"));
    }

    @Test
    void adminShouldCreateInvoice() throws Exception {
        mockMvc.perform(post("/v2/invoices")
                        .with(httpBasic("admin", "password")))
                .andExpect(status().isOk())
                .andExpect(content().string("Invoice Created"));
    }

    @Test
    void analystShouldNotCreateInvoice() throws Exception {
        mockMvc.perform(post("/v2/invoices")
                        .with(httpBasic("analyst", "password")))
                .andExpect(status().isForbidden());
    }

    @Test
    void adminShouldDeleteInvoice() throws Exception {
        mockMvc.perform(delete("/v2/invoices")
                        .with(httpBasic("admin", "password")))
                .andExpect(status().isOk())
                .andExpect(content().string("Invoice Deleted"));
    }

    @Test
    void analystShouldNotDeleteInvoice() throws Exception {
        mockMvc.perform(delete("/v2/invoices")
                        .with(httpBasic("analyst", "password")))
                .andExpect(status().isForbidden());
    }

    @Test
    void shouldReturn403WhenAuthorityMissing() throws Exception {
        mockMvc.perform(delete("/v2/invoices")
                        .with(httpBasic("user", "password")))
                .andExpect(status().isForbidden());
    }

    @Test
    void shouldRejectUnsupportedHttpMethod() throws Exception {
        mockMvc.perform(put("/v2/invoices")
                        .with(httpBasic("admin", "password")))
                .andExpect(status().isMethodNotAllowed());
    }
}
