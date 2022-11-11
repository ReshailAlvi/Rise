package pageObjects;
import base.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.CommonMethods;

public class LoginPage extends Driver {

    CommonMethods helper;
    public LoginPage(){
        PageFactory.initElements(driver, this);
        helper = new CommonMethods();
    }

    /* === Web Elements === */
    @FindBy(id = "user-name")
    WebElement emailTextbox;

    @FindBy(id = "password")
    WebElement passwordTextbox;

    @FindBy(id = "login-button")
    WebElement loginButton;

    /* === Actions/functions for Web Elements === */
    public void enterEmail(String email){
        emailTextbox.sendKeys(email);
    }

    public void enterPassword(String password){
        passwordTextbox.sendKeys(password);
    }

    public void clickLoginButton(){
        loginButton.click();
    }

    public void login(String email, String password) {
        emailTextbox.sendKeys(email);
        passwordTextbox.sendKeys(password);
        loginButton.click();
    }

    /*
        Why have indiviual actions and then a login function?
        This will allow us to test negative scenarios like only entering username/password etc
     */
}
