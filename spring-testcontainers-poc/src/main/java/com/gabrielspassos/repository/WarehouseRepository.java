package com.gabrielspassos.repository;

import com.gabrielspassos.domain.WarehouseEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WarehouseRepository extends CrudRepository<WarehouseEntity, String> {

    Optional<WarehouseEntity> findByName(String name);
}
