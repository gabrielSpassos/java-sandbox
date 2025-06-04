package com.gabrielspassos.repository;

import com.gabrielspassos.domain.CarEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends CrudRepository<CarEntity, String> {

    List<CarEntity> findByWarehouseId(String warehouseId);

}
