package com.gabrielspassos.poc.utils;

import com.google.common.util.concurrent.ListenableFuture;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ConcurrencyUtilsTest {

    private static ConcurrencyUtils concurrencyUtils;

    @BeforeAll
    public static void setup() {
        concurrencyUtils = new ConcurrencyUtils();
    }

    @Test
    void shouldReturnFiveWithCallBacks() throws ExecutionException, InterruptedException {
        ListenableFuture<Integer> listenableFutureWithCallback = concurrencyUtils.createListenableFutureWithCallback(5);

        assertEquals(5, listenableFutureWithCallback.get());
    }

    @Test
    void shouldThrowErrorForEvenNumberWithCallback() {
        ListenableFuture<Integer> listenableFutureWithCallback = concurrencyUtils.createListenableFutureWithCallback(4);

        ExecutionException executionException = assertThrows(ExecutionException.class, listenableFutureWithCallback::get);

        assertEquals("java.lang.RuntimeException: Value 4 is even", executionException.getMessage());
    }
}