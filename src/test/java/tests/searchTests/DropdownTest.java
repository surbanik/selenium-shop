package tests.searchTests;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import pages.HeaderPage;
import pages.MainPage;
import testBase.TestBase;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Execution(ExecutionMode.CONCURRENT)
public class DropdownTest extends TestBase {

    @RepeatedTest(10)
    public void dropdownTest(){
        MainPage mainPage = new MainPage(driver);
        HeaderPage headerPage = new HeaderPage(driver);
        String randomProductName = mainPage.getRandomProductName();

        headerPage.
                enterTextToSearchInput(randomProductName);

        assertTrue(headerPage.isProductOnHintList(randomProductName));

    }
}
