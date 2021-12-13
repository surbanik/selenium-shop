package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class MainPage extends PageBase {
    static private Logger logger = LoggerFactory.getLogger(MainPage.class);

    public MainPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "a.account>span.hidden-sm-down")
    WebElement userLogin;


    @FindBy(className = "user-info")
    WebElement signInButton;

    @FindBy(css = "[class='logout hidden-sm-down']")
    WebElement logOutButton;


    public void goToLoginPage() {
        signInButton.click();
        logger.info("Użytkownik przeszedł do strony logowania");
    }

    public String getUserLogin() {
        return userLogin.getText();
    }

    public void logOut() {
        logOutButton.click();
        logger.info("Użytkownik wylogował się");
    }

    public boolean checkIsUserLogedOut() {
        List<WebElement> elementsList = driver.findElements(By.cssSelector("[class='logout hidden-sm-down']"));
        return elementsList.isEmpty();
    }
}
