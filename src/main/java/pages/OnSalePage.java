package pages;

import basePage.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.math.BigDecimal;
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
            BigDecimal discountedPrice = getBigDecimalFromElementPrice(getDiscountedPriceList().get(i));
            BigDecimal regularPrice = getBigDecimalFromElementPrice(getRegularPriceList().get(i));

            if (!round(regularPrice.multiply(BigDecimal.valueOf(0.8))).equals(discountedPrice)) {
                return false;
            }
        }
        return true;
    }

    public ProductDetailPage clickOnRandomProduct() {
        performClick(productsList.get(random.nextInt(productsList.size())));
        return new ProductDetailPage(driver);
    }
}


