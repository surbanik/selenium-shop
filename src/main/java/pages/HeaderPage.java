package pages;

import basePage.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class HeaderPage extends BasePage {
    static private Logger logger = LoggerFactory.getLogger(HeaderPage.class);


    public HeaderPage(WebDriver driver) {super(driver);}

    @FindBy(css = "a>span.hidden-sm-down")
    WebElement userLogin;

    @FindBy(className = "user-info")
    WebElement signInButton;

    @FindBy(css = "[class='logout hidden-sm-down']")
    WebElement logOutButton;

    @FindBy(name ="s")
    WebElement searchInput;

    @FindBy(css = "div#search_widget>*>button")
    WebElement searchIcon;

    @FindBy(className = "ui-corner-all")
    List<WebElement> searchInputHint;

    @FindBy(css = "#top-menu>li")
    List<WebElement> categoryList;

    public List<WebElement> getCategoryList() {
        return categoryList;
    }

    public void hoverOnElementInCategoryList(int index){
        mouseHover(categoryList.get(index));
    }

    public void goToLoginPage() {
        performClick(signInButton);
    }

    public String getUserLogin() {
        return userLogin.getText();
    }

    public void logOut() {
        performClick(logOutButton);
    }

    public boolean isUserLoggedOut() {
        List<WebElement> elementsList = driver.findElements(By.cssSelector("[class='logout hidden-sm-down']"));
        return elementsList.isEmpty();
    }

    public ProductListPage searchProduct(String name){
        enterTextToSearchInput(name);
        performClick(searchIcon);
        return new ProductListPage(new MainPage(driver).getProducts());
    }

    public HeaderPage enterTextToSearchInput(String name){
        waitUntilElementToBeVisible(searchInput);
        searchInput.clear();
        typeTextTo(searchInput, name);
        return this;
    }

    public boolean isProductOnHintList(String productName){

        for (WebElement hint:searchInputHint){
            waitUntilElementToBeVisible(hint);
            if (hint.getText().contains(productName)) {
                return true;
            }
        }
        return false;
    }

    public int getAmountOfSubcategorysAccordingToCategoryIndex(int index){
        return new SubcategoryMenuPage(getCategoryList().get(index)).getSubcategoryList().size();
    }

    public String getSubcategoryNameAccordingToCategoryAndSubcategoryIndex(int categoryIndex, int subcategoryIndex){
        return getSubcategoryListAccordingToCategoryIndex(categoryIndex).get(subcategoryIndex).getText();
    }

    public WebElement getSubcategoryAccordingToCategoryAndSubcategoryIndex(int categoryIndex, int subcategoryIndex){
        return getSubcategoryListAccordingToCategoryIndex(categoryIndex).get(subcategoryIndex);
    }

    public  List<WebElement> getSubcategoryListAccordingToCategoryIndex(int categoryIndex){
        return new SubcategoryMenuPage(getCategoryList().get(categoryIndex)).getSubcategoryList();
    }

    public void clickOnSubcategoryAccordingToCategoryAndSubcategoryIndex(int categoryIndex, int subcategoryIndex){
        performWaitAndClick(getSubcategoryAccordingToCategoryAndSubcategoryIndex(categoryIndex,subcategoryIndex));
    }


}
