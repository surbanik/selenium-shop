package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;

import java.math.BigDecimal;

public class OrderDetailsRowPage {
    public OrderDetailsRowPage(WebElement table) {
        PageFactory.initElements(new DefaultElementLocatorFactory(table), this);
    }


    @FindBy(css = "strong>a")
    WebElement productName;

    @FindBy(css = "td:nth-child(2)")
    WebElement quantity;

    @FindBy(css = "td:nth-child(3)")
    WebElement unitPrice;

    @FindBy(css = "td:nth-child(4)")
    WebElement productLinePrice;

    public BigDecimal getBigDecimalFromElementPrice(WebElement element){
        return BigDecimal.valueOf(Double.valueOf(element.getText().substring(2)));
    }

    public BigDecimal getProductLinePrice() {
        return getBigDecimalFromElementPrice(productLinePrice);
    }

    public BigDecimal getUnitPrice() {
        return getBigDecimalFromElementPrice(unitPrice);
    }

    public int getQuantity() {
        return Integer.valueOf(quantity.getText());
    }

    public String getproductName() {
        return productName.getText();
    }


}
