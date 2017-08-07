package pageobjects;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import utilities.BrowserDriver;



public class Office365LoginPage extends BrowserDriver{

	// locator for Office 365 login page
	By office365Page = By.id("login_cta_text");
	By username = By.id("cred_userid_inputtext");
	By password = By.id("cred_password_inputtext");
	By signIn = By.id("cred_sign_in_button");
	
	By signBackIn = By.id("hero-banner-sign-back-in-to-office-365-link");
	By headLine = By.className("headline");

	public void verifyOfficeLoginPage(WebDriver driver) {
		try {
			waitVar.until(ExpectedConditions.presenceOfElementLocated(office365Page));
			assertEquals("Work or school, or personal Microsoft account", driver.findElement(office365Page).getText());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void enterUserID(WebDriver driver, String userid) {
		driver.findElement(username).sendKeys(userid);
	}
	
	public void enterUserPassword(WebDriver driver, String userpwd) {
		driver.findElement(password).sendKeys(userpwd);
	}
	
	public void clickSignIn(WebDriver driver) {
		driver.findElement(signIn).click();
	}
	
	public void confirmLoggedOut(WebDriver driver) {
		try {
			waitVar.until(ExpectedConditions.presenceOfElementLocated(signBackIn));
			String t_string = driver.findElement(headLine).getText();
			assertEquals("you're signed out now.", t_string.substring(t_string.lastIndexOf(",") + 1).trim());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
