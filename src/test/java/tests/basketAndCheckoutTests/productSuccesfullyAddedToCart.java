package tests.basketAndCheckoutTests;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import pages.HeaderPage;
import pages.MainPage;
import testBase.TestBase;

@Execution(ExecutionMode.CONCURRENT)
public class productSuccesfullyAddedToCart extends TestBase {


    @RepeatedTest(1)
    public void productAddedToCart() {
        HeaderPage headerPage = new HeaderPage(driver);
        MainPage mainPage = new MainPage(driver);

        mainPage.clickOnRandomProduct();





    }
}
