package models;

import lombok.Data;

@Data
public class OrderDetails {

    String paymentMethod;
    String shippingMethod;
    String orderReferenceNumber;
    String date;
    String status;
}
