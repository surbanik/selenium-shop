package models;

import java.math.BigDecimal;

public class Product {

    String name;
    BigDecimal price;

    public Product(String name, BigDecimal price) {
        this.name = name;
        this.price = price;
    }


    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }
}
