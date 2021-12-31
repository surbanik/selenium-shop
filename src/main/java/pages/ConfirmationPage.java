package pages;

import basePage.BasePage;
import models.Basket;
import models.OrderDetails;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ConfirmationPage extends BasePage {

    public ConfirmationPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = ".details>span")
    List<WebElement> productNameList;

    @FindBy(css = ".text-xs-left")
    List<WebElement> unitPriceList;

    @FindBy(css = ".text-xs-right")
    List<WebElement> productLinePriceList;

    @FindBy(css = "[class='col-xs-4 text-sm-center']")
    List<WebElement> quantityList;

    @FindBy(css = "#order-details ul>li:nth-child(1)")
    WebElement referenceNumber;

    @FindBy(css = "#order-details ul>li:nth-child(2)")
    WebElement paymentMethod;

    @FindBy(css = "#order-details ul>li:nth-child(3)")
    WebElement shippingMethod;


    public String getReferenceNumber() {
        return referenceNumber.getText().split(" ")[2];
    }

    public String getPaymentMethod() {
        return paymentMethod.getText();
    }

    public String getShippingMethod() {
        return shippingMethod.getText();
    }


    public List<BigDecimal> getUnitPriceList() {
        List<BigDecimal> list = new ArrayList<>();
        for (WebElement element : unitPriceList) {
            list.add(getBigDecimalFromElementPrice(element));
        }
        return list;
    }

    public List<BigDecimal> getProductLinePriceList() {
        List<BigDecimal> list = new ArrayList<>();
        for (WebElement element : productLinePriceList) {
            list.add(getBigDecimalFromElementPrice(element));
        }
        return list;
    }

    public boolean isProductDetailsAreCorrect(Basket basket) {
        for (int i = 0; i < basket.getProductLinesAmount(); i++) {
            if (!(productNameList.get(i).getText().contains(basket.getBasket().get(i).getProduct().getName())
                    && basket.getBasket().get(i).getQuantity() == Integer.valueOf(quantityList.get(i).getText())
                    && basket.getBasket().get(i).getProduct().getPrice().equals(getUnitPriceList().get(i))
                    && basket.getBasket().get(i).getProductLinePrice().equals(getProductLinePriceList().get(i)))) {
                return false;
            }
        }
        return true;
    }

    public boolean isPaymentAndShippingMethodsAreCorrect(OrderDetails orderDetails) {
        return getPaymentMethod().contains(orderDetails.getPaymentMethod())
                && getShippingMethod().contains(orderDetails.getShippingMethod());
    }

    public ConfirmationPage saveReferenceNumber(OrderDetails orderDetails){
        orderDetails.setOrderReferenceNumber(getReferenceNumber());
        return this;
    }
}