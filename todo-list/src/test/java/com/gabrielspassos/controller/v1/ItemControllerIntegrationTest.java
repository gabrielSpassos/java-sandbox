package com.gabrielspassos.controller.v1;

import com.gabrielspassos.BaseApplicationTest;
import com.gabrielspassos.controller.v1.response.ItemResponse;
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

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
class ItemControllerIntegrationTest extends BaseApplicationTest {

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void shouldCreateItem() throws Exception {
        String userId = createUser("it-test-create-item");
        String listId = createList(userId, "it-test-create-item");
        String path = String.format("/v1/lists/%s/items", listId);

        mockMvc.perform(post(path)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                            {
                                "description":"it-test-create-item"
                            }
                        """))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").isString())
                .andExpect(jsonPath("$.listId").value(listId))
                .andExpect(jsonPath("$.status").value("TO_DO"))
                .andExpect(jsonPath("$.description").value("it-test-create-item"))
                .andExpect(jsonPath("$.createdAt").isString())
                .andExpect(jsonPath("$.updatedAt").isString());
    }

    @Test
    void shouldFailToCreateItemForNonExistingList() throws Exception {
        String listId = UUID.randomUUID().toString();
        String path = String.format("/v1/lists/%s/items", listId);

        mockMvc.perform(post(path)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                            {
                                "description":"it-test-create-item-non-existing-list"
                            }
                        """))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("List not found"))
                .andExpect(jsonPath("$.code").value("LIST_NOT_FOUND"));
    }

    @Test
    void shouldUpdateItemStatus() throws Exception {
        String userId = createUser("it-test-update-item");
        String listId = createList(userId, "it-test-update-item");

        MvcResult createResult = mockMvc.perform(post("/v1/lists/{listId}/items", listId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                    {
                                        "description":"it-test-update-item"
                                    }
                                """))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").isString())
                .andReturn();

        String responseBody = createResult.getResponse().getContentAsString();
        ItemResponse itemResponse = objectMapper.readValue(responseBody, ItemResponse.class);

        mockMvc.perform(put("/v1/items/{itemId}", itemResponse.id())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                            {
                                "status":"DONE"
                            }
                        """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(itemResponse.id()))
                .andExpect(jsonPath("$.listId").value(listId))
                .andExpect(jsonPath("$.status").value("DONE"))
                .andExpect(jsonPath("$.description").value("it-test-update-item"))
                .andExpect(jsonPath("$.createdAt").value(itemResponse.createdAt()))
                .andExpect(jsonPath("$.updatedAt").isString());
    }

    @Test
    void shouldFailToUpdateItemWithInvalidStatus() throws Exception {
        String itemId = UUID.randomUUID().toString();

        mockMvc.perform(put("/v1/items/{itemId}", itemId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                            {
                                "status":"FOO"
                            }
                        """))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("invalid status"))
                .andExpect(jsonPath("$.code").value("INVALID_STATUS"));
    }

    @Test
    void shouldFailToUpdateItemWithItemNotFound() throws Exception {
        String itemId = UUID.randomUUID().toString();

        mockMvc.perform(put("/v1/items/{itemId}", itemId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                            {
                                "status":"DONE"
                            }
                        """))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("Item not found"))
                .andExpect(jsonPath("$.code").value("ITEM_NOT_FOUND"));
    }

    @Test
    void shouldFindOneItemByListId() throws Exception {
        String userId = createUser("it-test-find-one-item");
        String listId = createList(userId, "it-test-find-one-item");

        mockMvc.perform(post("/v1/lists/{listId}/items", listId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                            {
                                "description":"it-test-find-one-item"
                            }
                        """))
                .andExpect(status().isCreated());

        mockMvc.perform(get("/v1/lists/{listId}/items", listId)
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].id").isString())
                .andExpect(jsonPath("$.[0].listId").value(listId))
                .andExpect(jsonPath("$.[0].status").value("TO_DO"))
                .andExpect(jsonPath("$.[0].description").value("it-test-find-one-item"))
                .andExpect(jsonPath("$.[0].createdAt").isString())
                .andExpect(jsonPath("$.[0].updatedAt").isString());
    }

    @Test
    void shouldFindMultipleItemsByListId() throws Exception {
        String userId = createUser("it-test-find-multiple-item");
        String listId = createList(userId, "it-test-find-multiple-item");

        mockMvc.perform(post("/v1/lists/{listId}/items", listId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                            {
                                "description":"it-test-find-multiple-item1"
                            }
                        """))
                .andExpect(status().isCreated());

        mockMvc.perform(post("/v1/lists/{listId}/items", listId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                            {
                                "description":"it-test-find-multiple-item2"
                            }
                        """))
                .andExpect(status().isCreated());

        mockMvc.perform(get("/v1/lists/{listId}/items", listId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$.[0].id").isString())
                .andExpect(jsonPath("$.[0].listId").value(listId))
                .andExpect(jsonPath("$.[0].status").value("TO_DO"))
                .andExpect(jsonPath("$.[0].description").value("it-test-find-multiple-item1"))
                .andExpect(jsonPath("$.[0].createdAt").isString())
                .andExpect(jsonPath("$.[0].updatedAt").isString())
                .andExpect(jsonPath("$.[1].id").isString())
                .andExpect(jsonPath("$.[1].listId").value(listId))
                .andExpect(jsonPath("$.[1].status").value("TO_DO"))
                .andExpect(jsonPath("$.[1].description").value("it-test-find-multiple-item2"))
                .andExpect(jsonPath("$.[1].createdAt").isString())
                .andExpect(jsonPath("$.[1].updatedAt").isString());
    }

    @Test
    void shouldFindNoneItemByListId() throws Exception {
        String listId = UUID.randomUUID().toString();

        mockMvc.perform(get("/v1/lists/{listId}/items", listId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)))
                .andExpect(content().json("[]"));
    }

    @Test
    void shouldFailToFindItemByListIdWithInvalidId() throws Exception {
        String listId = "invalidId";

        mockMvc.perform(get("/v1/lists/{listId}/items", listId)
                        .contentType(MediaType.APPLICATION_JSON))
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

    private String createList(String userId, String name) throws Exception {
        String path = String.format("/v1/users/%s/lists", userId);
        String jsonBody = """
        {
            "name":"%s"
        }
        """.formatted(name);

        MvcResult createResult = mockMvc.perform(post(path)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonBody))
                .andExpect(status().isCreated())
                .andReturn();

        String responseBody = createResult.getResponse().getContentAsString();
        ListResponse response = objectMapper.readValue(responseBody, ListResponse.class);
        return response.id();
    }
}