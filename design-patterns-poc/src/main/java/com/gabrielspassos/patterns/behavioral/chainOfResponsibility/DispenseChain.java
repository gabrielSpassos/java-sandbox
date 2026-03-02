package com.gabrielspassos.patterns.behavioral.chainOfResponsibility;

public interface DispenseChain {

    boolean setNextChain(DispenseChain nextChain);

    Money dispense(Money money);

}
