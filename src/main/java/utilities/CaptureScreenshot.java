package utilities;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;

public class CaptureScreenshot {
	
	DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
	
	public void failedResultMethod(WebDriver driver, ITestResult testResult) throws Exception {
		String failedMethod = testResult.getName().toString().trim();
		Date date = new Date();
		String screenshot_failedMethod = failedMethod + dateFormat.format(date);
		if(ITestResult.FAILURE == testResult.getStatus()) {
			File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(scrFile, new File(VariablesInstance.SCREENSHOTS.variable_string() + screenshot_failedMethod + ".png"));
		}

	}

}