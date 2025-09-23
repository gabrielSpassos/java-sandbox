package com.gabrielspassos.poc.repository;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CalculatorRepository {

    private final List<Integer> dataStore = new ArrayList<>();

    public void save(int value) {
        dataStore.add(value);
    }

    public List<Integer> findAll() {
        return new ArrayList<>(dataStore);
    }
}
