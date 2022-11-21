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
        driver.close();
    }

    @Test(priority = 1,dataProvider = "LoginTestData", dataProviderClass = DataProvider.class)
    public void signInWithValidCredentials(String testname,String userId, String pwd) {
        //login using the credentials fetched from data provider
        loginPage.login(userId,pwd);
        Assert.assertTrue(driver.getCurrentUrl().contains(Constants.productEndpoint));
    }

    @Test(priority = 2)
    public void addRandomItemsToCart() throws InterruptedException {
        productPage.addElementsToCart(Constants.numberOfItemsToSelectOnProductPage);
    }

    @Test(priority =  3)
    public void findItemWithHighestValueInCart() {
        productPage.navigateToCart();
        productPage.fetchItemWithHighestValueInCart(Constants.numberOfItemsToSelectOnProductPage);
    }

    @Test(priority = 4)
    public void bringCartUnderBudget() throws InterruptedException {
        double cartValue = productPage.getCartValue();
        productPage.getCartWithinBudget(cartValue,Constants.numberOfItemsToSelectOnProductPage);
    }

}
