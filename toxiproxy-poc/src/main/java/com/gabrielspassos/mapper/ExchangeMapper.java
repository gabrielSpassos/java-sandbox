package com.gabrielspassos.mapper;

import com.gabrielspassos.client.response.UsdResponse;
import com.gabrielspassos.controller.v1.response.ExchangeResponse;

import java.math.BigDecimal;

public final class ExchangeMapper {

    private ExchangeMapper() {
    }

    public static ExchangeResponse toResponse(UsdResponse usdResponse) {
        return new ExchangeResponse(usdResponse.date(), BigDecimal.ONE, usdResponse.usd().brl());
    }

}
