package tests;

import base.Driver;
import org.testng.Assert;
import org.testng.annotations.*;
import pageObjects.LoginPage;
import pageObjects.ProductPage;
import resources.Constants;
import utilities.DataProvider;

public class ProductPageTests extends Driver {

    LoginPage loginPage;
    ProductPage productPage;
    @BeforeClass
    @Parameters({"browser"})
    public void setup(String browser) throws Exception {
        initiateDriver(browser);
        loginPage = new LoginPage();
        productPage = new ProductPage();
    }

    @AfterClass
    public void closeBrowser(){
        driver.quit();
    }

    @Test(priority = 1,dataProvider = "LoginTestData", dataProviderClass = DataProvider.class)
    public void signInWithValidCredentials(String testname,String userId, String pwd) {
        //login using the credentials fetched from data provider
        loginPage.login(userId,pwd);
        Assert.assertTrue(driver.getCurrentUrl().contains(Constants.productEndpoint));
    }

    @Test(priority = 2)
    public void addRandomItemsToCart(){
        productPage.addElementsToCart(Constants.numberOfItemsToSelectOnProductPage);
    }

}
