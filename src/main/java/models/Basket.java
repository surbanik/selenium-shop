package models;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Basket {
    List<ProductList> basket;
    BigDecimal totalPrice;

    public Basket() {
        basket = new ArrayList<>();
    }

    public void addProductToBasket(String productName, BigDecimal price, int quantity){
        for (ProductList orderLine: basket){
            if (isProductAlreadyinTheBasket(productName,orderLine.getProduct())){
                orderLine.setQuantity(orderLine.getQuantity()+quantity);
                orderLine.setProductListPrice(BigDecimal.valueOf(orderLine.getQuantity()).multiply(orderLine.getProduct().getPrice()));
                return;
            }
        }
        addNewProductToBasket(new Product(productName,price),quantity);

    }

    public boolean isProductAlreadyinTheBasket(String productName, Product product){
        return product.getName().equals(productName);
    }

    public void addNewProductToBasket(Product product, int quantity){
        basket.add(new ProductList(product,quantity));
    }



    public BigDecimal getBasketTotal(){
        BigDecimal total = BigDecimal.valueOf(0);
        for (ProductList productList: basket){
            total = total.add(productList.getProductListPrice());
        }
        return total;
    }

}
