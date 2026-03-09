package com.gabrielspassos.patterns.behavioral.visitor;

import java.math.BigDecimal;

public interface ShoppingCartVisitor {

    BigDecimal visit(Book book);
    BigDecimal visit(Fruit fruit);

}
