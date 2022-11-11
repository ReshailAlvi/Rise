package utilities;

import base.Driver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import resources.Constants;

import java.time.Duration;
import java.util.List;

public class CommonMethods {

    WebDriver driver;

    public CommonMethods(){
        this.driver = Driver.driver;
    }

    public void hoverOverElement(WebElement ele){
        Actions builder = new Actions(driver);
        builder.moveToElement(ele).perform();
    }

    public void hoverOverElementAndClick(WebElement elementToHover, WebElement elementToClick){
        Actions builder = new Actions(driver);
        builder.moveToElement(elementToHover).perform();
        elementToClick.click();
    }

    public void clickElement(WebElement ele){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(Constants.standardWaitTime));
        try {
            WebElement element = wait.until(ExpectedConditions.elementToBeClickable(ele));
            element.click();
        }catch (Exception e){
            throw new RuntimeException("Unable to click on "+ele);
        }
    }

    public void forceClick(WebElement ele){
        //sometimes elements are both visible and enabled but still can't be clicked
        JavascriptExecutor executor=(JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click()", ele);
    }

    public void waitForLoader(){
        List<WebElement> element = driver.findElements(By.className("q-loader"));

        if(element.size() >0) {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(Constants.standardTimeOut));
            try {
                wait.until(ExpectedConditions.invisibilityOf(element.get(0)));
            } catch (Exception e) {
                throw new RuntimeException("The page kept loading...");
            }
        }
    }
}
