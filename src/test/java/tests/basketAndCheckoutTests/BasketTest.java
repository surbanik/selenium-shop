package tests.basketAndCheckoutTests;

import models.Basket;
import org.junit.jupiter.api.RepeatedTest;
import pages.CartPage;
import pages.CategoryPage;
import pages.HeaderPage;
import pages.ProductDetailPage;
import testBase.TestBase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BasketTest extends TestBase {

    @RepeatedTest(1)
    public void basketTest() {
        HeaderPage headerPage = new HeaderPage(driver);
        ProductDetailPage productDetailPage = new ProductDetailPage(driver);
        CategoryPage categoryPage = new CategoryPage(driver);
        CartPage cartPage = new CartPage(driver);
        Basket basket = new Basket();
        final int NUMBER_OF_PRODUCTS = 2;

        for (int i = 0; i < NUMBER_OF_PRODUCTS; i++) {
            headerPage
                    .goToRandomSubcategory();

            categoryPage
                    .clickOnRandomProduct();

            productDetailPage
                    .addDisplayedProductToCart(basket)
                    .continueShopping();
        }
        headerPage
                .goToCart();

        assertEquals(basket.getProductsNameInBasket(), cartPage.getProductsNameInCart());
        assertEquals(basket.getProductsQuantitiesInBasket(), cartPage.getProductsQuantityInCart());
        assertEquals(basket.getProductsPriceInBasket(), cartPage.getProductsPriceInCart());
        assertEquals(basket.getBasketTotal(), cartPage.getTotalPrice());

        cartPage
                .setFirsProductQuantityTo5();

        assertTrue(cartPage.isTotalOrderValueIsCorrect());

        assertTrue(cartPage.isIncreaseingFirstProductQuantityChangePrice());
        assertTrue(cartPage.isTotalOrderValueIsCorrect());

        assertTrue(cartPage.isDecreaseingFirstProductQuantityChangePrice());
        assertTrue(cartPage.isTotalOrderValueIsCorrect());

        for (int i = 0; i < basket.getProductLinesAmount(); i++) {
            cartPage
                    .deleteTopItemFromBasket();

            assertTrue(cartPage.isTotalOrderValueIsCorrect());
        }
    }
}