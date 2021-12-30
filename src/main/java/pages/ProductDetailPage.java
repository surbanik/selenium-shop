package pages;

import basePage.BasePage;
import models.Basket;
import models.Product;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class ProductDetailPage extends BasePage {
    static private Logger logger = LoggerFactory.getLogger(ProductDetailPage.class);

    public ProductDetailPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "span.discount-percentage")
    WebElement discountLabel;

    @FindBy(css = "span.regular-price")
    WebElement regularPrice;

    @FindBy(css = ".current-price>span[itemprop='price']")
    WebElement discountedPrice;

    @FindBy(css = ".add-to-cart")
    WebElement addToCartButton;

    @FindBy(id = "quantity_wanted")
    WebElement quantityInput;

    @FindBy(css = "div>[itemprop='name']")
    WebElement productName;

    @FindBy(css = "[class='h6 product-name']")
    WebElement confirmationProductName;

    @FindBy(css = "span.product-quantity>strong")
    WebElement confirmationQuantity;

    @FindBy(css = "p.product-price")
    WebElement confirmationPrice;

    @FindBy(css = "p.product-total>span.value")
    WebElement confirmationTotal;

    @FindBy(css = ".cart-content>p.cart-products-count")
    WebElement confirmationNumberOfItemsInCartMessage;

    @FindBy(css = ".btn-secondary")
    WebElement confirmationContinueShoppingButton;

    @FindBy(css = ".cart-content-btn>.btn-primary")
    WebElement confirmationProceedToCheckout;

    @FindBy(css = "[placeholder='Your message here']")
    WebElement customizationInput;

    @FindBy(css = "[name='submitCustomizedData']")
    WebElement saveCustomizationButton;

    @FindBy(css = ".product-unavailable")
    List<WebElement> productUnavailable;

    @FindBy(css = ".alert-danger")
    List<WebElement> noProductAlert;


    public String getConfirmationProductName() {
        waitUntilElementToBeVisible(confirmationProductName);
        return confirmationProductName.getText();
    }

    public int getConfirmationNumberOfItemsInCart() {
        String message = confirmationNumberOfItemsInCartMessage.getText();
        String[] messageArr = message.split(" ");
        return Integer.valueOf(messageArr[2]);
    }

    public BigDecimal getConfirmationTotal() {
        return getBigDecimalFromElementPrice(confirmationTotal);
    }

    public String getProductName() {
        return productName.getText();
    }


    public boolean isDiscountLabelIsDispalyed() {
        return discountLabel.isDisplayed();
    }

    public boolean isRegularPriceIsDispalyed() {
        return regularPrice.isDisplayed();
    }

    public boolean isDiscountedPriceIsDispalyed() {
        return discountedPrice.isDisplayed();
    }

    public int getConfirmationQuantity() {
        return Integer.valueOf(confirmationQuantity.getText());
    }

    public BigDecimal getConfirmationPrice() {
        return getBigDecimalFromElementPrice(confirmationPrice);
    }

    public BigDecimal getRegularPrice() {
        return getBigDecimalFromElementPrice(regularPrice);
    }

    public BigDecimal getDiscountedPrice() {
        return getBigDecimalFromElementPrice(discountedPrice);
    }

    public boolean isDiscountPriceIs20PercentLowerThenRegular() {
        return (round(getRegularPrice().multiply(BigDecimal.valueOf(0.8))).equals(getDiscountedPrice()));
    }

    public ProductDetailPage setRandomQuantity(int origin, int bound) {
        int randomNum = ThreadLocalRandom.current().nextInt(origin, bound);
        typeTextTo(quantityInput, String.valueOf(randomNum));
        return this;
    }

    public int getQuantityInputValue() {
        return Integer.valueOf(quantityInput.getAttribute("value"));
    }

    public ProductDetailPage addDisplayedProductToCart(Basket basket) {
        if (getProductName().contains("CUSTOM")) {
            performClick(customizationInput);
            customizationInput.sendKeys("YourMessage");
            isProductAvailable();
            performClick(saveCustomizationButton);
        }
        basket.addProductToBasket(getProductName(), getDiscountedPrice(), getQuantityInputValue());
        isProductAvailable();
        performWaitAndClick(addToCartButton);
        return this;
    }

    public Product getDisplayedProduct() {
        return new Product(getProductName(), getDiscountedPrice());
    }

    public ProductDetailPage continueShopping() {
        performWaitAndClick(confirmationContinueShoppingButton);
        return this;
    }

    public CartPage proceedToCheckout() {
        performWaitAndClick(confirmationProceedToCheckout);
        return new CartPage(driver);
    }

    public void isProductAvailable() {
        if (noProductAlert.size() + productUnavailable.size() > 0) {
            logger.warn("BRAK PRODUKTÓW!!! TEST PRZERWANY");
            driver.quit();
        }
    }
}
