package com.gabrielspassos.service;

import com.gabrielspassos.client.ExchangeClient;
import com.gabrielspassos.client.NotificationClient;
import com.gabrielspassos.client.request.NotificationEventType;
import com.gabrielspassos.client.request.NotificationRequest;
import com.gabrielspassos.client.response.UsdRates;
import com.gabrielspassos.client.response.UsdResponse;
import com.gabrielspassos.dto.Pair;
import com.gabrielspassos.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.Objects;

@Service
public class ExchangeService {

    @Autowired
    private UserService userService;

    @Autowired
    private ExchangeClient exchangeClient;

    @Autowired
    private NotificationClient notificationClient;

    @Value("${exchange.notification.threshold.lower}")
    private String lowerNotificationThreshold;

    @Value("${exchange.notification.threshold.upper}")
    private String upperNotificationThreshold;

    public UsdResponse getUsdExchange(String userId) {
        UserEntity user = userService.findById(userId);

        UsdResponse exchangeResponse = exchangeClient.getUsdToBrl();

        UsdResponse response = formatResponse(exchangeResponse);

        var pair = shouldNotifyUser(response);
        if (pair.left()) {
            var notificationRequest
                    = new NotificationRequest(user.getId().toString(), pair.right(), response.usd().brl());
            notificationClient.notifyUser(notificationRequest);
        }

        return response;
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

    private Pair<Boolean, NotificationEventType> shouldNotifyUser(UsdResponse exchangeResponse) {
        var exchangeBrlRate = exchangeResponse.usd().brl();

        if (null == exchangeBrlRate) {
            return new Pair<>(false, null);
        }

        var isToday = LocalDate.now().toString().equals(exchangeResponse.date());

        if (isToday && new BigDecimal(lowerNotificationThreshold).compareTo(exchangeBrlRate) > 0) {
            return new Pair<>(true, NotificationEventType.BUY);
        }

        if (isToday&& new BigDecimal(upperNotificationThreshold).compareTo(exchangeBrlRate) < 0) {
            return new Pair<>(true, NotificationEventType.SELL);
        }

        return new Pair<>(false, null);
    }
}
