package pages;

import basePage.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class OnSalePage extends BasePage {

    public OnSalePage(WebDriver driver) {
        super(driver);
    }


    @FindBy(css = "div.product")
    List<WebElement> productsList;

    @FindBy(className = "product-flag")
    List<WebElement> discountLabelList;

    @FindBy(className = "regular-price")
    List<WebElement> regularPriceList;

    @FindBy(css = "span.price")
    List<WebElement> discountedPriceList;

    public int getRegularPriceAmount() {
        return regularPriceList.size();
    }

    public int getDiscountedPriceAmount() {
        return discountedPriceList.size();
    }

    public int getProductsAmount() {
        return productsList.size();
    }

    public int getDiscountLabelsAmount() {
        return discountLabelList.size();
    }

    public boolean isProductListDisplayed() {
        return getProductsAmount() > 0;
    }

    public boolean isDiscountLabelDisplayedOnEachProduct() {
        return getProductsAmount() == getDiscountLabelsAmount();
    }


    public List<WebElement> getRegularPriceList() {
        return regularPriceList;
    }

    public List<WebElement> getDiscountedPriceList() {
        return discountedPriceList;
    }

    public boolean isRegularPriceDisplayedOnEachProduct() {
        return getProductsAmount() == getRegularPriceAmount();
    }


    public boolean isDiscountedPriceDisplayedOnEachProduct() {
        return getProductsAmount() == getDiscountedPriceAmount();
    }


    public boolean isDiscountPriceIs20PercentLowerThenRegular() {
        for (int i = 0; i < getProductsAmount(); i++) {
            String discountedPrice = getElementText(getDiscountedPriceList().get(i));
            String regularPrice = getElementText(getRegularPriceList().get(i));

            double doubleDiscountedPrice = Double.parseDouble(discountedPrice.substring(2));
            double doubleRegularPrice = Double.parseDouble(regularPrice.substring(2));

            if (round(doubleRegularPrice * 0.8) != doubleDiscountedPrice) {
                return false;
            }
        }
        return true;
    }

    public ProductDetailPage clickOnRandomProduct(){
        performClick(productsList.get(random.nextInt(productsList.size())));
        return new ProductDetailPage(driver);
    }
}


