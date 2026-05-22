package com.gabrielspassos.controller.v1;

import com.gabrielspassos.BaseIntegrationTest;
import com.gabrielspassos.controller.v1.response.ListResponse;
import com.gabrielspassos.controller.v1.response.UserResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
class ListControllerIntegrationTest extends BaseIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void shouldCreateList() throws Exception {
        String userId = createUser("it-test-create-list");
        String path = String.format("/v1/users/%s/lists", userId);

        mockMvc.perform(post(path)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                            {
                                "name":"it-test-create-list"
                            }
                        """))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").isString())
                .andExpect(jsonPath("$.userId").value(userId))
                .andExpect(jsonPath("$.name").value("it-test-create-list"))
                .andExpect(jsonPath("$.createdAt").isString());
    }

    @Test
    void shouldCreateListWithoutListName() throws Exception {
        String userId = createUser("it-test-create-list-without-name");
        String path = String.format("/v1/users/%s/lists", userId);

        mockMvc.perform(post(path)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                            {
                                "name":null
                            }
                        """))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").isString())
                .andExpect(jsonPath("$.userId").value(userId))
                .andExpect(jsonPath("$.name").value("Untitled"))
                .andExpect(jsonPath("$.createdAt").isString());
    }

    @Test
    void shouldFailToCreateListWithoutExistingUser() throws Exception {
        String userId = UUID.randomUUID().toString();
        String path = String.format("/v1/users/%s/lists", userId);

        mockMvc.perform(post(path)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                            {
                                "name":"it-test-fail-to-create-list-with-non-existing-user"
                            }
                        """))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("User not found"))
                .andExpect(jsonPath("$.code").value("USER_NOT_FOUND"));
    }

    @Test
    void shouldFailToCreateListWithoutValidUserId() throws Exception {
        String userId = "invalidUserId";
        String path = String.format("/v1/users/%s/lists", userId);

        mockMvc.perform(post(path)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                            {
                                "name":"it-test-fail-to-create-list-with-invalid-userId"
                            }
                        """))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("invalid id"))
                .andExpect(jsonPath("$.code").value("INVALID_ID"));
    }

    @Test
    void shouldFindListByUserId() throws Exception {
        String userId = createUser("it-test-find-list-by-userId");
        String path = String.format("/v1/users/%s/lists", userId);

        MvcResult createResult = mockMvc.perform(post(path)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                            {
                                "name":"it-test-find-list-by-userId"
                            }
                        """))
                .andExpect(status().isCreated())
                .andReturn();

        String responseBody = createResult.getResponse().getContentAsString();
        ListResponse response = objectMapper.readValue(responseBody, ListResponse.class);

        mockMvc.perform(get(path).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(response.id()))
                .andExpect(jsonPath("$.userId").value(userId))
                .andExpect(jsonPath("$.name").value(response.name()))
                .andExpect(jsonPath("$.createdAt").value(response.createdAt()));
    }

    @Test
    void shouldNotFindListByUserId() throws Exception {
        String userId = UUID.randomUUID().toString();
        String path = String.format("/v1/users/%s/lists", userId);

        mockMvc.perform(get(path).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("List not found"))
                .andExpect(jsonPath("$.code").value("LIST_NOT_FOUND"));
    }

    @Test
    void shouldFailToFindListByInvalidUserId() throws Exception {
        String userId = "invalidUserId";
        String path = String.format("/v1/users/%s/lists", userId);

        mockMvc.perform(get(path).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("invalid id"))
                .andExpect(jsonPath("$.code").value("INVALID_ID"));
    }

    private String createUser(String name) throws Exception {
        String jsonBody = """
        {
            "name":"%s"
        }
        """.formatted(name);

        MvcResult createResult = mockMvc.perform(post("/v1/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonBody))
                .andExpect(status().isCreated())
                .andReturn();

        String responseBody = createResult.getResponse().getContentAsString();
        UserResponse response = objectMapper.readValue(responseBody, UserResponse.class);
        return response.id();
    }
}