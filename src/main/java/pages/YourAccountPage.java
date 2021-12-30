package pages;

import basePage.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class YourAccountPage extends BasePage {
    public YourAccountPage(WebDriver driver) {
        super(driver);
    }


@FindBy(css = "#history-link")
WebElement orderHistoryAndDetailsButton;

    public OrderHistoryPage goToOrderHistoryPage(){
        performWaitAndClick(orderHistoryAndDetailsButton);
        return new OrderHistoryPage(driver);
    }


}
