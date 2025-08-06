package com.gabrielspassos.controller;

import com.gabrielspassos.controller.request.CalculateDungeonHealthRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1")
public class DungeonController {

    @PostMapping("/dungeons")
    public ResponseEntity<Integer> calculateMinimumHealth(@RequestBody CalculateDungeonHealthRequest request) {
        return ResponseEntity.ok(1);
    }

}
