package driverFactory;

import basePage.WebListener;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

public class DriverFactory {
    private String browserName = System.getProperty("browser"); //"chrome";  //chrome, firefox, default-IE
    private WebDriver webDriver;
    private EventFiringWebDriver driver;
    private WebListener webListener;


    public WebDriver getDriver(Browser browser) {
        //listener
        switch (browser) {
            case CHROME:
                webDriver = getChromeDriver();
                break;
            case FIREFOX:
                webDriver = getFirefoxDriver();
                break;
            case IE:
                webDriver = getIEDriver();
                break;
            default:
                webDriver = getEdgeDriver();


        }
        return this.webDriver;
    }

    private WebDriver getEdgeDriver() {
        WebDriverManager.edgedriver().setup();
        webDriver = new EdgeDriver();
        webDriver.get(System.getProperty("webURL"));
        return webDriver;
    }

    private WebDriver getIEDriver() {
        WebDriverManager.iedriver().setup();
        webDriver = new InternetExplorerDriver();
        webDriver.get(System.getProperty("webURL"));
        return webDriver;
    }

    WebDriver getChromeDriver(){
        ChromeOptions optionsChrome = new ChromeOptions();
        WebDriverManager.chromedriver().setup();
        optionsChrome.addArguments("start-maximized");
        webDriver = new ChromeDriver(optionsChrome);          //listener
        driver = new EventFiringWebDriver(webDriver);
        webListener = new WebListener();
        driver.register(webListener);
        driver.get(System.getProperty("webURL"));
        return driver;
    }

    WebDriver getFirefoxDriver(){
        FirefoxOptions optionsFirefox = new FirefoxOptions();
        WebDriverManager.firefoxdriver().setup();
        optionsFirefox.addArguments("start-maximized");
        webDriver = new FirefoxDriver();          //listener
        webDriver.get(System.getProperty("webURL"));
        return webDriver;
    }
}
