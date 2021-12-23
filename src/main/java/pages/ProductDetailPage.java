package pages;

import basePage.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProductDetailPage extends BasePage {

    public ProductDetailPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "span.discount-percentage")
    WebElement discountLabel;


    @FindBy(css = "span.regular-price")
    WebElement regularPrice;

    @FindBy(css = ".current-price>span[itemprop='price']")
    public WebElement discountedPrice;


    public boolean isDiscountLabelIsDispalyed() {
        return discountLabel.isDisplayed();
    }

    public boolean isRegularPriceIsDispalyed() {
        return regularPrice.isDisplayed();
    }

    public boolean isDiscountedPriceIsDispalyed() {
        return discountedPrice.isDisplayed();
    }

    public WebElement getRegularPrice() {
        return regularPrice;
    }

    public WebElement getDiscountedPrice() {
        return discountedPrice;
    }

    public boolean isDiscountPriceIs20PercentLowerThenRegular() {
        String regularPrice = getElementText(getRegularPrice());
        String discountedPrice = getElementText(getDiscountedPrice());

        double doubleDiscountedPrice = Double.parseDouble(discountedPrice.substring(2));
        double doubleRegularPrice = Double.parseDouble(regularPrice.substring(2));

        return (round(doubleRegularPrice * 0.8) == doubleDiscountedPrice);
    }


}
