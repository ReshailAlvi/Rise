package pageObjects;
import base.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import utilities.CommonMethods;

import java.util.List;

public class ProductPage extends Driver {

    CommonMethods helper;

    public ProductPage(){
        PageFactory.initElements(driver,this);
        helper = new CommonMethods();
    }

    @FindBy(xpath = "//button[contains (@class, 'btn_inventory')]")
    List<WebElement> addToCartButton;

    public void addElementsToCart(int numberOfItems){
        if(numberOfItems > addToCartButton.size()) {
            System.out.println("Please select item count within this range: "+addToCartButton.size());
            return;
        }

        List<WebElement> randomItemList = helper.selectRandomItems(addToCartButton,numberOfItems);

        for (int index = 0; index < numberOfItems; index++) {
            randomItemList.get(index).click();
        }
    }
}

