package com.gabrielspassos.controller.v1;

import com.gabrielspassos.BaseApplicationTest;
import com.gabrielspassos.entity.UserEntity;
import com.gabrielspassos.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.json.JsonCompareMode;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.UUID;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
class UserControllerContractTest extends BaseApplicationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private UserService userService;

    @Test
    void shouldCreateUser() throws Exception {
        String expectedResponse = Files.readString(Path.of("src/test/resources/user-response.json"));
        UserEntity userEntity = new UserEntity();
        userEntity.setId(UUID.fromString("cc211026-c7c7-496d-a02e-187f13a5a8cf"));
        userEntity.setName("contract-test-user");
        userEntity.setCreatedAt(LocalDateTime.parse("2026-05-21T08:38:25"));

        when(userService.create("contract-test-user")).thenReturn(userEntity);

        mockMvc.perform(post("/v1/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                            {
                                "name":"contract-test-user"
                            }
                        """))
                .andExpect(status().isCreated())
                .andExpect(content().json(expectedResponse, JsonCompareMode.LENIENT));
    }

    @Test
    void shouldFindUserByName() throws Exception {
        String expectedResponse = Files.readString(Path.of("src/test/resources/user-response.json"));
        UserEntity userEntity = new UserEntity();
        userEntity.setId(UUID.fromString("cc211026-c7c7-496d-a02e-187f13a5a8cf"));
        userEntity.setName("contract-test-user");
        userEntity.setCreatedAt(LocalDateTime.parse("2026-05-21T08:38:25"));

        when(userService.findByName("contract-test-user")).thenReturn(userEntity);

        mockMvc.perform(get("/v1/users")
                .queryParam("name", "contract-test-user")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedResponse, JsonCompareMode.LENIENT));
    }
}