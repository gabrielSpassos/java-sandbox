package com.gabrielspassos.controller.v1.response;

public record ExchangeResponse(
        String date,
        ExchangeRatesResponse usd
) {
}
