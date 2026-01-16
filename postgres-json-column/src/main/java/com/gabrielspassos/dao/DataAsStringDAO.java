package com.gabrielspassos.dao;

import com.gabrielspassos.dao.repository.DataAsStringRepository;
import com.gabrielspassos.entity.DataAsStringEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.StreamSupport;

@Component
public class DataAsStringDAO {

    @Autowired
    private DataAsStringRepository dataAsStringRepository;

    public DataAsStringEntity save(DataAsStringEntity dataAsStringEntity) {
        var entityId = Optional.ofNullable(dataAsStringEntity.getId()).orElse(UUID.randomUUID());
        dataAsStringRepository.insert(
                entityId,
                dataAsStringEntity.getData(),
                dataAsStringEntity.getBinaryData(),
                dataAsStringEntity.getCreatedAt()
        );

        return dataAsStringRepository.findById(entityId).orElseThrow(IllegalStateException::new);
    }

    public Boolean delete(DataAsStringEntity dataEntity) {
        dataAsStringRepository.delete(dataEntity);
        return true;
    }

    public List<DataAsStringEntity> findAll() {
        var all = dataAsStringRepository.findAll();
        return StreamSupport.stream(all.spliterator(), false)
                .toList();
    }
}
