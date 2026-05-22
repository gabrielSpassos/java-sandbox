package com.gabrielspassos.controller.v1;

import com.gabrielspassos.BaseIntegrationTest;
import com.gabrielspassos.entity.ListEntity;
import com.gabrielspassos.exception.BadRequestException;
import com.gabrielspassos.exception.NotFoundException;
import com.gabrielspassos.service.ListService;
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
class ListControllerContractTest extends BaseIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ListService listService;

    @Test
    void shouldCreateList() throws Exception {
        String expectedResponse = Files.readString(Path.of("src/test/resources/list-response.json"));
        String userId = "138df585-b6dd-413a-b8cb-0504ae1631a8";
        String path = String.format("/v1/users/%s/lists", userId);
        ListEntity listEntity = new ListEntity();
        listEntity.setId(UUID.fromString("252f1f0f-eaef-4648-9f50-7381cc8f1ebf"));
        listEntity.setUserId(UUID.fromString(userId));
        listEntity.setName("contract-test-list");
        listEntity.setCreatedAt(LocalDateTime.parse("2026-05-21T08:38:25"));

        when(listService.create(userId, "contract-test-list")).thenReturn(listEntity);

        mockMvc.perform(post(path)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                            {
                                "name":"contract-test-list"
                            }
                        """))
                .andExpect(status().isCreated())
                .andExpect(content().json(expectedResponse, JsonCompareMode.LENIENT));
    }

    @Test
    void shouldFailToCreateListWithoutExistingUser() throws Exception {
        String userId = UUID.randomUUID().toString();
        String path = String.format("/v1/users/%s/lists", userId);
        String expectedResponse = Files.readString(Path.of("src/test/resources/user-not-found-response.json"));

        when(listService.create(userId, "contract-test-fail-to-create-list-with-non-existing-user"))
                .thenThrow(new NotFoundException("User not found", "USER_NOT_FOUND"));

        mockMvc.perform(post(path)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                            {
                                "name":"contract-test-fail-to-create-list-with-non-existing-user"
                            }
                        """))
                .andExpect(status().isNotFound())
                .andExpect(content().json(expectedResponse, JsonCompareMode.LENIENT));
    }

    @Test
    void shouldFailToCreateListWithoutValidUserId() throws Exception {
        String userId = "invalidUserId";
        String path = String.format("/v1/users/%s/lists", userId);
        String expectedResponse = Files.readString(Path.of("src/test/resources/invalid-id-response.json"));

        when(listService.create(userId, "contract-test-fail-to-create-list-with-invalid-userId"))
                .thenThrow(new BadRequestException("invalid id", "INVALID_ID"));

        mockMvc.perform(post(path)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                            {
                                "name":"contract-test-fail-to-create-list-with-invalid-userId"
                            }
                        """))
                .andExpect(status().isBadRequest())
                .andExpect(content().json(expectedResponse, JsonCompareMode.LENIENT));
    }

    @Test
    void shouldFindListByUserId() throws Exception {
        String expectedResponse = Files.readString(Path.of("src/test/resources/list-response.json"));
        String userId = "138df585-b6dd-413a-b8cb-0504ae1631a8";
        String path = String.format("/v1/users/%s/lists", userId);
        ListEntity listEntity = new ListEntity();
        listEntity.setId(UUID.fromString("252f1f0f-eaef-4648-9f50-7381cc8f1ebf"));
        listEntity.setUserId(UUID.fromString(userId));
        listEntity.setName("contract-test-list");
        listEntity.setCreatedAt(LocalDateTime.parse("2026-05-21T08:38:25"));

        when(listService.findByUserId(userId)).thenReturn(listEntity);

        mockMvc.perform(get(path).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedResponse, JsonCompareMode.LENIENT));
    }

    @Test
    void shouldNotFindListByUserId() throws Exception {
        String userId = UUID.randomUUID().toString();
        String path = String.format("/v1/users/%s/lists", userId);
        String expectedResponse = Files.readString(Path.of("src/test/resources/list-not-found-response.json"));

        when(listService.findByUserId(userId))
                .thenThrow(new NotFoundException("List not found", "LIST_NOT_FOUND"));

        mockMvc.perform(get(path).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(content().json(expectedResponse, JsonCompareMode.LENIENT));
    }

    @Test
    void shouldFailToFindListByInvalidUserId() throws Exception {
        String userId = "invalidUserId";
        String path = String.format("/v1/users/%s/lists", userId);
        String expectedResponse = Files.readString(Path.of("src/test/resources/invalid-id-response.json"));

        when(listService.findByUserId(userId))
                .thenThrow(new BadRequestException("invalid id", "INVALID_ID"));

        mockMvc.perform(get(path).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().json(expectedResponse, JsonCompareMode.LENIENT));
    }

}