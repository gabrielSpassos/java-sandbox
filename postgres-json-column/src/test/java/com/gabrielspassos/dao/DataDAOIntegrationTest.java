package com.gabrielspassos.dao;

import com.gabrielspassos.BaseIntegrationTests;
import com.gabrielspassos.entity.DataAsStringEntity;
import com.gabrielspassos.entity.DataEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import tools.jackson.databind.ObjectMapper;

import java.time.OffsetDateTime;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class DataDAOIntegrationTest extends BaseIntegrationTests {

    @Autowired
    private DataDAO dataDAO;

    @Test
    public void shouldSave() {
        var entity = createEntity();

        var savedEntity = dataDAO.save(entity);

        assertNotNull(savedEntity);
        assertNotNull(savedEntity.getId());
        assertNotNull(savedEntity.getData());
        assertNotNull(savedEntity.getBinaryData());
        assertNotNull(savedEntity.getCreatedAt());
    }

    private DataEntity createEntity() {
        var dataDTO = createDataDTO();

        var entity = new DataEntity();
        entity.setData(dataDTO);
        entity.setBinaryData(dataDTO);
        entity.setCreatedAt(OffsetDateTime.now());
        return entity;
    }
}
