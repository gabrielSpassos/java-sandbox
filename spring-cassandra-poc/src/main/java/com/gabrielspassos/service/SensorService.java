package com.gabrielspassos.service;

import com.gabrielspassos.domain.SensorEvent;
import com.gabrielspassos.repository.SensorEventRepository;
import org.springframework.stereotype.Service;

@Service
public class SensorService {

    private final SensorEventRepository sensorEventRepository;

    public SensorService(SensorEventRepository sensorEventRepository) {
        this.sensorEventRepository = sensorEventRepository;
    }

    public SensorEvent save(SensorEvent sensorEvent) {
        return sensorEventRepository.save(sensorEvent);
    }

    boolean deleteAll() {
        sensorEventRepository.deleteAll();
        return true;
    }
}
