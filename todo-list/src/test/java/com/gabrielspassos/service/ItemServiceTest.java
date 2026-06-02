package com.gabrielspassos.service;

import com.gabrielspassos.entity.ItemEntity;
import com.gabrielspassos.entity.ItemStatus;
import com.gabrielspassos.entity.ListEntity;
import com.gabrielspassos.mapper.UUIDMapper;
import com.gabrielspassos.repository.ItemRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ItemServiceTest {

    @InjectMocks
    private ItemService itemService;

    @Mock
    private ListService listService;

    @Mock
    private ItemRepository itemRepository;

    @Test
    void shouldCreateItem() {
        String listId = UUID.randomUUID().toString();
        ListEntity listEntity = createListEntity(listId);
        ItemEntity itemEntity = createItemEntity(listId);

        when(listService.findById(listId)).thenReturn(listEntity);
        when(itemRepository.save(any(ItemEntity.class))).thenReturn(itemEntity);

        ItemEntity output = itemService.create(listId, "test-item-description");

        assertNotNull(output);
        assertNotNull(output.getId());
        assertNotNull(output.getListId());
        assertEquals(listId, output.getListId().toString());
        assertEquals("test-item-description", output.getDescription());
        assertEquals(ItemStatus.TO_DO, output.getStatus());
        assertNotNull(output.getCreatedAt());
        assertNotNull(output.getUpdatedAt());
    }

    private ListEntity createListEntity(String listId) {
        ListEntity listEntity = new ListEntity();
        listEntity.setId(UUIDMapper.toUUID(listId));
        listEntity.setUserId(UUID.randomUUID());
        listEntity.setName("test-list-name");
        return listEntity;
    }

    private ItemEntity createItemEntity(String listId) {
        ItemEntity itemEntity = new ItemEntity();
        itemEntity.setId(UUID.randomUUID());
        itemEntity.setListId(UUIDMapper.toUUID(listId));
        itemEntity.setDescription("test-item-description");
        itemEntity.setStatus(ItemStatus.TO_DO);
        return itemEntity;
    }

}