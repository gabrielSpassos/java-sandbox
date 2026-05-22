package com.gabrielspassos.debezium.poc.controller.request;

import java.math.BigDecimal;

public record OrderRequest(String productName, BigDecimal amount) {
}
