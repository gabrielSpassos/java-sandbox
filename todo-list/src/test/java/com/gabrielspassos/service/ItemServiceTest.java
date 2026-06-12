package com.gabrielspassos.service;

import com.gabrielspassos.entity.ItemEntity;
import com.gabrielspassos.entity.ItemStatus;
import com.gabrielspassos.entity.ListEntity;
import com.gabrielspassos.exception.BadRequestException;
import com.gabrielspassos.exception.NotFoundException;
import com.gabrielspassos.mapper.UUIDMapper;
import com.gabrielspassos.repository.ItemRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
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

    @Test
    void shouldUpdateItemStatus() {
        String listId = UUID.randomUUID().toString();
        UUID itemId = UUID.randomUUID();
        ItemEntity itemEntity = createItemEntity(listId);
        itemEntity.setStatus(ItemStatus.DONE);

        when(itemRepository.findById(itemId)).thenReturn(Optional.of(itemEntity));
        when(itemRepository.save(any(ItemEntity.class))).thenReturn(itemEntity);

        ItemEntity output = itemService.updateStatus(itemId.toString(), "DONE");

        assertNotNull(output);
        assertNotNull(output.getId());
        assertNotNull(output.getListId());
        assertEquals(listId, output.getListId().toString());
        assertEquals("test-item-description", output.getDescription());
        assertEquals(ItemStatus.DONE, output.getStatus());
        assertNotNull(output.getCreatedAt());
        assertNotNull(output.getUpdatedAt());
    }

    @Test
    void shouldFailToUpdateItemStatusWithInvalidStatus() {
        var itemId = UUID.randomUUID().toString();

        assertThrows(BadRequestException.class, () -> itemService.updateStatus(itemId, "FOO"));
    }

    @Test
    void shouldFailToUpdateItemStatusWithNotFound() {
        UUID itemId = UUID.randomUUID();

        when(itemRepository.findById(itemId)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> itemService.updateStatus(itemId.toString(), "DONE"));
    }

    @Test
    void shouldRemoveItem() {
        String listId = UUID.randomUUID().toString();
        UUID itemId = UUID.randomUUID();
        ItemEntity itemEntity = createItemEntity(listId);

        when(itemRepository.findById(itemId)).thenReturn(Optional.of(itemEntity));
        doNothing().when(itemRepository).delete(any(ItemEntity.class));

        boolean output = itemService.removeItem(itemId.toString());

        assertTrue(output);
    }

    @Test
    void shouldFailToRemoveItemStatusWithNotFound() {
        UUID itemId = UUID.randomUUID();

        when(itemRepository.findById(itemId)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> itemService.removeItem(itemId.toString()));
    }

    @Test
    void shouldFindItemsByListId() {
        UUID listId = UUID.randomUUID();
        ItemEntity itemEntity = createItemEntity(listId.toString());

        when(itemRepository.findByListId(listId)).thenReturn(List.of(itemEntity));

        List<ItemEntity> output = itemService.findByListId(listId.toString());

        assertNotNull(output);
        assertFalse(output.isEmpty());
        assertEquals(1, output.size());
        assertNotNull(output.getFirst().getId());
        assertNotNull(output.getFirst().getListId());
        assertEquals(listId.toString(), output.getFirst().getListId().toString());
        assertEquals("test-item-description", output.getFirst().getDescription());
        assertEquals(ItemStatus.TO_DO, output.getFirst().getStatus());
        assertNotNull(output.getFirst().getCreatedAt());
        assertNotNull(output.getFirst().getUpdatedAt());
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