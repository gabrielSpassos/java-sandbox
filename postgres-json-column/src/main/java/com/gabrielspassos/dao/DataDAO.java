package com.gabrielspassos.dao;

import com.gabrielspassos.dao.repository.DataRepository;
import com.gabrielspassos.entity.DataEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.StreamSupport;

@Component
public class DataDAO {

    @Autowired
    private DataRepository dataRepository;

    public DataEntity save(DataEntity dataEntity) {
        return dataRepository.save(dataEntity);
    }

    public Boolean delete(DataEntity dataEntity) {
        dataRepository.delete(dataEntity);
        return true;
    }

    public List<DataEntity> findAll() {
        var all = dataRepository.findAll();
        return StreamSupport.stream(all.spliterator(), false)
                .toList();
    }
}
