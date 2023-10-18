package org.gabrielspassos.checkout;

import java.math.BigDecimal;

import static org.gabrielspassos.util.AmountUtil.getAmountEquivalentToPercentage;
import static org.gabrielspassos.util.AmountUtil.setScaleAndRound;

public class BuyNowPayLater implements CheckOut {

    private final BigDecimal feePercentage = BigDecimal.valueOf(3);
    private final BigDecimal discountCupom;

    public BuyNowPayLater(BigDecimal discountCupom) {
        this.discountCupom = discountCupom;
    }

    @Override
    public BigDecimal calculatePercentageCupomDiscount(BigDecimal paymentAmount) {
        return getAmountEquivalentToPercentage(paymentAmount, this.discountCupom);
    }

    @Override
    public BigDecimal executePayment(BigDecimal amount, BigDecimal discount, Long installments, String customerType) {
        BigDecimal finalFeePercentage = "Gold".equals(customerType) ? BigDecimal.ZERO : feePercentage;
        BigDecimal amountLessDiscount = setScaleAndRound(amount.subtract(discount));
        BigDecimal feeAmount = getAmountEquivalentToPercentage(amountLessDiscount, finalFeePercentage);
        BigDecimal finalAmount = setScaleAndRound(amountLessDiscount.add(feeAmount));
        return setScaleAndRound(finalAmount.divide(BigDecimal.valueOf(installments)));
    }

}
