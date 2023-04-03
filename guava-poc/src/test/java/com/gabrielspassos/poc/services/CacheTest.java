package com.gabrielspassos.poc.services;

import com.google.common.base.Strings;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.cache.RemovalListener;
import com.google.common.cache.RemovalNotification;
import com.google.common.cache.Weigher;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class CacheTest {

    private LoadingCache<String, String> cache;

    @Test
    void testCache() throws InterruptedException {
        CacheLoader<String, String> loader = new CacheLoader<>() {
            public String load(String key) {
                return key.toUpperCase();
            }

            public ListenableFuture<String> reload(String key, String oldValue) {
                String newValue = Strings.repeat(oldValue, 2);
                System.out.printf("Key: %s, Old value: %s, new value: %s%n", key, oldValue, newValue);
                return Futures.immediateFuture(newValue);
            }
        };
        RemovalListener<String, String> removalListener = new RemovalListener<>() {
            public void onRemoval(RemovalNotification<String, String> removal) {
                System.out.println("Removal notification: " + removal);
            }
        };
        LoadingCache<String, String> cache = CacheBuilder.newBuilder()
                .maximumSize(4)
                .removalListener(removalListener)
                .refreshAfterWrite(20, TimeUnit.MILLISECONDS)
                .expireAfterWrite(2, TimeUnit.SECONDS)
                .build(loader);

        assertEquals(0, cache.size());
        String key = cache.getUnchecked("key");
        assertEquals(1, cache.size());
        assertEquals("KEY", key);
        TimeUnit.SECONDS.sleep(1);
        assertEquals("KEYKEY", cache.getIfPresent("key"));
        TimeUnit.SECONDS.sleep(5);
        assertNull(cache.getIfPresent("key"));
    }

    @Test
    void shouldInsertOnCache() {
        cache = CacheBuilder.newBuilder()
                .build(createCacheLoader());

        String output = cache.getUnchecked("hello");
        long cacheSize = cache.size();

        assertEquals("HELLO", output);
        assertEquals(1, cacheSize);
        assertEquals("HELLO", cache.getIfPresent("hello"));
    }

    @Test
    void shouldHaveOnlyFourItemsOnCache() {
        cache = CacheBuilder.newBuilder()
                .maximumSize(4)
                .build(createCacheLoader());

        cache.getUnchecked("first");
        cache.getUnchecked("second");
        cache.getUnchecked("third");
        cache.getUnchecked("four");
        cache.getUnchecked("five");

        assertEquals(4, cache.size());
        assertNull(cache.getIfPresent("first"));
        assertEquals("FIVE", cache.getIfPresent("five"));
    }

    @Test
    void shouldHaveOnlyThreeItemsOnCache() {
        cache = CacheBuilder.newBuilder()
                .weigher(createCacheWeigher())
                .maximumWeight(20)
                .build(createCacheLoader());

        cache.getUnchecked("aaaaaa");
        cache.getUnchecked("bbbbbb");
        cache.getUnchecked("cccccc");
        cache.getUnchecked("dddddd");

        assertEquals(2, cache.size());
        assertNull(cache.getIfPresent("bbbbbb"));
        assertNull(cache.getIfPresent("cccccc"));
        assertEquals("AAAAAA", cache.getIfPresent("aaaaaa"));
        assertEquals("DDDDDD", cache.getIfPresent("dddddd"));
    }

    @Test
    void shouldRetainValueForTime() throws InterruptedException {
        cache = CacheBuilder.newBuilder()
                .expireAfterAccess(2, TimeUnit.MILLISECONDS)
                .build(createCacheLoader());

        cache.getUnchecked("hello");
        assertEquals(1, cache.size());

        cache.getUnchecked("hello");
        assertEquals(1, cache.size());
        Thread.sleep(300);

        cache.getUnchecked("test");
        assertEquals(1, cache.size());
        assertNull(cache.getIfPresent("hello"));
    }

    @Test
    void whenEntryLiveTimeExpire_thenEviction() throws InterruptedException {
        cache = CacheBuilder.newBuilder()
                .expireAfterWrite(2, TimeUnit.MILLISECONDS)
                .build(createCacheLoader());

        cache.getUnchecked("hello");
        assertEquals(1, cache.size());

        cache.getUnchecked("hello");
        assertEquals(1, cache.size());
        Thread.sleep(300);

        cache.getUnchecked("test");
        assertEquals(1, cache.size());
        assertNull(cache.getIfPresent("hello"));
    }

    @Test
    public void whenNullValue_thenOptional() {
        LoadingCache<String, Optional<String>> cache
                = CacheBuilder.newBuilder().build(createCacheLoaderWithNullHandler());

        assertEquals("text.txt", cache.getUnchecked("text.txt").get());
    }

    private CacheLoader<String, String> createCacheLoader() {
        return new CacheLoader<>() {
            @Override
            public String load(String key) {
                return key.toUpperCase();
            }
        };
    }

    private CacheLoader<String, Optional<String>> createCacheLoaderWithNullHandler() {
        return new CacheLoader<>() {
            @Override
            public Optional<String> load(String key) {
                return Optional.ofNullable(key);
            }
        };
    }

    private Weigher<String, String> createCacheWeigher() {
        return new Weigher<>() {
            @Override
            public int weigh(String key, String value) {
                return value.length();
            }
        };
    }

}