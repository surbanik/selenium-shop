package Tests;

import Pages.LoginPage;
import Pages.MainPage;
import Pages.RegistrationPage;
import org.junit.jupiter.api.RepeatedTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RegistrationTest extends TestBase {

    @RepeatedTest(1)
    public void ragistrationTest() {
        String firstName = "TestName";
        String lastName = "TestLastName";
        String password = "testowe";


        new MainPage(driver)
                .goToLoginPage();

        new LoginPage(driver)
                .goToRegistrationForm();

        new RegistrationPage(driver)
                .setFirstName(firstName)
                .setLastName(lastName)
                .setEmail()
                .setPassword(password)
                .checkPolicyCheckbox()
                .checkCustomerPrivacyCheckbox()
                .clickOnSaveButton();

        assertEquals(firstName + " " + lastName, new MainPage(driver).getUserLogin());

    }
}