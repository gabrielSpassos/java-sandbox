package com.gabrielspassos.poc.entity.value;

public record JsonbPayload(String value) {

    @Override
    public String toString() {
        return value;
    }
}
