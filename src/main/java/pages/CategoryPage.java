package pages;

import basePage.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class CategoryPage extends BasePage {

    public CategoryPage(WebDriver driver) {
        super(driver);
    }


    @FindBy(css = "[class='products row']")
    WebElement products;

    @FindBy(id = "search_filters")
    WebElement filter;

    @FindBy(xpath = "//div[@id='js-product-list-top']/div/p")
    WebElement numberOfProductsMessage;

    @FindBy(css = "div#js-product-list-header>div>h1")
    WebElement categoryHeader;

    @FindBy(css = ".filter-block")
    List<WebElement> activeFiltersList;

    @FindBy(css = ".facet-label>a")
    List<WebElement> filterOptionList;

    public boolean isChosenCategoryIsOnHeader(String chosenCategory) {
        return categoryHeader.getText().equals(chosenCategory);
    }

    public boolean isFilterIsDisplayed() {
        return filter.isDisplayed();
    }

    public boolean isNumberOfProductMessageContainActualValue() {
        return numberOfProductsMessage.getText().contains(Integer.toString(new ProductListPage(products).howManyProductsOnList()));
    }

    public ProductDetailPage clickOnRandomProduct() {
        ProductListPage productListPage = new ProductListPage(products);
        performWaitAndClick(productListPage.getRandomProduct());
        return new ProductDetailPage(driver);
    }


    public boolean isUsedFilterOptionIsOnActiveFiltersList() {
        for (int i = 0; i < filterOptionList.size(); i++) {
            String optionName = filterOptionList.get(i).getText().substring(0, 7);
            performClick(filterOptionList.get(i));

            wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.cssSelector(".filter-block"), i));
            if (!activeFiltersList.get(i).getText().contains(optionName)) {
                return false;
            }
        }
        return true;
    }


}
