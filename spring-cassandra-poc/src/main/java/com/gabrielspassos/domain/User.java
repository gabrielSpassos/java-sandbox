package com.gabrielspassos.domain;

import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.time.LocalDate;

@Table("users")
public record User(
        @PrimaryKey
        String id,
        String name,
        @Column("birth_date")
        LocalDate birthDate
) {
}
