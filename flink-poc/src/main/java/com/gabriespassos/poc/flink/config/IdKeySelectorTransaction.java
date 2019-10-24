package com.gabriespassos.poc.flink.config;

import org.apache.flink.api.java.functions.KeySelector;
import org.apache.flink.api.java.tuple.Tuple3;

public class IdKeySelectorTransaction implements KeySelector<Tuple3<String, Integer, Double>, Integer> {

    @Override
    public Integer getKey(Tuple3<String, Integer, Double> value) throws Exception {
        return value.f1;
    }
}
