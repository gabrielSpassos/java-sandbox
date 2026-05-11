package com.gabrielspassos.v2;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class AuthorizationRoleApplicationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void analystShouldReadInvoice() throws Exception {
        mockMvc.perform(get("/v2/invoices")
                        .with(httpBasic("analyst", "password")))
                .andExpect(status().isOk());
    }
}
