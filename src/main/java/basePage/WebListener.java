package basePage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WebListener extends AbstractWebDriverEventListener {
    Logger logger = LoggerFactory.getLogger(WebListener.class);

    @Override
    public void afterClickOn(WebElement element, WebDriver driver) {
        WebElement alertElement = driver.findElement(By.cssSelector(".alert-danger"));
        WebElement alertElement2 = driver.findElement(By.cssSelector(".product-unavailable"));
        if (alertElement.isDisplayed() || alertElement2.isDisplayed()){
            logger.warn("brak produktów w sklepie. test przerwany");
            driver.quit();
        }
    }

    @Override
    public void beforeClickOn(WebElement element, WebDriver driver) {
    }

    @Override
    public void beforeNavigateTo(String url, WebDriver driver) {
    }

    @Override
    public void afterNavigateTo(String url, WebDriver driver) {
    }


}
