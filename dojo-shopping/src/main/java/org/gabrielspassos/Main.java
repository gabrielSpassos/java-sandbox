package org.gabrielspassos;

import org.gabrielspassos.shopping.Product;
import org.gabrielspassos.shopping.ShoppingCart;

import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.addProduct(new Product("tshirt", new BigDecimal("12.50"), 1));
        shoppingCart.addProduct(new Product("pants", new BigDecimal("20.90"), 1));
        shoppingCart.addProduct(new Product("shoes", new BigDecimal("18.70"), 1));

        BigDecimal checkout1 = shoppingCart
                .checkout("Regular", "BNPL", BigDecimal.valueOf(0), "Percentage", 1L);
        System.out.println(checkout1);

        BigDecimal checkout2 = shoppingCart
                .checkout("Gold", "BNPL", BigDecimal.valueOf(0), "Percentage", 1L);
        System.out.println(checkout2);

        BigDecimal checkout3 = shoppingCart
                .checkout("Regular", "BNPL", BigDecimal.valueOf(0), "Percentage", 2L);
        System.out.println(checkout3);
    }
}