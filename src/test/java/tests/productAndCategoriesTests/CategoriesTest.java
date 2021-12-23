package tests.productAndCategoriesTests;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import pages.CategoryPage;
import pages.HeaderPage;
import testBase.TestBase;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Execution(ExecutionMode.CONCURRENT)
public class CategoriesTest extends TestBase {

    @RepeatedTest(1)
    public void categoriesAndSubcategoriesTest() {
        HeaderPage headerPage = new HeaderPage(driver);
        CategoryPage categoryPage = new CategoryPage(driver);

        for (int i = 0; i < headerPage.getCategoryList().size(); i++) {
            for(int j=0; j < headerPage.getAmountOfSubcategorysAccordingToCategoryIndex(i);j++){
                headerPage.hoverOnElementInCategoryList(i);
                String subcategoryName = headerPage.getSubcategoryNameAccordingToCategoryAndSubcategoryIndex(i,j);
                headerPage.clickOnSubcategoryAccordingToCategoryAndSubcategoryIndex(i,j);

                assertTrue(categoryPage.isChosenCategoryIsOnHeader(subcategoryName));
                assertTrue(categoryPage.isFilterIsDisplayed());
                assertTrue(categoryPage.isNumberOfProductMessageContainActualValue());
            }
        }
    }
}
