package com.gabrielspassos.v1;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class SecurityIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldAllowPublicAccess() throws Exception {
        mockMvc.perform(get("/v1/public/hello"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello Public!"));
    }

    @Test
    void shouldRejectUnauthenticatedUserEndpoint() throws Exception {
        mockMvc.perform(get("/v1/user/hello"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void shouldAllowUserRole() throws Exception {
        mockMvc.perform(get("/v1/user/hello")
                        .with(httpBasic("user", "password")))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello User!"));
    }

    @Test
    void shouldDenyUserAccessToAdmin() throws Exception {
        mockMvc.perform(get("/v1/admin/hello")
                        .with(httpBasic("user", "password")))
                .andExpect(status().isForbidden());
    }

    @Test
    void shouldAllowAdminAccess() throws Exception {
        mockMvc.perform(get("/v1/admin/hello")
                        .with(httpBasic("admin", "password")))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello Admin!"));
    }

    @Test
    void shouldRejectInvalidCredentials() throws Exception {
        mockMvc.perform(get("/v1/user/hello")
                        .with(httpBasic("user", "wrong")))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void shouldReturn403ForWrongRole() throws Exception {
        mockMvc.perform(get("/v1/admin/hello")
                        .with(httpBasic("user", "password")))
                .andExpect(status().isForbidden());
    }

}
