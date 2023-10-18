package org.gabrielspassos.checkout;

import java.math.BigDecimal;

public interface CheckOut {

    BigDecimal executePayment(BigDecimal amount, BigDecimal discount, Long installments, String customerType);

    BigDecimal calculatePercentageCupomDiscount(BigDecimal paymentAmount);
}
