package com.gabrielspassos.controller;

import com.gabrielspassos.controller.request.CalculateDungeonHealthRequest;
import com.gabrielspassos.controller.response.CalculateDungeonHealthResponse;
import com.gabrielspassos.service.DungeonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
