package com.gabrielspassos.service;

import static java.util.Objects.isNull;

public class HashService {

    public int hash(String input) {
        if (isNull(input) || input.isBlank()) {
            return 0;
        }

        int hash = 7;
        for (char c : input.toCharArray()) {
            hash = hash * 31 + c;
        }
        return Math.abs(hash);
    }

    public int basicHash(String input) {
        if (isNull(input) || input.isBlank()) {
            return -1;
        }

        int hash = 7;
        for (char c : input.toCharArray()) {
            hash = hash * 31 + c;
        }
        return hash;
    }

    public long djb2Hash(String input) {
        if (isNull(input) || input.isBlank()) {
            return -1;
        }

        long hash = 5381;
        for (char c : input.toCharArray()) {
            hash = ((hash << 5) + hash) + c;
        }
        return hash;
    }

}
