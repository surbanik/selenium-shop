package pages;

import basePage.BasePage;
import models.Basket;
import models.User;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

public class OrderDetailsPage extends BasePage {
    public OrderDetailsPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "#order-products>tbody>tr")
    List<WebElement> table;

    @FindBy(css = "article>address")
    List<WebElement> addresses;

    public List<OrderDetailsRowPage> createOrderDetailRowPageList() {
        List<OrderDetailsRowPage> orderDetailRowPageList = new ArrayList<>();
        for (WebElement row : table) {
            orderDetailRowPageList.add(new OrderDetailsRowPage(row));
        }
        return orderDetailRowPageList;
    }


    public boolean isProductDetailsAreCorrect(Basket basket) {
        List<OrderDetailsRowPage> rowPageList = createOrderDetailRowPageList();
        for (int i = 0; i < basket.getProductLinesAmount(); i++) {
            if (!(rowPageList.get(i).getproductName().contains(basket.getBasket().get(i).getProduct().getName())
                    && rowPageList.get(i).getQuantity() == basket.getBasket().get(i).getQuantity()
                    && rowPageList.get(i).getUnitPrice().equals(basket.getBasket().get(i).getProduct().getPrice())
                    && rowPageList.get(i).getProductLinePrice().equals(basket.getBasket().get(i).getProductLinePrice()))) {
                return false;
            }
        }
        return true;
    }

    public boolean isDeliveryAddressIsCorrect(User user) {
        for (WebElement address : addresses) {
            if (!(address.getText().contains(user.getAddress())
                    && address.getText().contains(user.getCountry())
                    && address.getText().contains(user.getZipCode())
                    && address.getText().contains(user.getCity()))) {
                return false;
            }
        }
        return true;
    }
}
