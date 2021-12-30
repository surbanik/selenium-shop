package pages;

import basePage.BasePage;
import models.User;
import models.UserFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "div.col-md-6>input[name='email']")
    WebElement emailInput;

    @FindBy(css = "input[name='password']")
    WebElement passwordInput;

    @FindBy(id = "submit-login")
    WebElement signInButton;

    @FindBy(className = "alert")
    WebElement failedAlert;

    @FindBy(className = "no-account")
    WebElement registrationLink;


    public LoginPage setEmail(String email) {
        typeTextTo(emailInput, email);
        return this;
    }

    public LoginPage setPassword(String password) {
        typeTextTo(passwordInput, password);
        return this;
    }

    public LoginPage clickOnSignInButton() {
        performClick(signInButton);
        return this;
    }

    public String getAlertMessage() {
        return failedAlert.getText();
    }

    public RegistrationPage goToRegistrationForm() {
        performClick(registrationLink);
        return new RegistrationPage(driver);
    }

    public LoginPage logInWithNonExistingUser() {
        UserFactory userFactory = new UserFactory();
        User user = userFactory.getRandomUser();

        setEmail(user.getEmail());
        setPassword(user.getPassword());
        clickOnSignInButton();
        return this;
    }

    public LoginPage logInWithExistingUser() {
        UserFactory userFactory = new UserFactory();
        User user = userFactory.getAlreadyRegisteredUser();

        setEmail(user.getEmail());
        setPassword(user.getPassword());
        clickOnSignInButton();
        return this;
    }


}
