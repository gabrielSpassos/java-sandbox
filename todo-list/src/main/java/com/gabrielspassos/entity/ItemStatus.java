package com.gabrielspassos.entity;

import java.util.stream.Stream;

public enum ItemStatus {

    TO_DO,
    DONE;

    public static ItemStatus convert(String status) {
        return Stream.of(ItemStatus.values())
                .filter(itemStatus -> itemStatus.name().equalsIgnoreCase(status))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("invalid status"));
    }
}
