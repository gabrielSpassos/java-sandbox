package com.gabrielspassos.dao.repository;

import com.gabrielspassos.entity.DataAsStringEntity;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;
import java.util.UUID;

@Repository
public interface DataAsStringRepository extends CrudRepository<DataAsStringEntity, UUID> {

    @Modifying
    @Query("""
        INSERT INTO data (id, data, data_b, created_at)
        VALUES (:id, :data::json, :binaryData::jsonb, :createdAt)
    """)
    void insert(UUID id, String data, String binaryData, OffsetDateTime createdAt);

}
