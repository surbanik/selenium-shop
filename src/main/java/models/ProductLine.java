package models;

import java.math.BigDecimal;


public class ProductLine {
    Product product;
    int quantity;
    BigDecimal productLinePrice;


    public ProductLine(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
        this.productLinePrice = product.getPrice().multiply(BigDecimal.valueOf(quantity));
    }


    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setProductLinePrice(BigDecimal productLinePrice) {
        this.productLinePrice = productLinePrice;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public BigDecimal getProductLinePrice() {
        return productLinePrice;
    }
}
