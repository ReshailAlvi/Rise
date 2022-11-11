package utilities;

import resources.Constants;

import java.lang.reflect.Method;
import java.util.Arrays;

public class DataProvider {

    public static String[][] testData;

    @org.testng.annotations.DataProvider(name = "LoginTestData")
    public static Object[][] createDataObject(Method m) throws Exception {
        testData = ExcelUtil.getExcelDataIn2DArray(System.getProperty("user.dir") + Constants.pathForTestData,Constants.loginDataFileName);

        int rows = testData.length;

        Object[][] signin_credentials = new Object[rows][3];

        for(int i=0;i<rows;i++)
        {
            if(m.getName().equalsIgnoreCase(testData[i][0])) {
                signin_credentials[i][0] = testData[i][0];
                signin_credentials[i][1] = testData[i][1];
                signin_credentials[i][2] = testData[i][2];
            }
        }
        return filterData(signin_credentials);
    }

    public static Object[][] filterData(Object[][] arr){
        String[][] nonEmptyArray = Arrays.stream(arr)
                .map(row -> Arrays.stream(row)
                        .filter(e -> e != null)
                        .toArray(String[]::new))
                .filter(row -> row.length > 0)
                .toArray(String[][]::new);
        return nonEmptyArray;
    }
}
