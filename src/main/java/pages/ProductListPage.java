package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;

import java.util.List;
import java.util.Random;

public class ProductListPage{

    public ProductListPage(WebElement productLIst) {
        PageFactory.initElements(new DefaultElementLocatorFactory(productLIst), this);
    }

    @FindBy(className = "product-title")
    List<WebElement> productTitleList;

    @FindBy(css = "div.product")
    List<WebElement> product;

    public int getRandomProductIndex(){
        Random random = new Random();
        return random.nextInt(product.size());
    }

    public WebElement getRandomProduct(){
        return product.get(getRandomProductIndex());
    }

    public String getRandomProductTitle(){
        return productTitleList.get(getRandomProductIndex()).getText();

    }

    public boolean isProductOnList(String randomProductName) {
        for(WebElement product: productTitleList){
            if (product.getText().equals(randomProductName)){
                return true;
            }
        }
        return false;
    }

    public int howManyProductsOnList(){
        return productTitleList.size();
    }
}
