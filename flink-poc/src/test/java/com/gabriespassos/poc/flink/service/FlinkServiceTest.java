package com.gabriespassos.poc.flink.service;

import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.api.java.tuple.Tuple3;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class FlinkServiceTest {

    private FlinkService flinkService;

    @Before
    public void setup() {
        this.flinkService = new FlinkService();
    }

    @Test
    public void shouldJoinData() throws Exception {
        List<Tuple2<Tuple2<Integer, String>, Tuple3<Integer, String, String>>> joined = flinkService.joinDataSources();

        assertEquals(1, joined.size());
        assertEquals("transaction_1", joined.get(0).f0.f1);
        assertEquals("London", joined.get(0).f1.f2);
    }
}
