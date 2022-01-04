package models;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductLine {
    Product product;
    int quantity;
    BigDecimal productLinePrice;


    public ProductLine(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
        this.productLinePrice = product.getPrice().multiply(BigDecimal.valueOf(quantity));
    }

}
