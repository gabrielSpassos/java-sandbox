package com.gabrielspassos.service;

import com.gabrielspassos.domain.CarEntity;
import com.gabrielspassos.dto.CarDTO;
import com.gabrielspassos.dto.WarehouseDTO;
import com.gabrielspassos.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CarService {

    @Autowired
    private WarehouseService warehouseService;

    @Autowired
    private CarRepository carRepository;

    public CarDTO create(String brand, String name, String warehouseName) {
        WarehouseDTO warehouseDTO = warehouseService.findOrCreate(warehouseName);
        CarEntity carEntity = new CarEntity(UUID.randomUUID().toString(), warehouseDTO.getId(), brand, name, true);
        CarEntity saved = carRepository.save(carEntity);
        return new CarDTO(saved);
    }

    public List<CarDTO> findByWarehouseId(String warehouseId) {
        return carRepository.findByWarehouseId(warehouseId)
                .stream()
                .map(CarDTO::new)
                .toList();
    }

    public Optional<CarDTO> findById(String carId) {
        return carRepository.findById(carId)
                .map(CarDTO::new);
    }
}
