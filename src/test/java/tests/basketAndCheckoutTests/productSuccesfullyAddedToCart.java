package tests.basketAndCheckoutTests;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import pages.CategoryPage;
import pages.HeaderPage;
import pages.MainPage;
import pages.ProductDetailPage;
import testBase.TestBase;

@Execution(ExecutionMode.CONCURRENT)
public class productSuccesfullyAddedToCart extends TestBase {


    @RepeatedTest(1)
    public void productAddedToCart() {
        HeaderPage headerPage = new HeaderPage(driver);
        MainPage mainPage = new MainPage(driver);
        ProductDetailPage productDetailPage = new ProductDetailPage(driver);
        CategoryPage categoryPage = new CategoryPage(driver);

        for(int i=0; i<3;i++){
        headerPage
                .openRandomSubcategory();

        categoryPage
                .clickOnRandomProduct();

        productDetailPage
                .setRandomQuantity()
                .addProductToCart();

        }



    }
}
