package com.gabrielspassos.dao;

import com.gabrielspassos.BaseIntegrationTests;
import com.gabrielspassos.entity.DataAsStringEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import tools.jackson.databind.ObjectMapper;

import java.time.OffsetDateTime;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class DataAsStringDAOIntegrationTest extends BaseIntegrationTests {

    @Autowired
    private DataAsStringDAO dataAsStringDAO;

    @Test
    public void shouldSave() {
        var entity = createEntity();

        var savedEntity = dataAsStringDAO.save(entity);

        assertNotNull(savedEntity);
        assertNotNull(savedEntity.getId());
        assertNotNull(savedEntity.getData());
        assertNotNull(savedEntity.getBinaryData());
        assertNotNull(savedEntity.getCreatedAt());
    }

    private ObjectMapper createMapper() {
        return new ObjectMapper();
    }

    private DataAsStringEntity createEntity() {
        var mapper = createMapper();

        var dataDTO = createDataDTO();

        var jsonData = mapper.writeValueAsString(dataDTO);

        var entity = new DataAsStringEntity();
        entity.setData(jsonData);
        entity.setBinaryData(jsonData);
        entity.setCreatedAt(OffsetDateTime.now());
        return entity;
    }
}
