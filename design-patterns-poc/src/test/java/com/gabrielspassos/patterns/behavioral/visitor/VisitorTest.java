package com.gabrielspassos.patterns.behavioral.visitor;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class VisitorTest {

    @Test
    void shouldHandleBookUnder50Price() {
        Book book = new Book("1234", new BigDecimal(45));
        ShoppingCartVisitor shoppingCart = new ShoppingCartVisitorImpl();

        BigDecimal result = book.accept(shoppingCart);

        assertEquals(new BigDecimal(45), result);
    }

    @Test
    void shouldHandleBookOver50Price() {
        Book book = new Book("6789", new BigDecimal(55));
        ShoppingCartVisitor shoppingCart = new ShoppingCartVisitorImpl();

        BigDecimal result = book.accept(shoppingCart);

        assertEquals(new BigDecimal("53.9"), result);
    }

    @Test
    void shouldHandleFruitPrice() {
        Fruit fruit = new Fruit("banana", new BigDecimal("2.3"), new BigDecimal("3.5"));
        ShoppingCartVisitor shoppingCart = new ShoppingCartVisitorImpl();

        BigDecimal result = fruit.accept(shoppingCart);

        assertEquals(new BigDecimal("8.05"), result);
    }

}