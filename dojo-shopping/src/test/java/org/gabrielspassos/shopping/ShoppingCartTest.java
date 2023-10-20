package org.gabrielspassos.shopping;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ShoppingCartTest {

    private final String REGULAR_CUSTOMER_TYPE = "Regular";
    private final String GOLD_CUSTOMER_TYPE = "Gold";
    private final String BANK_TRANSFER_CHECK_OUT = "Bank Transfer";
    private final String CREDIT_CARD_CHECK_OUT = "Credit Card";
    private final String BUY_NOW_PAY_LATER_CHECK_OUT = "BNPL";
    private final String FIXED_DISCOUNT_TYPE = "Fixed";
    private final String PERCENTAGE_DISCOUNT_TYPE = "Percentage";

    @Test
    void shouldPayWithBankTransfer() {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.addProduct(new Product("tshirt", new BigDecimal("20.00"), 1));
        shoppingCart.addProduct(new Product("pants", new BigDecimal("30.00"), 1));

        BigDecimal finalAmount = shoppingCart.checkout(
                REGULAR_CUSTOMER_TYPE, BANK_TRANSFER_CHECK_OUT, BigDecimal.valueOf(0), FIXED_DISCOUNT_TYPE, 1L);

        assertEquals(new BigDecimal("47.50"), finalAmount);
    }

    @Test
    void shouldPayWithBankTransferWithFixedDiscount() {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.addProduct(new Product("tshirt", new BigDecimal("20.00"), 1));
        shoppingCart.addProduct(new Product("pants", new BigDecimal("30.00"), 1));

        BigDecimal finalAmount = shoppingCart.checkout(
                REGULAR_CUSTOMER_TYPE, BANK_TRANSFER_CHECK_OUT, BigDecimal.valueOf(10), FIXED_DISCOUNT_TYPE, 1L);

        assertEquals(new BigDecimal("38.00"), finalAmount);
    }

    @Test
    void shouldPayWithBankTransferWithPercentageDiscount() {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.addProduct(new Product("tshirt", new BigDecimal("20.00"), 1));
        shoppingCart.addProduct(new Product("pants", new BigDecimal("30.00"), 1));

        BigDecimal finalAmount = shoppingCart.checkout(
                REGULAR_CUSTOMER_TYPE, BANK_TRANSFER_CHECK_OUT, BigDecimal.valueOf(10), PERCENTAGE_DISCOUNT_TYPE, 1L);

        assertEquals(new BigDecimal("42.75"), finalAmount);
    }

    @Test
    void shouldPayWithCreditCard() {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.addProduct(new Product("tshirt", new BigDecimal("20.00"), 1));
        shoppingCart.addProduct(new Product("pants", new BigDecimal("30.00"), 1));

        BigDecimal finalAmount = shoppingCart.checkout(
                REGULAR_CUSTOMER_TYPE, CREDIT_CARD_CHECK_OUT, BigDecimal.valueOf(0), FIXED_DISCOUNT_TYPE, 1L);

        assertEquals(new BigDecimal("50.00"), finalAmount);
    }

    @Test
    void shouldPayWithCreditCardWithFixedDiscount() {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.addProduct(new Product("tshirt", new BigDecimal("20.00"), 1));
        shoppingCart.addProduct(new Product("pants", new BigDecimal("30.00"), 1));

        BigDecimal finalAmount = shoppingCart.checkout(
                REGULAR_CUSTOMER_TYPE, CREDIT_CARD_CHECK_OUT, BigDecimal.valueOf(10), FIXED_DISCOUNT_TYPE, 1L);

        assertEquals(new BigDecimal("40.00"), finalAmount);
    }

    @Test
    void shouldPayWithCreditCardWithPercentageDiscount() {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.addProduct(new Product("tshirt", new BigDecimal("20.00"), 1));
        shoppingCart.addProduct(new Product("pants", new BigDecimal("30.00"), 1));

        BigDecimal finalAmount = shoppingCart.checkout(
                REGULAR_CUSTOMER_TYPE, CREDIT_CARD_CHECK_OUT, BigDecimal.valueOf(10), PERCENTAGE_DISCOUNT_TYPE, 1L);

        assertEquals(new BigDecimal("45.00"), finalAmount);
    }

    @Test
    void shouldPayWithBuyNowPayLater() {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.addProduct(new Product("tshirt", new BigDecimal("20.00"), 1));
        shoppingCart.addProduct(new Product("pants", new BigDecimal("30.00"), 1));

        BigDecimal finalAmount = shoppingCart.checkout(
                REGULAR_CUSTOMER_TYPE, BUY_NOW_PAY_LATER_CHECK_OUT, BigDecimal.valueOf(0), FIXED_DISCOUNT_TYPE, 1L);

        assertEquals(new BigDecimal("51.50"), finalAmount);
    }

    @Test
    void shouldPayWithBuyNowPayLaterWithInstallments() {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.addProduct(new Product("tshirt", new BigDecimal("20.00"), 1));
        shoppingCart.addProduct(new Product("pants", new BigDecimal("30.00"), 1));

        BigDecimal finalAmount = shoppingCart.checkout(
                REGULAR_CUSTOMER_TYPE, BUY_NOW_PAY_LATER_CHECK_OUT, BigDecimal.valueOf(0), FIXED_DISCOUNT_TYPE, 2L);

        assertEquals(new BigDecimal("25.75"), finalAmount);
    }

    @Test
    void shouldPayWithBuyNowPayLaterWithFixedDiscount() {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.addProduct(new Product("tshirt", new BigDecimal("20.00"), 1));
        shoppingCart.addProduct(new Product("pants", new BigDecimal("30.00"), 1));

        BigDecimal finalAmount = shoppingCart.checkout(
                REGULAR_CUSTOMER_TYPE, BUY_NOW_PAY_LATER_CHECK_OUT, BigDecimal.valueOf(10), FIXED_DISCOUNT_TYPE, 1L);

        assertEquals(new BigDecimal("41.20"), finalAmount);
    }

    @Test
    void shouldPayWithBuyNowPayLaterWithPercentageDiscount() {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.addProduct(new Product("tshirt", new BigDecimal("20.00"), 1));
        shoppingCart.addProduct(new Product("pants", new BigDecimal("30.00"), 1));

        BigDecimal finalAmount = shoppingCart.checkout(
                REGULAR_CUSTOMER_TYPE, BUY_NOW_PAY_LATER_CHECK_OUT, BigDecimal.valueOf(10), PERCENTAGE_DISCOUNT_TYPE, 1L);

        assertEquals(new BigDecimal("46.35"), finalAmount);
    }

    @Test
    void shouldPayWithBuyNowPayLaterWithGoldCustomer() {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.addProduct(new Product("tshirt", new BigDecimal("20.00"), 1));
        shoppingCart.addProduct(new Product("pants", new BigDecimal("30.00"), 1));

        BigDecimal finalAmount = shoppingCart.checkout(
                GOLD_CUSTOMER_TYPE, BUY_NOW_PAY_LATER_CHECK_OUT, BigDecimal.valueOf(0), FIXED_DISCOUNT_TYPE, 1L);

        assertEquals(new BigDecimal("50.00"), finalAmount);
    }

    @Test
    void shouldRemoveProductFromCart() {
        Product tshirt = new Product("tshirt", new BigDecimal("20.00"), 1);
        Product pants = new Product("pants", new BigDecimal("30.00"), 1);

        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.addProduct(tshirt);
        shoppingCart.addProduct(pants);
        shoppingCart.removeProduct(pants);

        BigDecimal finalAmount = shoppingCart.checkout(
                REGULAR_CUSTOMER_TYPE, CREDIT_CARD_CHECK_OUT, BigDecimal.valueOf(0), FIXED_DISCOUNT_TYPE, 1L);

        assertEquals(new BigDecimal("20.00"), finalAmount);
    }

    @Test
    void shouldThrowErrorToRemoveProductFromCart() {
        Product pants = new Product("pants", new BigDecimal("30.00"), 1);
        ShoppingCart shoppingCart = new ShoppingCart();

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> shoppingCart.removeProduct(pants));

        assertEquals("Product is not on cart.", exception.getMessage());
    }

    @Test
    void shouldThrowErrorForInvalidCheckout() {
        ShoppingCart shoppingCart = new ShoppingCart();

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> shoppingCart.checkout(GOLD_CUSTOMER_TYPE, "TEST CHECKOUT", BigDecimal.valueOf(0), FIXED_DISCOUNT_TYPE, 1L));

        assertEquals("Invalid checkout TEST CHECKOUT", exception.getMessage());
    }

}