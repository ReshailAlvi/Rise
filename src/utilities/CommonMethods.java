package utilities;

import base.Driver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import resources.Constants;

import java.time.Duration;
import java.util.*;

public class CommonMethods {

    WebDriver driver;

    public CommonMethods(){
        this.driver = Driver.driver;
    }

    public List<WebElement> selectRandomItems(List<WebElement> list, int numberOfItems){
        HashSet<Integer> set = new HashSet<>();
        Random random = new Random();
        List<WebElement> randomList = new ArrayList<>();
        for (int i = 0; i <numberOfItems ; i++) {
            int int_random = random.nextInt(list.size());
            while(set.contains(int_random)){
                int_random = random.nextInt(list.size());
            }
            set.add(int_random);
        }

        // creates Iterator oblect.
        Iterator itr = set.iterator();

        for (int j = 0; j < numberOfItems; j++) {
            int index = (int) itr.next();
            randomList.add(j,list.get(index));
        }
        return randomList;
    }

    public double removeItemFromCart(List<WebElement> list, int budget) throws InterruptedException {
        double[] priceArray = getPriceOfAllItemsInCart(list);
        int index = findItemToRemove(priceArray,budget);
        double valueOfItem = priceArray[index];
        System.out.println("Removing this item from cart: "+valueOfItem);
        //remove that item from list
        removeItem(list.get(index),valueOfItem);
        Thread.sleep(5000); // these are added just for demo purposes
        //return value of item returned so it can be deducted from cart
        return valueOfItem;
    }

    public double[] getPriceOfAllItemsInCart(List<WebElement> listOfItems){
        double[] priceArray = new double[listOfItems.size()];
        List<WebElement> priceOfItems = listOfItems.get(0).findElements(By.xpath("//*[contains (@class, 'inventory_item_price')]"));
        for(int i=0; i<listOfItems.size(); i++) {
            String formatPrice = priceOfItems.get(i).getText().replace("$", "");
            double value = Double.parseDouble(formatPrice);
            priceArray[i] = value;
        }
        return priceArray;
    }

    public void removeItem(WebElement ele,double valueOfItem){
        /*
            This makes no sense (I know!)
         */
        if(valueOfItem == 49.99){
            ele.findElement(By.id("remove-sauce-labs-fleece-jacket")).click();

        }else{
            ele.findElement(By.xpath("//button[text() = 'Remove']")).click();
        }
    }

    public int findItemToRemove(double[] arr,int budget) {
        double differenceFromBudget = Math.abs(arr[0] - budget);
        int idx = 0;
        for(int c = 1; c < arr.length; c++){
            double currentItem = Math.abs(arr[c] - budget);
            if(currentItem < differenceFromBudget){
                idx = c;
                differenceFromBudget = currentItem;
            }
        }
        return idx;
    }
}
