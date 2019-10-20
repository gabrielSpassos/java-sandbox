package com.gabriespassos.poc.flink.config;

import org.apache.flink.api.java.functions.KeySelector;
import org.apache.flink.api.java.tuple.Tuple2;

public class IdKeySelectorTransaction implements KeySelector<Tuple2<Integer, String>, Integer> {

    @Override
    public Integer getKey(Tuple2<Integer, String> value) throws Exception {
        return value.f0;
    }
}
