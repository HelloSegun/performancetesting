package testsuites;

import org.testng.annotations.Test;

import pageobjects.Office365LoginPage;
import pageobjects.OfficeHome;
import pageobjects.PlanitSingleSignOnPage;
import utilities.VariablesInstance;
import utilities.BaseStopwatch;
import utilities.ConnectToInfluxDB;

//import org.influxdbSegun.ConnectToInfluxDB;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

public class LoginLogout extends BeforeAfterSuiteSetup{
	
	private double loggingInTime;
	private double loggingOutTime;
	
	//Screenshot screenshot = new Screenshot();
	Office365LoginPage login = new Office365LoginPage();
	OfficeHome home = new OfficeHome();
	PlanitSingleSignOnPage s_signon = new PlanitSingleSignOnPage();
	BaseStopwatch time_elapsed = new BaseStopwatch();


  @Test (priority=0, invocationCount=5)
  public void logInAndOutOfOffice() throws InterruptedException {
	  try {
		  System.out.println(VariablesInstance.CHROME.variable_string());
		  browser(VariablesInstance.CHROME.variable_string());
		  clearBrowserCache();
		  goTo(VariablesInstance.OFFICE365.variable_string());
	  } catch (Exception e) {
		  e.printStackTrace();
		  Assert.fail();
	  }
	  try {
		  System.out.println("logging in");
		  login.verifyOfficeLoginPage(driver);
		  login.enterUserID(driver, VariablesInstance.USERID.variable_string());
		  login.enterUserPassword(driver, VariablesInstance.USERPWD.variable_string());
		  login.clickSignIn(driver);
		  time_elapsed.startTime();
		  driver.getTitle();
		  
		  int page_id = 0;
		  if (driver.getTitle().equals("Microsoft Office Home")) {
			  page_id = Integer.valueOf(VariablesInstance.OFFICEHOME.variable_string());
		  } else if (driver.getTitle().equals("Sign In")) {
			  page_id = Integer.valueOf(VariablesInstance.OFFICESIGNIN.variable_string());
		  }
		  /*
		  switch(page_id) {
		  	case 1:
		  		home.verifyOfficeHomePage(driver);
		  		break;
		  	case 2:
		  		s_signon.verifyOfficeLoginPage(driver);
		  		s_signon.enterUserPassword(driver, VariablesInstance.USERPWD.variable_string());
		  		s_signon.clickSignIn(driver);
		  		home.verifyOfficeHomePage(driver);
		  		break;
		  	default:
		  		throw new Exception("Either page is not displaying");
		  }
		  */
		  home.verifyOfficeHomePage(driver);
		  this.loggingInTime = time_elapsed.elapsedTime();
		  System.out.println(loggingInTime + " sec");
	  } catch (Exception e) {
		  e.printStackTrace();
		  Assert.fail();
	  }
	  
	  try {
		  System.out.println("logging out");
		  home.signingOut(driver);
		  time_elapsed.startTime();
		  login.confirmLoggedOut(driver);
		  time_elapsed.elapsedTime();
		  this.loggingOutTime = time_elapsed.elapsedTime();
		  System.out.println(loggingOutTime + " sec");    
	  } catch (Exception e) {
		  e.printStackTrace();
		  Assert.fail();
	  }
	  
	  try {
		  ConnectToInfluxDB conn_influx;
		  conn_influx = new ConnectToInfluxDB(VariablesInstance.DBNAME.variable_string(), VariablesInstance.MEASURE.variable_string(),
				  VariablesInstance.TAG.variable_string(), VariablesInstance.TAGVALUE.variable_string(), VariablesInstance.ADDFIRST.variable_string(),
				  VariablesInstance.ADDSECOND.variable_string(), loggingInTime, loggingOutTime);
		  conn_influx.connectToInfluxDB();
		  conn_influx.writeIntoDatabase2();
		  //shutDownBrowser();
	  } catch (Exception e) {
		  e.printStackTrace();
		  Assert.fail();
	  }
	  
	  shutDownBrowser();
	  
	  //Thread.sleep(10000);
  }
 /* 
  @Test (priority=1)
  public void logOutOfOffice() {
	  try {
		  System.out.println("logging out");
		  home.signingOut(driver);
		  time_elapsed.startTime();
		  login.confirmLoggedOut(driver);
		  time_elapsed.elapsedTime();
		  this.loggingOutTime = time_elapsed.elapsedTime();
		  System.out.println(loggingOutTime + " sec");
	  } catch (Exception e) {
		  e.printStackTrace();
		  Assert.fail();
	  }
  }
*/
  /*
  @Test
  public void f() {
	  try {
		  
	  } catch (Exception e) {
		  e.printStackTrace();
		  Assert.fail();
	  }
  }
  */
  
  @AfterMethod
  public void takeScreenshot(ITestResult result) {
	  try {
		  screenshot.failedResultMethod(driver, result);;
	  } catch (Exception e) {
		  e.printStackTrace();
		  Assert.fail();
	  }
  }

  @BeforeTest
  public void beforeTest() {
	  try {
		  System.out.println("beforeTest");
		  /*
		  System.out.println(VariablesInstance.CHROME.variable_string());
		  browser(VariablesInstance.CHROME.variable_string());
		  clearBrowserCache();
		  goTo(VariablesInstance.OFFICE365.variable_string());
		  */
	  } catch (Exception e) {
		  e.printStackTrace();
		  Assert.fail();
	  }
  }

  @AfterTest
  public void afterTest() {
	  try {
		  /*
		  System.out.println("afterTest");
		  ConnectToInfluxDB conn_influx;
		  conn_influx = new ConnectToInfluxDB(VariablesInstance.DBNAME.variable_string(), VariablesInstance.MEASURE.variable_string(),
				  VariablesInstance.TAG.variable_string(), VariablesInstance.TAGVALUE.variable_string(), VariablesInstance.ADDFIRST.variable_string(),
				  VariablesInstance.ADDSECOND.variable_string(), loggingInTime, loggingOutTime);
		  conn_influx.connectToInfluxDB();
		  conn_influx.writeIntoDatabase2();
		  shutDownBrowser();
		  */
	  } catch (Exception e) {
		  e.printStackTrace();
		  Assert.fail();
	  }
  }

}
