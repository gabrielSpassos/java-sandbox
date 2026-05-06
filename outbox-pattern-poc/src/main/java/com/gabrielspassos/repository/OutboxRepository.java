package com.gabrielspassos.repository;

import com.gabrielspassos.entity.OutboxEntity;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface OutboxRepository extends CrudRepository<OutboxEntity, UUID> {

    @Query("select o from OutboxEvent o where o.processed = false")
    List<OutboxEntity> findUnprocessed();
}
