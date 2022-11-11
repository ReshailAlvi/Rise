package tests;

import base.Driver;
import org.testng.annotations.*;
import pageObjects.ProductPage;
import resources.Constants;

public class ProductPageTests extends Driver {

    ProductPage productPage;

    @BeforeClass
    @Parameters("browser")
    public void setup(String browser) throws Exception {
        initiateDriver(browser);
        productPage = new ProductPage();
    }

    @AfterClass
    public void closeBrowser(){
        driver.quit();
    }


    @Test(priority = 1)
    public void addRandomItemsToCart(){
        productPage.addElementsToCart(Constants.numberOfItemsToSelectOnProductPage);
    }
}
