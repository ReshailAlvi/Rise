package tests;

import base.Driver;
import org.testng.Assert;
import org.testng.annotations.*;
import pageObjects.ProductPage;
import pageObjects.LoginPage;
import resources.Constants;
import utilities.DataProvider;

public class LoginTests extends Driver{
    LoginPage loginPage;
    @BeforeClass
    @Parameters("browser")
    public void setup(String browser) throws Exception {
        initiateDriver(browser);
        loginPage = new LoginPage();
    }

    @BeforeMethod
    public void navigateToLogin(){
        Assert.assertTrue(driver.getCurrentUrl().contains(Constants.loginEndpoint));
    }

    @Test(priority = 1,dataProvider = "LoginTestData", dataProviderClass = DataProvider.class)
    public void signInWithValidCredentials(String testname,String userId, String pwd) {
        //login using the credentials fetched from data provider
        loginPage.login(userId,pwd);
        Assert.assertTrue(driver.getCurrentUrl().contains(Constants.productEndpoint));
    }

}
