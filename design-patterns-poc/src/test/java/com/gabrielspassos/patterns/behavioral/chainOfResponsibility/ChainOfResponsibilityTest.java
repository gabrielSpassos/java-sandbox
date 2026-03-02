package com.gabrielspassos.patterns.behavioral.chainOfResponsibility;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ChainOfResponsibilityTest {

    @Test
    void shouldDispense530() {
        DispenseChain chain = createChain();

        var money = new Money(530);

        var dispense = chain.dispense(money);

        assertNotNull(dispense);
        assertEquals(530, dispense.getAmount());
    }

    @Test
    void shouldDispense100() {
        DispenseChain chain = createChain();

        var money = new Money(100);

        var dispense = chain.dispense(money);

        assertNotNull(dispense);
        assertEquals(100, dispense.getAmount());
    }

    @Test
    void shouldDispense40() {
        DispenseChain chain = createChain();

        var money = new Money(40);

        var dispense = chain.dispense(money);

        assertNotNull(dispense);
        assertEquals(40, dispense.getAmount());
    }

    @Test
    void shouldDispense10() {
        DispenseChain chain = createChain();

        var money = new Money(10);

        var dispense = chain.dispense(money);

        assertNotNull(dispense);
        assertEquals(10, dispense.getAmount());
    }

    private DispenseChain createChain() {
        DispenseChain chain50 = new Dollar50Dispenser();
        DispenseChain chain20 = new Dollar20Dispenser();
        DispenseChain chain10 = new Dollar10Dispenser();

        chain20.setNextChain(chain10);
        chain50.setNextChain(chain20);

        return chain50;
    }

}