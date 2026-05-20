package com.gabrielspassos.mapper;

import com.gabrielspassos.entity.ItemStatus;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

public class StringMapper {

    public static String fromUUID(UUID uuid) {
        return Optional.ofNullable(uuid).map(UUID::toString).orElse(null);
    }

    public static String fromLocalDateTime(LocalDateTime localDateTime) {
        return Optional.ofNullable(localDateTime).map(LocalDateTime::toString).orElse(null);
    }

    public static String fromItemStatus(ItemStatus itemStatus) {
        return Optional.ofNullable(itemStatus).map(Enum::name).orElse(null);
    }
}
