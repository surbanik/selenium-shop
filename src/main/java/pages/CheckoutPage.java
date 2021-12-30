package pages;

import basePage.BasePage;
import models.Basket;
import models.OrderDetails;
import models.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.regex.Pattern;


public class CheckoutPage extends BasePage {

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }


    @FindBy(css = "[name='address1']")
    WebElement addressInput;

    @FindBy(css = "[name='city']")
    WebElement cityInput;

    @FindBy(css = "[name='postcode']")
    WebElement postcodeInput;

    @FindBy(css = "[name='id_country']")
    WebElement countrySelect;

    @FindBy(css = ".form-footer .continue")
    WebElement personalInfoContinueButton;

    @FindBy(css = "#checkout-delivery-step .continue")
    WebElement deliveryInfoContinueButton;

    @FindBy(css = ".js-modal-content")
    WebElement popup;

    @FindBy(id = "payment-option-2")
    WebElement payByBankWireInput;

    @FindBy(id = "conditions_to_approve[terms-and-conditions]")
    WebElement termsAndConditionsCheckbox;

    @FindBy(id = "cta-terms-and-conditions-0")
    WebElement termsOfServiceLink;

    @FindBy(css = ".modal-content>.close")
    WebElement closePopupButton;

    @FindBy(css = ".ps-shown-by-js>[type='submit']")
    WebElement placeOrderButton;

    @FindBy(css = "div.col-sm-4.col-xs-12 > span")
    List<WebElement> shippingMethodList;

    @FindBy(css = ".delivery-options input[type='radio']")
    List<WebElement> shippingMethodInputList;

    @FindBy(css = "#cart-subtotal-shipping>span.value")
    WebElement shippingCost;


    public CheckoutPage fillAddressForm(User user) {
        Select country = new Select(countrySelect);

        typeTextTo(addressInput, user.getAddress());
        typeTextTo(cityInput, user.getCity());
        typeTextTo(postcodeInput, user.getZipCode());
        country.selectByVisibleText(user.getCountry());
        return this;

    }

    public CheckoutPage clickOnContinueButtonPersonalInfoSection() {
        performWaitAndClick(personalInfoContinueButton);
        return this;
    }


    public CheckoutPage clickOnContinueButtonDeliveryInfoSection() {
        performWaitAndClick(deliveryInfoContinueButton);
        return this;
    }

    public CheckoutPage selectPayByBankWire(OrderDetails orderDetails) {
        performClick(payByBankWireInput);
        orderDetails.setPaymentMethod("Bank transfer");
        return this;
    }

    public CheckoutPage clickOnTermsOfServiceLink() {
        performClick(termsOfServiceLink);
        return this;
    }

    public CheckoutPage clickOnTermsAndConditionsCheckbox() {
        performClick(termsAndConditionsCheckbox);
        return this;
    }

    public boolean isPopupNotEmpty() {
        waitUntilElementToBeVisible(popup);
        return !popup.getText().isBlank();
    }

    public CheckoutPage closePopup() {
        performWaitAndClick(closePopupButton);
        return this;
    }

    public CheckoutPage placeOrder(OrderDetails orderDetails) {
        performWaitAndClick(placeOrderButton);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        LocalDateTime now = LocalDateTime.now();
        orderDetails.setDate(dtf.format(now));
        orderDetails.setStatus("Awaiting bank wire payment");
        return this;
    }

    public CheckoutPage setRandomShippingMethod(Basket basket, OrderDetails orderDetails) {
        int rnd = random.nextInt(shippingMethodList.size());
        orderDetails.setShippingMethod(shippingMethodList.get(rnd).getText());
        if (rnd == 0) {
            basket.setTotalPrice(basket.getBasketTotal());
        } else {
            performClick(shippingMethodInputList.get(rnd));
            wait.until(ExpectedConditions.textMatches(By.cssSelector("#cart-subtotal-shipping>span.value"), Pattern.compile("[0-9]...")));
            basket.setTotalPrice(basket.getBasketTotal().add(getBigDecimalFromElementPrice(shippingCost)));
        }
        return this;
    }

}
