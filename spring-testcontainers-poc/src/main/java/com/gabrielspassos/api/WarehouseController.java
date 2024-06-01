package com.gabrielspassos.api;

import com.gabrielspassos.dto.WarehouseDTO;
import com.gabrielspassos.service.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1")
public class WarehouseController {

    @Autowired
    private WarehouseService warehouseService;

    @PostMapping("/warehouses/{name}")
    public ResponseEntity<?> createWarehouse(@PathVariable("name") String name) {
        WarehouseDTO warehouseDTO = warehouseService.findOrCreate(name);
        return ResponseEntity.ok(warehouseDTO);
    }


}
