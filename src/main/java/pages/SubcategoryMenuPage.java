package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;

import java.util.List;

public class SubcategoryMenuPage {
    
    public SubcategoryMenuPage(WebElement subcategoryMenu) {
        PageFactory.initElements(new DefaultElementLocatorFactory(subcategoryMenu), this);
    }

    @FindBy(css = "ul>li>a")
    List<WebElement> subcategoryList;

    public List<WebElement> getSubcategoryList() {
        return subcategoryList;
    }

}
