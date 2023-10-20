package org.gabrielspassos.shopping;

import org.gabrielspassos.checkout.BankTransfer;
import org.gabrielspassos.checkout.BuyNowPayLater;
import org.gabrielspassos.checkout.CheckOut;
import org.gabrielspassos.checkout.CreditCard;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {

    private List<Product> products;

    public ShoppingCart() {
        this.products = new ArrayList<>();
    }

    public Product addProduct(Product product) {
        products.add(product);
        return product;
    }

    public Product removeProduct(Product productToRemove) {
        int indexOfProduct = products.indexOf(productToRemove);
        if (0 > indexOfProduct) {
            throw new IllegalArgumentException("Product is not on cart.");
        }

        Product product = products.get(indexOfProduct);
        products.remove(indexOfProduct);
        return product;
    }

    public BigDecimal checkout(String customerType, String checkOutType, BigDecimal cupomDiscount, String discountType, Long installments) {
        CheckOut checkOut = getCheckOutByType(checkOutType, cupomDiscount);
        BigDecimal productsTotalPrice = getProductsTotalPrice();
        BigDecimal finalDiscount = "Percentage".equals(discountType)
                ? checkOut.calculatePercentageCupomDiscount(productsTotalPrice)
                : cupomDiscount;

        installments = checkOut instanceof BuyNowPayLater ? installments : 1;
        return checkOut.executePayment(productsTotalPrice, finalDiscount, installments, customerType);
    }

    private CheckOut getCheckOutByType(String checkOutType, BigDecimal cupomDiscount) {
        if ("Credit Card".equals(checkOutType)) {
            return new CreditCard(cupomDiscount);
        }

        if ("BNPL".equals(checkOutType)) {
            return new BuyNowPayLater(cupomDiscount);
        }

        if ("Bank Transfer".equals(checkOutType)) {
            return new BankTransfer(cupomDiscount);
        }

        throw new IllegalArgumentException("Invalid checkout " + checkOutType);
    }

    private BigDecimal getProductsTotalPrice() {
        return products.stream()
                .map(Product::getProductTotalPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
