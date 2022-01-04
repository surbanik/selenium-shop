package tests.registrationTests;

import models.User;
import models.UserFactory;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import pages.HeaderPage;
import pages.LoginPage;
import pages.RegistrationPage;
import org.junit.jupiter.api.RepeatedTest;
import testBase.TestBase;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Execution(ExecutionMode.CONCURRENT)
public class RegistrationTest extends TestBase {

    @RepeatedTest(1)
    public void ragistrationTest() {
        HeaderPage headerPage = new HeaderPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        RegistrationPage registrationPage = new RegistrationPage(driver);
        UserFactory userFactory = new UserFactory();
        User user = userFactory.getRandomUser();


        headerPage
                .goToLoginPage();

        loginPage
                .goToRegistrationForm();

        registrationPage
                .registerNewUser(user);

        assertEquals(user.getFirstName() + " " + user.getLastName(), headerPage.getUserLogin());

    }
}