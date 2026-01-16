package com.gabrielspassos.dao;

import com.gabrielspassos.BaseIntegrationTests;
import com.gabrielspassos.entity.DataEntity;
import org.junit.jupiter.api.Test;
import org.postgresql.util.PGobject;
import org.springframework.beans.factory.annotation.Autowired;
import tools.jackson.databind.ObjectMapper;

import java.sql.SQLException;
import java.time.OffsetDateTime;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class DataDAOIntegrationTest extends BaseIntegrationTests {

    @Autowired
    private DataDAO dataDAO;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void shouldSave() throws SQLException {
        var entity = createEntity();

        var savedEntity = dataDAO.save(entity);

        assertNotNull(savedEntity);
        assertNotNull(savedEntity.getId());
        assertNotNull(savedEntity.getData());
        assertNotNull(savedEntity.getBinaryData());
        assertNotNull(savedEntity.getCreatedAt());
    }

    private DataEntity createEntity() throws SQLException {
        var dataDTO = createDataDTO();
        var jsonData = objectMapper.writeValueAsString(dataDTO);

        var pgData = new PGobject();
        pgData.setType("json");
        pgData.setValue(jsonData);

        var pgDataB = new PGobject();
        pgDataB.setType("jsonb");
        pgDataB.setValue(jsonData);

        var entity = new DataEntity();
        entity.setData(pgData);
        entity.setBinaryData(pgDataB);
        entity.setCreatedAt(OffsetDateTime.now());
        return entity;
    }

}
