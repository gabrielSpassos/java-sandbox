package com.gabrielspassos.patterns.behavioral.strategy;

import java.math.BigDecimal;

public interface PaymentStrategy {

    BigDecimal pay(BigDecimal amount);

}
