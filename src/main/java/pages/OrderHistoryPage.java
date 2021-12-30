package pages;

import basePage.BasePage;
import models.Basket;
import models.OrderDetails;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Thread.sleep;

public class OrderHistoryPage extends BasePage {
    public OrderHistoryPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "tbody>tr")
    List<WebElement> table;

    public List<OrderHistoryRowPage> createOrderHistoryRowPageList() {
        List<OrderHistoryRowPage> orderHistoryRowPageList = new ArrayList<>();
        for (WebElement row : table) {
            orderHistoryRowPageList.add(new OrderHistoryRowPage(row));
        }
        return orderHistoryRowPageList;
    }

    public OrderHistoryRowPage getOrderHistoryRow(OrderDetails orderDetails) {
        for (OrderHistoryRowPage rowPage : createOrderHistoryRowPageList()) {
            if (rowPage.getOrderReference().equals(orderDetails.getOrderReferenceNumber())) {
                return rowPage;
            }
        }
        return null;
    }

    public boolean isOrderDetailsAreCorrect(OrderDetails orderDetails, Basket basket) {
        OrderHistoryRowPage historyRow = getOrderHistoryRow(orderDetails);
        return (historyRow.getDate().equals(orderDetails.getDate())
                && historyRow.getTotalPrice().equals(basket.getTotalPrice())
                && historyRow.getPaymentMethod().equals(orderDetails.getPaymentMethod())
                && historyRow.getOrderStatus().equals(orderDetails.getStatus()));
    }

    public OrderDetailsPage goToOrderDetailsPage(OrderDetails orderDetails) throws InterruptedException {
        sleep(2000);
        OrderHistoryRowPage historyRow = getOrderHistoryRow(orderDetails);
        performClick(historyRow.detailsLink);
        return new OrderDetailsPage(driver);
    }
}

