package utilities;

import base.Driver;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;
import java.io.File;
import java.io.IOException;


public class TestListener implements ITestListener {
    @Override
    public void onTestFailure(ITestResult testResult){
        File scrFile = ((TakesScreenshot) Driver.driver).getScreenshotAs(OutputType.FILE);
        File screenshot =  new File("errorScreenshots/" + testResult.getName() + ".jpg");
        try {
            FileUtils.copyFile(scrFile,screenshot);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String DestFile = System.getProperty("user.dir") + "/"+screenshot;
        Reporter.log("</br><font color='#73a9d0'>***************Screenshot Of the error****************</font>");
        Reporter.log("</br><img id='ErrorResult' src='" + DestFile + "' style='width:600px'/>");
    }
}
