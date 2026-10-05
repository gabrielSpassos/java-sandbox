package com.gabrielspassos.service;

import com.gabrielspassos.client.ExchangeClient;
import com.gabrielspassos.client.response.UsdRates;
import com.gabrielspassos.client.response.UsdResponse;
import com.gabrielspassos.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

@Service
public class ExchangeService {

    @Autowired
    private UserService userService;

    @Autowired
    private ExchangeClient exchangeClient;

    public UsdResponse getUsdExchange(String userId) {
        UserEntity user = userService.findById(userId);

        UsdResponse response = exchangeClient.getUsdToBrl();

        return formatResponse(response);
    }

    private UsdResponse formatResponse(UsdResponse usdResponse) {
        var brlValue = Objects.nonNull(usdResponse) && Objects.nonNull(usdResponse.usd())
                ? formatBigDecimal(usdResponse.usd().brl())
                : null;

        var usdRates = new UsdRates(brlValue);
        return new UsdResponse(usdResponse.date(), usdRates);
    }

    private BigDecimal formatBigDecimal(BigDecimal value) {
        if (Objects.isNull(value)) {
            return null;
        }

        return value.setScale(2, RoundingMode.HALF_UP);
    }
}
