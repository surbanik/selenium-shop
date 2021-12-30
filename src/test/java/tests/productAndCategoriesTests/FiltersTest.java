package tests.productAndCategoriesTests;

import org.junit.jupiter.api.RepeatedTest;
import pages.CategoryPage;
import pages.HeaderPage;
import testBase.TestBase;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class FiltersTest extends TestBase {

    @RepeatedTest(10)
    public void Filters() {
        HeaderPage headerPage = new HeaderPage(driver);
        CategoryPage categoryPage = new CategoryPage(driver);

        headerPage
                .goToArtCategory();

        assertTrue(categoryPage.isUsedFilterOptionIsOnActiveFiltersList());

    }
}
