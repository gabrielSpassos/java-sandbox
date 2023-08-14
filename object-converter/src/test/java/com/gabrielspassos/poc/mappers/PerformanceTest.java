package com.gabrielspassos.poc.mappers;

import com.gabrielspassos.poc.cacheablereflections.CacheableReflectionsConverterMapper;
import com.gabrielspassos.poc.dto.PerformanceOneDTO;
import com.gabrielspassos.poc.dto.PerformanceTwoDTO;
import com.gabrielspassos.poc.memory.InMemoryClassObjectConverterMapper;
import com.gabrielspassos.poc.reflections.ObjectConverterMapper;
import com.gabrielspassos.poc.unsafe.UnsafeObjectConverterMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PerformanceTest {

    private static InMemoryClassObjectConverterMapper inMemoryClassObjectConverterMapper;
    private static CacheableReflectionsConverterMapper cacheableReflectionsConverterMapper;
    private static ObjectConverterMapper objectWithoutCacheConverterMapper;
    private static UnsafeObjectConverterMapper unsafeObjectConverterMapper;

    @BeforeAll
    static void setup() {
        inMemoryClassObjectConverterMapper = InMemoryClassObjectConverterMapper.getMapper();
        cacheableReflectionsConverterMapper = CacheableReflectionsConverterMapper.getCacheableReflectionsConverterMapper();
        objectWithoutCacheConverterMapper = ObjectConverterMapper.getObjectConverterMapper();
        unsafeObjectConverterMapper = UnsafeObjectConverterMapper.getUnsafeObjectConverterMapper();
    }

    @Test
    void shouldTestPerformance() {
        // scenario 1 - 1 iteration
        int one = 1;
        testPerformanceBaseScenario(one);

        // scenario 2 - 10 iteration
        int ten = 10;
        testPerformanceBaseScenario(ten);

        // scenario 3 - 100 iteration
        int oneHundred = 100;
        testPerformanceBaseScenario(oneHundred);

        // scenario 4 - 1K iteration
        int oneThousand = 1000;
        testPerformanceBaseScenario(oneThousand);

        // scenario 5 - 10K iteration
        int tenThousand = 10000;
        testPerformanceBaseScenario(tenThousand);

        // scenario 6 - 1M iteration
        int oneMillion = 1000000;
        testPerformanceBaseScenario(oneMillion);

        // scenario 7 - 10M iteration
        int tenMillion = 10000000;
        testPerformanceBaseScenario(tenMillion);

        // scenario 8 - 30M iteration
        int thirtyMillion = 30000000;
        testPerformanceBaseScenario(thirtyMillion);
    }

    private void testPerformanceBaseScenario(int iterations) {
        System.out.printf("Test Performance with %s iterations%n", iterations);
        long startWithoutCache = System.currentTimeMillis();
        for (int i = 0; i < iterations; i++) {
            PerformanceOneDTO performanceOneDTO = buildPerformanceOneDTO();
            PerformanceTwoDTO firstConvertedObject = objectWithoutCacheConverterMapper.convert(performanceOneDTO, PerformanceTwoDTO.class);
            PerformanceOneDTO secondConvertedObject = objectWithoutCacheConverterMapper.convert(firstConvertedObject, PerformanceOneDTO.class);

            assertEquals(firstConvertedObject.getId(), secondConvertedObject.getId());
            assertEquals(firstConvertedObject.getName(), secondConvertedObject.getName());
            assertEquals(firstConvertedObject.getValue(), secondConvertedObject.getValue());
        }
        long finishWithoutCache = System.currentTimeMillis();
        System.out.print("Time in milliseconds without cache converter: ");
        System.out.println(finishWithoutCache - startWithoutCache);

        long startWithCache = System.currentTimeMillis();
        for (int i = 0; i < iterations; i++) {
            PerformanceOneDTO performanceOneDTO = buildPerformanceOneDTO();
            PerformanceTwoDTO firstConvertedObject = cacheableReflectionsConverterMapper.convert(performanceOneDTO, PerformanceTwoDTO.class);
            PerformanceOneDTO secondConvertedObject = cacheableReflectionsConverterMapper.convert(firstConvertedObject, PerformanceOneDTO.class);

            assertEquals(firstConvertedObject.getId(), secondConvertedObject.getId());
            assertEquals(firstConvertedObject.getName(), secondConvertedObject.getName());
            assertEquals(firstConvertedObject.getValue(), secondConvertedObject.getValue());
        }
        long finishWithCache = System.currentTimeMillis();
        System.out.print("Time in milliseconds with cacheable converter: ");
        System.out.println(finishWithCache - startWithCache);

        long startInMemory = System.currentTimeMillis();
        for (int i = 0; i < iterations; i++) {
            PerformanceOneDTO performanceOneDTO = buildPerformanceOneDTO();
            PerformanceTwoDTO firstConvertedObject = inMemoryClassObjectConverterMapper.convert(performanceOneDTO, PerformanceTwoDTO.class);
            PerformanceOneDTO secondConvertedObject = inMemoryClassObjectConverterMapper.convert(firstConvertedObject, PerformanceOneDTO.class);

            assertEquals(firstConvertedObject.getId(), secondConvertedObject.getId());
            assertEquals(firstConvertedObject.getName(), secondConvertedObject.getName());
            assertEquals(firstConvertedObject.getValue(), secondConvertedObject.getValue());
        }
        long finishInMemory = System.currentTimeMillis();
        System.out.print("Time in milliseconds with in memory converter: ");
        System.out.println(finishInMemory - startInMemory);

        long startUnsafe = System.currentTimeMillis();
        for (int i = 0; i < iterations; i++) {
            PerformanceOneDTO performanceOneDTO = buildPerformanceOneDTO();
            PerformanceTwoDTO firstConvertedObject = unsafeObjectConverterMapper.convert(performanceOneDTO, PerformanceTwoDTO.class);
            PerformanceOneDTO secondConvertedObject = unsafeObjectConverterMapper.convert(firstConvertedObject, PerformanceOneDTO.class);

            assertEquals(firstConvertedObject.getId(), secondConvertedObject.getId());
            assertEquals(firstConvertedObject.getName(), secondConvertedObject.getName());
            assertEquals(firstConvertedObject.getValue(), secondConvertedObject.getValue());
        }
        long finishUnsafe = System.currentTimeMillis();
        System.out.print("Time in milliseconds with unsafe converter: ");
        System.out.println(finishUnsafe - startUnsafe);
        System.out.println("*******************************************************");
    }

    private PerformanceOneDTO buildPerformanceOneDTO() {
        return new PerformanceOneDTO(
                UUID.randomUUID().toString(),
                UUID.randomUUID().toString(),
                UUID.randomUUID().toString()
        );
    }

}
