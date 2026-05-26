package com.gabrielspassos.controller.v1;

import com.gabrielspassos.BaseApplicationTest;
import com.gabrielspassos.entity.ItemEntity;
import com.gabrielspassos.entity.ItemStatus;
import com.gabrielspassos.entity.ListEntity;
import com.gabrielspassos.service.ItemService;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
public class ItemControllerContractTest extends BaseApplicationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ItemService itemService;


    @Test
    void shouldCreateItem() throws Exception {
        String expectedResponse = Files.readString(Path.of("src/test/resources/item-response.json"));
        String listId = "64442a1d-ac0f-48b7-bb78-7366c96b2499";

        ItemEntity itemEntity = new ItemEntity();
        itemEntity.setId(UUID.fromString("36933325-00a5-4cd1-9d3c-8873d4ca9525"));
        itemEntity.setListId(UUID.fromString(listId));
        itemEntity.setStatus(ItemStatus.TO_DO);
        itemEntity.setDescription("contract-test-item");
        itemEntity.setCreatedAt(LocalDateTime.parse("2026-05-21T08:38:25"));
        itemEntity.setUpdatedAt(LocalDateTime.parse("2026-05-21T08:38:25"));

        when(itemService.create(listId, "contract-test-item")).thenReturn(itemEntity);

        mockMvc.perform(post("/v1/lists/{listId}/items", listId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                            {
                                "description":"contract-test-item"
                            }
                        """))
                .andExpect(status().isCreated())
                .andExpect(content().json(expectedResponse, JsonCompareMode.LENIENT));
    }
}
