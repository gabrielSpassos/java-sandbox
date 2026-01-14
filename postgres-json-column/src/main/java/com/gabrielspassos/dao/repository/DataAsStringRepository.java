package com.gabrielspassos.dao.repository;

import com.gabrielspassos.entity.DataAsStringEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface DataAsStringRepository extends CrudRepository<DataAsStringEntity, UUID> {
}
