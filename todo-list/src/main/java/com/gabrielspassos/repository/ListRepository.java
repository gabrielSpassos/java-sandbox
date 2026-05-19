package com.gabrielspassos.repository;

import com.gabrielspassos.entity.ListEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ListRepository extends CrudRepository<ListEntity, UUID> {

    Optional<ListEntity> findByUserId(UUID userId);

}
