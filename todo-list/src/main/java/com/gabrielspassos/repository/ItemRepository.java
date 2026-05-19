package com.gabrielspassos.repository;

import com.gabrielspassos.entity.ItemEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ItemRepository extends CrudRepository<ItemEntity, UUID> {

    List<ItemEntity> findByListId(UUID listId);

}
