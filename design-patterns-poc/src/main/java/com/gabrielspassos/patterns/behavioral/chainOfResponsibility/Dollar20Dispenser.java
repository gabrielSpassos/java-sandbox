package com.gabrielspassos.patterns.behavioral.chainOfResponsibility;

import java.math.BigDecimal;

public class Dollar20Dispenser implements DispenseChain {

    private DispenseChain chain;

    @Override
    public boolean setNextChain(DispenseChain nextChain) {
        this.chain = nextChain;
        return true;
    }

    @Override
    public Money dispense(Money money) {
        if (money.getAmount() < 20) {
            return this.chain.dispense(money);
        }

        var num = money.getAmount() / 20;
        var remainder = money.getAmount() % 20;
        IO.println("Dispensing "+num+" 20$ note");
        if(remainder != 0) {
            this.chain.dispense(new Money(remainder));
        }
        return money;
    }
}
