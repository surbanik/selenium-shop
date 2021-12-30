package tests.searchTests;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import pages.HeaderPage;
import pages.MainPage;
import pages.ProductListPage;
import testBase.TestBase;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Execution(ExecutionMode.CONCURRENT)
public class SearchTest extends TestBase {

    @RepeatedTest(10)
    public void searchTest(){
        MainPage mainPage = new MainPage(driver);
        HeaderPage headerPage = new HeaderPage(driver);
        ProductListPage productListPage = new ProductListPage(mainPage.getProducts());
        String randomProductName = mainPage.getRandomProductName();

        headerPage
                .searchProduct(randomProductName);

        assertTrue(productListPage.isProductOnList(randomProductName));

    }
}
