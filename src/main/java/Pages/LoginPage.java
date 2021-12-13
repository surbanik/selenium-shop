package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoginPage extends PageBase {
    static private Logger logger = LoggerFactory.getLogger(LoginPage.class);

    public LoginPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
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
        emailInput.sendKeys(email);
        logger.info("W polu 'Email' wpisano wartość {}", email);
        return this;
    }

    public LoginPage setPassword(String password) {
        passwordInput.sendKeys(password);
        logger.info("W polu 'Password' wpisano wartość {}", password);
        return this;
    }

    public LoginPage clickOnSignInButton() {
        signInButton.click();
        logger.info("Użytkownik użył przycisku 'Sign In'");
        return this;
    }

    public String getAlertMessage() {
        return failedAlert.getText();
    }

    public RegistrationPage goToRegistrationForm() {
        registrationLink.click();
        logger.info("Użytkownik przeszedł do formularza rejestracyjnego");
        return new RegistrationPage(driver);
    }

}
