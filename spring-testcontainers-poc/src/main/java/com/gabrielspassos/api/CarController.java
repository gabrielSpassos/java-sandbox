package com.gabrielspassos.api;

import com.gabrielspassos.dto.CarDTO;
import com.gabrielspassos.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1")
public class CarController {

    @Autowired
    private CarService carService;

    @PostMapping("/warehouses/{warehouseName}/cars/{brand}/{name}")
    public ResponseEntity<?> createCar(@PathVariable("warehouseName") String warehouseName,
                                       @PathVariable("brand") String brand,
                                       @PathVariable("name") String name) {
        CarDTO carDTO = carService.create(brand, name, warehouseName);
        return ResponseEntity.ok(carDTO);
    }

    @GetMapping("/cars/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") String id) {
        Optional<CarDTO> carById = carService.findById(id);
        return carById.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/warehouses/{id}/cars")
    public ResponseEntity<?> findByWarehouseId(@PathVariable("id") String warehouseId) {
        List<CarDTO> cars = carService.findByWarehouseId(warehouseId);
        return ResponseEntity.ok(cars);
    }


}
