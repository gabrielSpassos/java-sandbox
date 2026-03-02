package com.gabrielspassos.patterns.behavioral.chainOfResponsibility;

public class Dollar50Dispenser implements DispenseChain {

    private DispenseChain chain;

    @Override
    public boolean setNextChain(DispenseChain nextChain) {
        this.chain = nextChain;
        return true;
    }

    @Override
    public Money dispense(Money money) {
        if (money.getAmount() < 50) {
            return this.chain.dispense(money);
        }

        var num = money.getAmount() / 50;
        var remainder = money.getAmount() % 50;
        IO.println("Dispensing "+num+" 50$ note");
        if(remainder != 0) {
            this.chain.dispense(new Money(remainder));
        }
        return money;
    }
}
