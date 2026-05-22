package com.gabrielspassos.poc.repository;

import com.gabrielspassos.poc.entity.OutboxEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface OutboxRepository extends CrudRepository<OutboxEntity, UUID> {
}
