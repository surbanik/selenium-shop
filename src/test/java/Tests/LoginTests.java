package Tests;

import Pages.LoginPage;
import Pages.MainPage;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Execution(ExecutionMode.CONCURRENT)
public class LoginTests extends TestBase {

    @RepeatedTest(1)
    public void loginFailed() {

        new MainPage(driver)
                .goToLoginPage();

        new LoginPage(driver)
                .setEmail("email@email.pl")
                .setPassword("niepoprawnehaslo")
                .clickOnSignInButton();

        assertEquals("Authentication failed.", new LoginPage(driver).getAlertMessage());

    }

    @RepeatedTest(1)
    public void loginSuccess() {

        new MainPage(driver)
                .goToLoginPage();

        new LoginPage(driver)
                .setEmail("test@test.pl")
                .setPassword("testtest")
                .clickOnSignInButton();

        assertEquals("Test Test", new MainPage(driver).getUserLogin());

        new MainPage(driver)
                .logOut();

        assertTrue(new MainPage(driver).checkIsUserLogedOut());

    }
}
