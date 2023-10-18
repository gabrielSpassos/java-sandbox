package org.gabrielspassos.checkout;

import java.math.BigDecimal;

import static org.gabrielspassos.util.AmountUtil.getAmountEquivalentToPercentage;
import static org.gabrielspassos.util.AmountUtil.setScaleAndRound;

public class BankTransfer implements CheckOut {

    private final BigDecimal baseDiscountPercentage = BigDecimal.valueOf(5);
    private final BigDecimal discountCupom;

    public BankTransfer(BigDecimal discountCupom) {
        this.discountCupom = discountCupom;
    }

    @Override
    public BigDecimal calculatePercentageCupomDiscount(BigDecimal paymentAmount) {
        return getAmountEquivalentToPercentage(paymentAmount, this.discountCupom);
    }

    @Override
    public BigDecimal executePayment(BigDecimal amount, BigDecimal discount, Long installments, String customerType) {
        BigDecimal amountLessDiscount = setScaleAndRound(amount.subtract(discount));
        BigDecimal bankTransferDiscount = getAmountEquivalentToPercentage(amountLessDiscount, baseDiscountPercentage);
        return setScaleAndRound(amountLessDiscount.subtract(bankTransferDiscount));
    }
}
