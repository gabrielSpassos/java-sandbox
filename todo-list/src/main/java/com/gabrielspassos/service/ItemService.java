package com.gabrielspassos.service;

import com.gabrielspassos.entity.ItemEntity;
import com.gabrielspassos.entity.ItemStatus;
import com.gabrielspassos.exception.BadRequestException;
import com.gabrielspassos.exception.NotFoundException;
import com.gabrielspassos.mapper.UUIDMapper;
import com.gabrielspassos.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {

    @Autowired
    private ListService listService;

    @Autowired
    private ItemRepository itemRepository;

    public ItemEntity create(String listId, String description) {
        // create item for existing list
        var list = listService.findById(listId);

        ItemEntity itemEntity = new ItemEntity();
        itemEntity.setListId(list.getId());
        itemEntity.setDescription(description);
        itemEntity.setStatus(ItemStatus.TO_DO);

        return itemRepository.save(itemEntity);
    }

    public ItemEntity updateStatus(String itemId, String itemStatus) {
        ItemStatus status = convertStatus(itemStatus);

        ItemEntity itemEntity = findById(itemId);
        itemEntity.setStatus(status);

        return itemRepository.save(itemEntity);
    }

    public List<ItemEntity> findByListId(String listId) {
        var listIdAsUUID = UUIDMapper.toUUID(listId);

        return itemRepository.findByListId(listIdAsUUID);
    }

    private ItemEntity findById(String itemId) {
        var itemIdAsUUID = UUIDMapper.toUUID(itemId);

        return itemRepository.findById(itemIdAsUUID)
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
