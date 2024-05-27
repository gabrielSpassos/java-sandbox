package com.gabrielspassos;

import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisURI;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;

public class RedisBackedCache {

    private final RedisClient redisClient;
    private final StatefulRedisConnection<String, String> connection;

    public RedisBackedCache(String address, Integer port) {
        this.redisClient = RedisClient.create(createUri(address, port));
        this.connection = redisClient.connect();
    }

    public String put(String key, String value) {
        RedisCommands<String, String> syncCommands = connection.sync();
        return syncCommands.set(key, value);
    }

    public String get(String key) {
        RedisCommands<String, String> syncCommands = connection.sync();
        return syncCommands.get(key);
    }

    private RedisURI createUri(String address, Integer port) {
        return RedisURI.Builder
                .redis("localhost", 6379)
                .withPassword("password")
                .withDatabase(1)
                .build();
    }
}
