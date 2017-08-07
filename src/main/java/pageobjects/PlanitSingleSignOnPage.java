package pageobjects;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import utilities.BrowserDriver;

public class PlanitSingleSignOnPage extends BrowserDriver{

	// locator for Planit Single Sign-On page
	By SingleSignOnPage = By.id("header");
	By userName = By.id("userNameInput");
	By passWord = By.id("passwordInput");
	By Sign_in_btn = By.id("submitButton");
	
	
	public void verifyOfficeLoginPage(WebDriver driver) {
		try {
			waitVar.until(ExpectedConditions.presenceOfElementLocated(SingleSignOnPage));
			assertEquals("Work or school, or personal Microsoft account", driver.findElement(SingleSignOnPage).getText());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void enterUserID(WebDriver driver, String userid) {
		driver.findElement(userName).sendKeys(userid);
	}
	
	public void enterUserPassword(WebDriver driver, String userpwd) {
		driver.findElement(passWord).sendKeys(userpwd);
	}
	
	public void clickSignIn(WebDriver driver) {
		driver.findElement(Sign_in_btn).click();
	}
}
