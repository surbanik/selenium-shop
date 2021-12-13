package Pages;

import org.apache.commons.lang.RandomStringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RegistrationPage extends PageBase {
    static private Logger logger = LoggerFactory.getLogger(RegistrationPage.class);

    public RegistrationPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "input[name='firstname']")
    WebElement firstNameInput;

    @FindBy(css = "input[name='lastname']")
    WebElement lastNameInput;

    @FindBy(css = "input.form-control[name='email']")
    WebElement emailInput;

    @FindBy(css = "[name='password']")
    WebElement passwordInput;

    @FindBy(css = "[name='customer_privacy']")
    WebElement customerPrivacyCheckbox;

    @FindBy(css = "[name='psgdpr']")
    WebElement policyCheckbox;

    @FindBy(css = "button.btn-primary")
    WebElement saveButton;


    public RegistrationPage setFirstName(String name) {
        firstNameInput.sendKeys(name);
        logger.info("W polu 'First name' wpisane wartość: {}", name);
        return this;
    }


    public RegistrationPage setLastName(String surname) {
        lastNameInput.sendKeys(surname);
        logger.info("W polu 'Last name' wpisane wartość: {}", surname);
        return this;
    }


    public RegistrationPage setEmail() {
        String email = RandomStringUtils.randomAlphanumeric(6);
        email += "@gmail.com";
        emailInput.sendKeys(email);
        logger.info("W polu 'Email' wpisane wartość: {}", email);
        return this;
    }

    public RegistrationPage setPassword(String password) {
        passwordInput.sendKeys(password);
        logger.info("W polu 'Last name' wpisane wartość: {}", password);
        return this;
    }

    public RegistrationPage checkCustomerPrivacyCheckbox() {
        customerPrivacyCheckbox.click();
        logger.info("Użytkownik zaznaczył 'customer privacy checkbox'");
        return this;
    }

    public RegistrationPage checkPolicyCheckbox() {
        policyCheckbox.click();
        logger.info("Użytkownik zaznaczył 'policy checkbox'");
        return this;
    }

    public MainPage clickOnSaveButton() {
//        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", saveButton);
        saveButton.click();
        logger.info("Użytkownik kliknął w przycisk 'save'");
        return new MainPage(driver);
    }


}
