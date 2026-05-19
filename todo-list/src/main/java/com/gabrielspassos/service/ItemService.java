package com.gabrielspassos.service;

import com.gabrielspassos.entity.ItemEntity;
import com.gabrielspassos.entity.ItemStatus;
import com.gabrielspassos.exception.BadRequestException;
import com.gabrielspassos.exception.NotFoundException;
import com.gabrielspassos.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    public ItemEntity create(UUID listId, String description) {
        ItemEntity itemEntity = new ItemEntity();
        itemEntity.setListId(listId);
        itemEntity.setDescription(description);
        itemEntity.setStatus(ItemStatus.TO_DO);

        return itemRepository.save(itemEntity);
    }

    public ItemEntity updateStatus(UUID itemId, String itemStatus) {
        ItemStatus status = convertStatus(itemStatus);

        ItemEntity itemEntity = findById(itemId);
        itemEntity.setStatus(status);

        return itemRepository.save(itemEntity);
    }

    public List<ItemEntity> findByListId(UUID listId) {
        return itemRepository.findByListId(listId);
    }

    private ItemEntity findById(UUID itemId) {
        return itemRepository.findById(itemId)
                .orElseThrow(() -> new NotFoundException("Item not found", "ITEM_NOT_FOUND"));
    }

    private ItemStatus convertStatus(String itemStatus) {
        try {
            return ItemStatus.convert(itemStatus);
        } catch (Exception e) {
            throw new BadRequestException(e.getMessage(), "INVALID_STATUS");
        }
    }

}
