package pages;

import basePage.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MainPage extends BasePage {

    public MainPage(WebDriver driver) {
        super(driver);
    }


    public WebElement getProducts() {
        return products;
    }

    @FindBy(css = "[class='products row']")
    WebElement products;

    @FindBy(className = "footer-container")
    WebElement footer;


    public String getRandomProductName() {
        return new ProductListPage(products).getRandomProductTitle();
    }

    public OnSalePage clickOnPricesDrop(){
        performWaitAndClick(new FooterPage(footer).pricesDrop);
        return new OnSalePage(driver);
    }



}
