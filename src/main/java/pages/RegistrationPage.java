package pages;

import basePage.BasePage;
import models.User;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RegistrationPage extends BasePage {
    static private Logger logger = LoggerFactory.getLogger(RegistrationPage.class);

    public RegistrationPage(WebDriver driver) {super(driver);}

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
        typeTextTo(firstNameInput,name);
        return this;
    }

    public RegistrationPage setLastName(String lastName) {
        typeTextTo(lastNameInput,lastName);
        return this;
    }

    public RegistrationPage setEmail(String email) {
        typeTextTo(emailInput, email);
        return this;
    }

    public RegistrationPage setPassword(String password) {
        typeTextTo(passwordInput, password);
        return this;
    }

    public RegistrationPage checkCustomerPrivacyCheckbox() {
        performClick(customerPrivacyCheckbox);
        return this;
    }

    public RegistrationPage checkPolicyCheckbox() {
        performClick(policyCheckbox);
        return this;
    }

    public HeaderPage clickOnSaveButton() {
        performClick(saveButton);
        return new HeaderPage(driver);
    }

    public RegistrationPage registerNewUser(User user){
        setFirstName(user.getFirstName());
        setLastName(user.getLastName());
        setEmail(user.getEmail());
        setPassword(user.getPassword());
        checkCustomerPrivacyCheckbox();
        checkPolicyCheckbox();
        clickOnSaveButton();
        return this;
    }
}
