package com.gabrielspassos.mapper;

import com.gabrielspassos.client.response.UsdRates;
import com.gabrielspassos.client.response.UsdResponse;
import com.gabrielspassos.controller.v1.response.ExchangeRatesResponse;
import com.gabrielspassos.controller.v1.response.ExchangeResponse;

public final class ExchangeMapper {

    private ExchangeMapper() {
    }

    public static ExchangeResponse toResponse(UsdResponse usdResponse) {
        var rates = toResponse(usdResponse.usd());

        return new ExchangeResponse(usdResponse.date(), rates);
    }

    private static ExchangeRatesResponse toResponse(UsdRates usdRates) {
        return new ExchangeRatesResponse(usdRates.brl());
    }
}
