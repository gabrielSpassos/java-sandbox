package com.gabrielspassos.controller;

import com.gabrielspassos.controller.request.CalculateDungeonHealthRequest;
import com.gabrielspassos.controller.response.CalculateDungeonHealthResponse;
import com.gabrielspassos.service.DungeonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1")
public class DungeonController {

    @Autowired
    private DungeonService dungeonService;

    @PostMapping("/dungeons")
    public ResponseEntity<CalculateDungeonHealthResponse> calculateMinimumHealth(@RequestBody CalculateDungeonHealthRequest request) {
        var response = dungeonService.calculateMinimumHealth(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/dungeons")
    public ResponseEntity<List<CalculateDungeonHealthResponse>> findAll() {
        var responses = dungeonService.findAll();
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/dungeons/{id}")
    public ResponseEntity<CalculateDungeonHealthResponse> findById(@PathVariable String id) {
        var response = dungeonService.findById(id);
        return ResponseEntity.ok(response);
    }

}
