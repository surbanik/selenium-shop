package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.FluentWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.Random;

public class PageBase {
    static private Logger logger = LoggerFactory.getLogger(PageBase.class);

    public PageBase(WebDriver driver){
        this.driver = driver;
        wait = new FluentWait(driver)
                .withTimeout(Duration.ofMillis(10000))
                .pollingEvery(Duration.ofMillis(10));
        action = new Actions(driver);
        random = new Random();
    }

    public WebDriver driver;
    public FluentWait wait;
    public Actions action;
    public Random random;
}
