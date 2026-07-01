package com.gabrielspassos.mapper;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.UUID;

public final class StringMapper {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

    private StringMapper() {
    }

    public static String fromUUID(UUID uuid) {
        return Optional.ofNullable(uuid).map(UUID::toString).orElse(null);
    }

    public static String fromLocalDateTime(LocalDateTime localDateTime) {
        return Optional.ofNullable(localDateTime).map(date -> date.format(FORMATTER)).orElse(null);
    }

}
