package base;

import java.io.*;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import resources.Constants;


public class Driver {

    public static WebDriver driver;
    public static Properties envConfig;

    public void getProperties() throws FileNotFoundException {
        //Environment specific properties file loading
        InputStream configFile = new FileInputStream(System.getProperty("user.dir") + Constants.configProperties);
        envConfig = new Properties();
        try {
            envConfig.load(configFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void initiateDriver(String browserToUse) throws Exception {
        this.getProperties();
        /*
        The browser is currently parametrized but we can also read it from config.prop like below
         */
        String browser = envConfig.getProperty("browser");
        String headless = envConfig.getProperty("headless");

        //Browser configuration
        if (browserToUse.equalsIgnoreCase("Firefox")) {
            WebDriverManager.firefoxdriver().setup();
            FirefoxOptions options = new FirefoxOptions();
            if(headless.equalsIgnoreCase("YES")){
                options.addArguments("--headless");
            }
            driver = new RemoteWebDriver(new URL(Constants.remoteDriver),options);
        } else if (browserToUse.equalsIgnoreCase("Chrome")) {
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--disable-notifications");
            if(headless.equalsIgnoreCase("YES")){
                options.addArguments("--headless");
            }
            driver = new RemoteWebDriver(new URL(Constants.remoteDriver),options);
        } else if (browserToUse.equalsIgnoreCase("IE")) {
            WebDriverManager.iedriver().setup();
            driver = new InternetExplorerDriver();
            InternetExplorerOptions options = new InternetExplorerOptions();
            driver = new RemoteWebDriver(new URL(Constants.remoteDriver),options);
        } else {
            throw new RuntimeException("Browser type unsupported");
        }

        //Setting implicit wait
        driver.manage().timeouts().implicitlyWait(Constants.standardTimeOut, TimeUnit.SECONDS);

        driver.manage().window().maximize();

        driver.get(envConfig.getProperty("baseUrl"));
    }

    @BeforeMethod
    public void loadBaseUrl(Method method) {
        driver.get(envConfig.getProperty("baseUrl"));

    }

    @AfterMethod
    public void deleteCookies(){
        driver.manage().deleteAllCookies();
    }

    @AfterSuite
    public void suiteTearDown() {
        driver.quit();
    }
}