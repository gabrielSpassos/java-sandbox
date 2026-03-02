package com.gabrielspassos.patterns.behavioral.chainOfResponsibility;

import java.math.BigDecimal;

public class Dollar10Dispenser implements DispenseChain {

    private DispenseChain chain;

    @Override
    public boolean setNextChain(DispenseChain nextChain) {
        this.chain = nextChain;
        return true;
    }

    @Override
    public Money dispense(Money money) {
        if (money.getAmount() < 10) {
            return this.chain.dispense(money);
        }

        var num = money.getAmount() / 10;
        var remainder = money.getAmount() % 10;
        IO.println("Dispensing "+num+" 10$ note");
        if(remainder != 0) {
            this.chain.dispense(new Money(remainder));
        }
        return money;
    }

}
