package resources;

public class Constants {
    //Remote driver endpoint
    public static String remoteDriver = "http://localhost:4445";
    //config.properties file path
    public static String configProperties = "/src/resources/config.properties";
    //endpoints to validate
    public static String loginEndpoint = "https://www.saucedemo.com/";
    public static String productEndpoint = "/inventory.html";
    //path of excel sheets for data
    public static String pathForTestData = "/src/resources/testdata.xlsx";
    public static String loginDataFileName = "LoginData";
    //Driver variables
    public static int standardTimeOut = 20;
    public static int standardWaitTime = 10;
    //Test data to be used in tests
    public static int numberOfItemsToSelectOnProductPage = 3;
}
