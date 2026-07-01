package com.gabrielspassos.client.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.math.BigDecimal;

@JsonIgnoreProperties(ignoreUnknown = true)
public record UsdRates(
        BigDecimal brl
) {
}