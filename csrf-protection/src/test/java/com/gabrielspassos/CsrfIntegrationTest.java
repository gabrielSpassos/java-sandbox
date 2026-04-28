package com.gabrielspassos;

import com.gabrielspassos.configs.SecurityConfig;
import jakarta.servlet.http.Cookie;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Import(SecurityConfig.class)
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
    void shouldWorkWithValidCookieAndHeader() throws Exception {
        var token = "test-token";

        mockMvc.perform(post("/transfer")
                        .cookie(new jakarta.servlet.http.Cookie("XSRF-TOKEN", token))
                        .header("X-XSRF-TOKEN", token)
                        .param("amount", "123")
                        .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(content().string("Transferred: 123"));
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

    @Test
    void shouldFailWhenHeaderMissingButCookiePresent() throws Exception {
        mockMvc.perform(post("/transfer")
                        .cookie(new Cookie("XSRF-TOKEN", "abc"))
                        .param("amount", "100"))
                .andExpect(status().isForbidden());
    }
}
