package pages;

import basePage.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class CartPage extends BasePage {

    public CartPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "[class='product-line-info']>.label")
    List<WebElement> productNameList;

    @FindBy(css = "input.js-cart-line-product-quantity")
    List<WebElement> quantityInputList;

    @FindBy(css = "span>strong")
    List<WebElement> productLinePriceList;

    @FindBy(css = ".cart-total>.value")
    WebElement totalPrice;

    @FindBy(css = "div.checkout .btn-primary")
    WebElement proceedToCheckoutButton;

    @FindBy(css = ".js-subtotal")
    WebElement totalNumberOFItems;

    @FindBy(css = "span>strong:nth-child(1)")
    WebElement firstProductOnList;

    @FindBy(css = ".current-price>.price")
    List<WebElement> singleProductPriceList;

    @FindBy(css = "button.js-increase-product-quantity")
    List<WebElement> increaseQuantityButtonList;

    @FindBy(css = "button.js-decrease-product-quantity")
    List<WebElement> decreaseQuantityButtonList;

    @FindBy(css = "a.remove-from-cart")
    List<WebElement> removeFromBasketList;


    public List<String> getProductsNameInCart() {
        List<String> nameList = new ArrayList<>();
        for (WebElement productName : productNameList) {
            nameList.add(productName.getText());
        }
        return nameList;
    }

    public List<Integer> getProductsQuantityInCart() {
        List<Integer> quantityList = new ArrayList<>();
        for (WebElement quantityInput : quantityInputList) {
            quantityList.add(Integer.valueOf(quantityInput.getAttribute("value")));
        }
        return quantityList;
    }

    public List<BigDecimal> getProductsPriceInCart() {
        List<BigDecimal> priceList = new ArrayList<>();
        for (WebElement productPrice : productLinePriceList) {
            priceList.add(getBigDecimalFromElementPrice(productPrice));
        }
        return priceList;
    }

    public List<BigDecimal> getUnitPriceInCart() {
        List<BigDecimal> priceList = new ArrayList<>();
        for (WebElement unitPrice : singleProductPriceList) {
            priceList.add(getBigDecimalFromElementPrice(unitPrice));
        }
        return priceList;
    }

    public BigDecimal getTotalPrice() {
        return getBigDecimalFromElementPrice(totalPrice);
    }


    public void setFirsProductQuantityTo5(){
        typeTextTo(quantityInputList.get(0), "5");
        typeEnterOnElement(quantityInputList.get(0));
        performWaitAndClick(totalPrice);
        wait.until(ExpectedConditions.textToBePresentInElement(firstProductOnList,"$"+ getUnitPriceInCart().get(0).multiply(BigDecimal.valueOf(5))));
    }

    public boolean isTotalOrderValueIsCorrect(){
        if (quantityInputList.size() > 0) {

            BigDecimal orderPrice = BigDecimal.valueOf(0);
            for (int i = 0; i < quantityInputList.size(); i++) {
                BigDecimal quantity = BigDecimal.valueOf(getProductsQuantityInCart().get(i));
                BigDecimal price = getUnitPriceInCart().get(i);
                orderPrice = orderPrice.add(price.multiply(quantity));
            }
            return (getTotalPrice().equals(orderPrice));
        }
        return (getTotalPrice().equals(BigDecimal.valueOf(0).setScale(2, RoundingMode.HALF_UP)));
    }

    public boolean isIncreaseingFirstProductQuantityChangePrice(){
        int prevValue = Integer.valueOf(totalNumberOFItems.getText().split(" ")[0]);
        performWaitAndClick(increaseQuantityButtonList.get(0));
        prevValue += 1;
        wait.until(ExpectedConditions.textToBePresentInElement(totalNumberOFItems, prevValue + " items"));
        return (getUnitPriceInCart().get(0).multiply(BigDecimal.valueOf(getProductsQuantityInCart().get(0))).equals(getProductsPriceInCart().get(0)));
    }

    public boolean isDecreaseingFirstProductQuantityChangePrice() {
        int prevValue = Integer.valueOf(totalNumberOFItems.getText().split(" ")[0]);
        performWaitAndClick(decreaseQuantityButtonList.get(0));
        prevValue -= 1;
        wait.until(ExpectedConditions.textToBePresentInElement(totalNumberOFItems, prevValue + " items"));
        return (getUnitPriceInCart().get(0).multiply(BigDecimal.valueOf(getProductsQuantityInCart().get(0))).equals(getProductsPriceInCart().get(0)));
    }

    public void deleteTopItemFromBasket() {
        int prevValue = Integer.valueOf(totalNumberOFItems.getText().split(" ")[0]);
        int numberOfDeletedProducts = Integer.valueOf(quantityInputList.get(0).getAttribute("value"));
        int aftValue = prevValue - numberOfDeletedProducts;
        performWaitAndClick(removeFromBasketList.get(0));
        String message = (aftValue != 1) ? " items" : " item";
        wait.until(ExpectedConditions.textToBePresentInElement(totalNumberOFItems, aftValue + message));
    }

    public CheckoutPage goToCheckout(){
        performWaitAndClick(proceedToCheckoutButton);
        return new CheckoutPage(driver);
    }


}
