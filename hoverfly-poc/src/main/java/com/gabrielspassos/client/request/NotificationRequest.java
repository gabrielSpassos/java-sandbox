package com.gabrielspassos.client.request;

import java.math.BigDecimal;

public record NotificationRequest(String userId, NotificationEventType eventType, BigDecimal exchangeValue) {
}
