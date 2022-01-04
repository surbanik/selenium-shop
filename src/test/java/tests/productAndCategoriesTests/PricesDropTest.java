package tests.productAndCategoriesTests;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import pages.MainPage;
import pages.OnSalePage;
import pages.ProductDetailPage;
import testBase.TestBase;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Execution(ExecutionMode.CONCURRENT)
public class PricesDropTest extends TestBase {

    @RepeatedTest(1)
    public void pricesDropTest() {
        OnSalePage onSalePage = new OnSalePage(driver);
        MainPage mainPage = new MainPage(driver);
        ProductDetailPage productDetailPage = new ProductDetailPage(driver);

        mainPage
                .clickOnPricesDrop();

        assertTrue(onSalePage.isProductListDisplayed());
        assertTrue(onSalePage.isDiscountLabelDisplayedOnEachProduct());
        assertTrue(onSalePage.isRegularPriceDisplayedOnEachProduct());
        assertTrue(onSalePage.isDiscountedPriceDisplayedOnEachProduct());
        assertTrue(onSalePage.isDiscountPriceIs20PercentLowerThenRegular());

        onSalePage
                .clickOnRandomProduct();

        assertTrue(productDetailPage.isDiscountLabelIsDispalyed());
        assertTrue(productDetailPage.isDiscountedPriceIsDispalyed());
        assertTrue(productDetailPage.isRegularPriceIsDispalyed());
        assertTrue(productDetailPage.isDiscountPriceIs20PercentLowerThenRegular());

    }
}
