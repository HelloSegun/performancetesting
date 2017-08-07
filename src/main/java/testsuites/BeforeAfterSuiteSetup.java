package testsuites;

import utilities.BrowserDriver;
import utilities.CaptureScreenshot;

import org.testng.annotations.BeforeSuite;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;

public class BeforeAfterSuiteSetup extends BrowserDriver {
 
	CaptureScreenshot screenshot = new CaptureScreenshot();
	
  @BeforeSuite
  public void beforeSuite() {
	  try {
			System.out.println("beforeSuite");
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
  }

  @AfterSuite
  public void afterSuite() {
		try {
			System.out.println("afterSuite");
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
  }

}
