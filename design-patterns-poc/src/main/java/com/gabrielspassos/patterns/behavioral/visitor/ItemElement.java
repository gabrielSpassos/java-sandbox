package com.gabrielspassos.patterns.behavioral.visitor;

import java.math.BigDecimal;

public interface ItemElement {

    BigDecimal accept(ShoppingCartVisitor visitor);
}
