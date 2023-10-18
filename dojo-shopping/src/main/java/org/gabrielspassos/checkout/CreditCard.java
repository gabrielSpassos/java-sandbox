package org.gabrielspassos.checkout;

import java.math.BigDecimal;

import static org.gabrielspassos.util.AmountUtil.getAmountEquivalentToPercentage;
import static org.gabrielspassos.util.AmountUtil.setScaleAndRound;

public class CreditCard implements CheckOut{

    private final BigDecimal discountCupom;

    public CreditCard(BigDecimal discountCupom) {
        this.discountCupom = discountCupom;
    }

    @Override
    public BigDecimal calculatePercentageCupomDiscount(BigDecimal paymentAmount) {
        return getAmountEquivalentToPercentage(paymentAmount, this.discountCupom);
    }

    @Override
    public BigDecimal executePayment(BigDecimal amount, BigDecimal discount, Long installments, String customerType) {
        return setScaleAndRound(amount.subtract(discount));
    }
}
