package tests.basketAndCheckoutTests;

import models.Basket;
import models.Product;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import pages.CategoryPage;
import pages.HeaderPage;
import pages.ProductDetailPage;
import testBase.TestBase;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Execution(ExecutionMode.CONCURRENT)
public class ProductSuccesfullyAddedToCartTest extends TestBase {


    @RepeatedTest(1)
    public void productAddedToCart() {
        HeaderPage headerPage = new HeaderPage(driver);
        ProductDetailPage productDetailPage = new ProductDetailPage(driver);
        CategoryPage categoryPage = new CategoryPage(driver);
        Basket basket = new Basket();
        Product product;
        final int NUMBER_OF_PRODUCTS = 3;

        for (int i = 0; i < NUMBER_OF_PRODUCTS; i++) {
            headerPage
                    .goToRandomSubcategory();

            categoryPage
                    .clickOnRandomProduct();

            productDetailPage
                    .setRandomQuantity(1,6);

            product = productDetailPage.getDisplayedProduct();

            productDetailPage
                    .addDisplayedProductToCart(basket);

            assertEquals(productDetailPage.getConfirmationProductName(), product.getName());
            assertEquals(productDetailPage.getConfirmationPrice(), product.getPrice());
            assertEquals(productDetailPage.getConfirmationQuantity(), basket.getProductQuantityByName(product.getName()));
            assertEquals(productDetailPage.getConfirmationNumberOfItemsInCart(), basket.getBasketSize());
            assertEquals(productDetailPage.getConfirmationTotal(), basket.getBasketTotal());

            productDetailPage.continueShopping();
        }

        assertEquals(headerPage.getCartCount(), basket.getBasketSize());

    }
}
