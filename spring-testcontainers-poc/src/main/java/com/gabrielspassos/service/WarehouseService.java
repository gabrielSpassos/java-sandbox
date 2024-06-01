package com.gabrielspassos.service;

import com.gabrielspassos.domain.WarehouseEntity;
import com.gabrielspassos.dto.WarehouseDTO;
import com.gabrielspassos.repository.WarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class WarehouseService {

    @Autowired
    private WarehouseRepository warehouseRepository;

    public WarehouseDTO findOrCreate(String name) {
        WarehouseEntity warehouseEntity = warehouseRepository.findByName(name).orElseGet(() -> save(name));
        return new WarehouseDTO(warehouseEntity);
    }

    private WarehouseEntity save(String name) {
        WarehouseEntity warehouseEntity = new WarehouseEntity(UUID.randomUUID().toString(), name, true);
        return warehouseRepository.save(warehouseEntity);
    }
}
