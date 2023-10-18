package org.gabrielspassos.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class AmountUtil {

    private static final BigDecimal ONE_HUNDRED = BigDecimal.valueOf(100.00);

    public static BigDecimal setScaleAndRound(BigDecimal amount) {
        return amount.setScale(2, RoundingMode.CEILING);
    }

    public static BigDecimal getAmountEquivalentToPercentage(BigDecimal amount, BigDecimal amountPercentage) {
        BigDecimal amountEquivalentToPercentage = (amount.multiply(amountPercentage)).divide(ONE_HUNDRED, RoundingMode.CEILING);
        return setScaleAndRound(amountEquivalentToPercentage);
    }
}
