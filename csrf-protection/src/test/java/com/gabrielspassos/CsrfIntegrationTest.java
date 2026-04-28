package com.gabrielspassos;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CsrfIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldAllowPostWhenCsrfTokenIsValid() throws Exception {
        mockMvc.perform(post("/transfer")
                        .param("amount", "100")
                        .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(content().string("Transferred: 100"));
    }

    @Test
    void shouldRejectPostWhenCsrfTokenIsMissing() throws Exception {
        mockMvc.perform(post("/transfer")
                        .param("amount", "100"))
                .andExpect(status().isForbidden());
    }

    @Test
    void shouldRejectPostWhenCsrfTokenIsInvalid() throws Exception {
        mockMvc.perform(post("/transfer")
                        .param("amount", "100")
                        .with(csrf().useInvalidToken()))
                .andExpect(status().isForbidden());
    }
}
