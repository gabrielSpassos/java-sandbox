package com.gabrielspassos.dao.repository;

import com.gabrielspassos.entity.DataEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface DataRepository extends CrudRepository<DataEntity, UUID> {
}
