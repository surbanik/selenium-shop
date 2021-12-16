package tests;

import org.junit.jupiter.api.Test;

public class TestTest extends TestBase{
    @Test
    public void titleTest(){
        System.out.println(driver.getTitle());
        System.out.println(System.getProperty("title"));
    }
}
