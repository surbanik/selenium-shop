package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class OrderHistoryRowPage {
    public OrderHistoryRowPage(WebElement table) {
        PageFactory.initElements(new DefaultElementLocatorFactory(table), this);
    }


    @FindBy(css = ":nth-child(1)")
    WebElement orderReference;

    @FindBy(css = ":nth-child(2)")
    WebElement date;

    @FindBy(css = ":nth-child(3)")
    WebElement totalPrice;

    @FindBy(css = ":nth-child(4)")
    WebElement paymentMethod;

    @FindBy(css = ":nth-child(5)>span")
    WebElement orderStatus;

    @FindBy(linkText = "Details")
    WebElement detailsLink;

    public BigDecimal getBigDecimalFromElementPrice(WebElement element){
        return BigDecimal.valueOf(Double.valueOf(element.getText().substring(1))).setScale(2, RoundingMode.HALF_UP);
    }

    public String getOrderReference() {
        return orderReference.getText();
    }

    public String getDate() {
        return date.getText();
    }

    public BigDecimal getTotalPrice() {
        return getBigDecimalFromElementPrice(totalPrice);
    }

    public String getPaymentMethod() {
        return paymentMethod.getText();
    }

    public String getOrderStatus() {
        return orderStatus.getText();
    }


}
