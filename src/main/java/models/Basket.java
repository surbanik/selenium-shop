package models;

import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
public class Basket {


    List<ProductLine> basket;
    BigDecimal totalPrice;

    public Basket() {
        basket = new ArrayList<>();
    }



    public void addProductToBasket(String productName, BigDecimal price, int quantity) {
        for (ProductLine orderLine : basket) {
            if (isProductAlreadyInBasket(productName, orderLine.getProduct())) {
                orderLine.setQuantity(orderLine.getQuantity() + quantity);
                orderLine.setProductLinePrice(BigDecimal.valueOf(orderLine.getQuantity()).multiply(orderLine.getProduct().getPrice()));
                return;
            }
        }
        addNewProductToBasket(new Product(productName, price), quantity);

    }

    protected boolean isProductAlreadyInBasket(String productName, Product product) {
        return product.getName().equals(productName);
    }

    protected void addNewProductToBasket(Product product, int quantity) {
        basket.add(new ProductLine(product, quantity));
    }


    public BigDecimal getBasketTotal() {
        BigDecimal total = BigDecimal.valueOf(0);
        for (ProductLine productList : basket) {
            total = total.add(productList.getProductLinePrice());
        }
        return total;
    }

    public int getBasketSize() {
        int total = 0;
        for (ProductLine productList : basket) {
            total += productList.getQuantity();
        }
        return total;
    }


    public int getProductQuantityByName(String productName) {
        for (ProductLine productList : basket) {
            if (productList.getProduct().getName().equals(productName)) {
                return productList.getQuantity();
            }
        }
        return 0;
    }

    public List<String> getProductsNameInBasket(){
        List<String> nameList = new ArrayList<>();
        for(ProductLine productLine:basket){
            nameList.add(productLine.getProduct().getName());
        }
        return nameList;
    }

    public List<Integer> getProductsQuantitiesInBasket(){
        List<Integer> quantitiesList = new ArrayList<>();
        for(ProductLine productLine:basket){
            quantitiesList.add(productLine.getQuantity());
        }
        return quantitiesList;
    }

    public List<BigDecimal> getProductsPriceInBasket(){
        List<BigDecimal> priceList = new ArrayList<>();
        for(ProductLine productLine:basket){
            priceList.add(productLine.getProductLinePrice());
        }
        return priceList;
    }

    public int getProductLinesAmount(){
        return basket.size();
    }


}




