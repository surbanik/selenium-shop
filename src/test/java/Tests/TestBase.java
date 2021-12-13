package Tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.io.FileHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class TestBase {
    static private Logger logger = LoggerFactory.getLogger(TestBase.class);
    protected WebDriver driver;

    public void screenShotMaker() {
        Date nowDate = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("MM.dd.HH-mm-ss");
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        try {
            FileHandler.copy(source, new File("src/test/java/screenshots/" + sdf.format(nowDate) + ".png"));
            System.out.println("Screenshot taken");
        } catch (Exception e) {
            System.out.println("Exception while taking screenshot " + e.getMessage());
        }
    }

    @BeforeAll
    static void setDriver() {
        WebDriverManager.chromedriver().setup();
        logger.info("Webdriver initialized");
    }

    @BeforeEach
    void setUp() {
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("download.default_directory", Helpers.DOWNLOAD_DIR);
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", prefs);

        driver = new ChromeDriver(options);
        logger.info("Webdriver window start");
        driver.manage().window().maximize();
        logger.info("Webdriver window maximized");

        driver.get("http://146.59.32.4/");
    }

    @AfterEach
    void tearDown() {
        driver.quit();
        logger.info("Webdriver window closed");
    }
}
