package pages;

import basePage.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

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

    public boolean isChosenCategoryIsOnHeader(String chosenCategory){
        return categoryHeader.getText().equals(chosenCategory);
    }

    public boolean isFilterIsDisplayed(){
        return filter.isDisplayed();
    }

    public boolean isNumberOfProductMessageContainActualValue(){
        return numberOfProductsMessage.getText().contains(Integer.toString(new ProductListPage(products).howManyProductsOnList()));
    }

}
