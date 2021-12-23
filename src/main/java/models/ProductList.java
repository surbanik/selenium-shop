package models;

import java.math.BigDecimal;


public class ProductList {
    Product product;
    int quantity;
    BigDecimal productListPrice;


    public ProductList(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
        this.productListPrice = product.getPrice().multiply(BigDecimal.valueOf(quantity));
    }


    public void setProduct(Product product) {
        this.product = product;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setProductListPrice(BigDecimal productListPrice) {
        this.productListPrice = productListPrice;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public BigDecimal getProductListPrice() {
        return productListPrice;
    }
}
