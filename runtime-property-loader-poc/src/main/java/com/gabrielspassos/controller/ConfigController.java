package com.gabrielspassos.controller;

import com.gabrielspassos.config.ConfigManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ConfigController {

    @Autowired
    private ConfigManager configManager;

    @GetMapping("/configs")
    public ResponseEntity<?> getConfigs() {
        List<String> configs = configManager.getConfigs();
        return ResponseEntity.ok(configs);
    }

}
