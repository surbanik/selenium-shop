package basePage;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.Coordinates;
import org.openqa.selenium.interactions.Locatable;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.internal.EventFiringMouse;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Duration;
import java.util.Random;

public class BasePage {
    static private Logger logger = LoggerFactory.getLogger(BasePage.class);
    public WebDriver driver;
    public WebDriverWait wait;
    public Actions action;
    public Random random;
    private EventFiringMouse eventFiringMouse;
    private WebListener webListener;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        action = new Actions(driver);
        random = new Random();
        PageFactory.initElements(driver, this);
    }

    private void jsScrollIntoViewElement(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    String getElementSelector(WebElement element) {
        return element.toString().split("->")[1];
    }

    public void performClick(WebElement element) {
        try {
            logger.info("Trying to click on element: {}", getElementSelector(element));
            element.click();
        } catch (TimeoutException e) {
            e.printStackTrace();
            logger.error("Element not found");
        }
    }

    public void performWaitAndClick(WebElement element) {
        try {
            waitUntilElementToBeClickable(element);
            logger.info("Trying to click on element: {} with text: {}", getElementSelector(element), element.getText());
            element.click();
        } catch (TimeoutException e) {
            e.printStackTrace();
            logger.error("Element not found");
        }
    }


    public void typeTextTo(WebElement element, String message) {
        wait.until(ExpectedConditions.visibilityOf(element));
        element.clear();
        element.sendKeys(Keys.chord(Keys.CONTROL, "a"), message);
        logger.info("Text: {} has been typed to element: {}", message, getElementSelector(element));
    }

    public void typeEnterOnElement(WebElement element){
        element.sendKeys(Keys.ENTER);
        logger.info("ENTER has been used on element: {}", getElementSelector(element));
    }

    public void waitUntilElementToBeVisible(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
        logger.info("Waiting to element: {} be visible", getElementSelector(element));
    }


    public void waitUntilElementToBeClickable(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
        logger.info("Waiting to element: {} be clickable", getElementSelector(element));
    }


    public void mouseHover(WebElement element) {
        eventFiringMouse = new EventFiringMouse(driver, webListener);
        Locatable item = (Locatable) element;
        Coordinates coordinates = item.getCoordinates();
        eventFiringMouse.mouseMove(coordinates);
    }

    public static BigDecimal round(BigDecimal bigDecimal) {
        bigDecimal = bigDecimal.setScale(2, RoundingMode.HALF_UP);
        return bigDecimal;
    }

    public BigDecimal getBigDecimalFromElementPrice(WebElement element) {
        return BigDecimal.valueOf(Double.valueOf(element.getText().substring(1))).setScale(2, RoundingMode.HALF_UP);
    }
}
