package com.gabrielspassos.controller.v1.response;

import java.math.BigDecimal;

public record ExchangeResponse(
        String date,
        BigDecimal usd,
        BigDecimal brl
) {
}
