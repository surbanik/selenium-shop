package tests.loginTests;

import models.User;
import models.UserFactory;
import pages.HeaderPage;
import pages.LoginPage;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import testBase.TestBase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Execution(ExecutionMode.CONCURRENT)
public class LoginTests extends TestBase {

    @RepeatedTest(1)
    public void loginFailed() {
        HeaderPage headerPage = new HeaderPage(driver);
        LoginPage loginPage = new LoginPage(driver);

        headerPage
                .goToLoginPage();

        loginPage
                .logInWithNonExistingUser();

        assertEquals(System.getProperty("loginFailMessage"), loginPage.getAlertMessage());
    }

    @RepeatedTest(1)
    public void loginSuccess() {
        HeaderPage headerPage = new HeaderPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        UserFactory userFactory = new UserFactory();
        User user = userFactory.getAlreadyRegisteredUser();

        headerPage
                .goToLoginPage();

        loginPage
                .logInWithExistingUser();

        assertEquals(user.getFirstName()+" "+user.getLastName(), new HeaderPage(driver).getUserLogin());

        headerPage
                .logOut();

        assertTrue(headerPage.isUserLoggedOut());


    }
}
