package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;

public class FooterPage {

    public FooterPage(WebElement footer) {
        PageFactory.initElements(new DefaultElementLocatorFactory(footer), this);
    }

    @FindBy(id="link-product-page-prices-drop-1")
    WebElement pricesDrop;



}
