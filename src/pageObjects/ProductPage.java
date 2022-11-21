package pageObjects;
import base.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import utilities.CommonMethods;

import java.util.List;

public class ProductPage extends Driver {

    CommonMethods helper;
    double cartValue = 0;
    public ProductPage(){
        PageFactory.initElements(driver,this);
        helper = new CommonMethods();
    }

    @FindBy(xpath = "//button[contains (@class, 'btn_inventory')]")
    List<WebElement> addToCartButton;

    @FindBy(xpath = "//*[contains (@class, 'shopping_cart_link')]")
    WebElement shoppingCartLink;

    @FindBy(xpath = "//div[contains (@class, 'cart_item_label')]")
    List<WebElement> itemsInCart;

    @FindBy(xpath = "//button[@class = 'btn btn_secondary btn_small cart_button']")
    WebElement removeItemFromCart;


    public void addElementsToCart(int numberOfItems) throws InterruptedException {

        List<WebElement> randomItemList = helper.selectRandomItems(addToCartButton,numberOfItems);

        for (int index = 0; index < numberOfItems; index++) {
            randomItemList.get(index).click();
            Thread.sleep(3000); // these are added just for demo purposes
        }
    }

    public void navigateToCart(){
        //navigate to shopping cart link
        shoppingCartLink.click();
    }

    public double fetchItemWithHighestValueInCart(int numberOfItems){
        //select item with highest value in the cart
        double highestValue = 0;
        List<WebElement> priceOfItems = itemsInCart.get(0).findElements(By.xpath("//*[contains (@class, 'inventory_item_price')]"));
        for(int i=0; i<numberOfItems; i++){
            String formatPrice = priceOfItems.get(i).getText().replace("$","");
            double value = Double.parseDouble(formatPrice);
            if(value > highestValue){
                highestValue = value;
            }
        }
        System.out.println("The item with the highest value is: "+highestValue);
        return highestValue;
    }

    public double getCartValue() {
        double total = 0;
        List<WebElement> priceOfItems = itemsInCart.get(0).findElements(By.xpath("//*[contains (@class, 'inventory_item_price')]"));
        for(int i=0; i<priceOfItems.size(); i++){
            String formatPrice = priceOfItems.get(i).getText().replace("$","");
            double value = Double.parseDouble(formatPrice);
            total = total + value;
        }
        return total;
    }

    public void getCartWithinBudget(double cartValue,int numberOfItemsInCart) throws InterruptedException {
        double currentItemPrice = 0.00;
        int halfTheCartValue = (int) (cartValue/2);

        for (int i = numberOfItemsInCart-1; i >= 0 ; i--) {
            cartValue = getCartValue();

            if(cartValue > halfTheCartValue) {
                System.out.println("Current cart value: "+cartValue);
                System.out.println("My budget: "+halfTheCartValue);
                Thread.sleep(5000); // these are added just for demo purposes
                currentItemPrice = helper.removeItemFromCart(itemsInCart,halfTheCartValue);
                Thread.sleep(5000); // these are added just for demo purposes
                cartValue = cartValue - currentItemPrice;
            }else{
                System.out.println("The value of cart is within budget now!");
                break;
            }
        }
    }
}

