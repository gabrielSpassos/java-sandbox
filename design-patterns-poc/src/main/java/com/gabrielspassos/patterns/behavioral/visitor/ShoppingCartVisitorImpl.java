package com.gabrielspassos.patterns.behavioral.visitor;

import java.math.BigDecimal;

public class ShoppingCartVisitorImpl implements ShoppingCartVisitor {

    private static final BigDecimal ONE_HUNDRED = new BigDecimal(100);
    private static final BigDecimal BOOK_DISCOUNT_PERCENT = new BigDecimal(2);

    @Override
    public BigDecimal visit(Book book) {
        // apply discount if book price is greater than 50
        if (new BigDecimal(50).compareTo(book.getPrice()) < 0) {
            var twoPercentBookCost = BOOK_DISCOUNT_PERCENT.multiply(book.getPrice()).divide(ONE_HUNDRED);
            BigDecimal cost = book.getPrice().subtract(twoPercentBookCost);
            IO.println("Book isbn " + book.getIsbnNumber() + " final cost after discount of $" + twoPercentBookCost + " final value: $" + cost);
            return cost;
        }

        IO.println("Book isbn " + book.getIsbnNumber() + " final cost: $" + book.getPrice());
        return book.getPrice();
    }

    @Override
    public BigDecimal visit(Fruit fruit) {
        BigDecimal cost = fruit.getPriceByKg().multiply(fruit.getWeight());
        IO.println("Fruit " + fruit.getName() + "final cost: $" + cost);
        return cost;
    }

}
