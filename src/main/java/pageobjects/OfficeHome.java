package pageobjects;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import utilities.BrowserDriver;


public class OfficeHome extends BrowserDriver {

	// locator for Office home page
	By officeHomePage = By.id("ShellMail_link");
	By myAccountButton = By.id("O365_MeFlexPane_ButtonID");
	//By myAccountButton = By.xpath("//*[@id=\"O365_MeFlexPane_ButtonID\"]/div[1]/div[1]");
	////*[@id="O365_MeFlexPane_ButtonID"]/div[1]/div[1]
	//By myAccountButton = By.cssSelector("#O365_MeFlexPane_ButtonID > div.o365cs-mfp-header > div.o365cs-mfp-header-displayname.o365cs-rsp-tn-hide.o365cs-mfp-header-displayname-wrap");
	//By.id("O365_MeFlexPane_ButtonID");
	By myAccountSignOut = By.id("_ariaId_10");
	
	public String verifyOfficeHomePage(WebDriver driver) {
		try {
			waitVar.until(ExpectedConditions.presenceOfElementLocated(officeHomePage));
			assertEquals("Go to your email", driver.findElement(officeHomePage).getAttribute("aria-label"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "Go to your email";
	}
	
	public void signingOut(WebDriver driver) {
		//waitVar.until(ExpectedConditions.visibilityOfElementLocated(myAccountButton)).click();
		driver.findElement(myAccountButton).click();
		driver.findElement(myAccountSignOut).click();
	}
}
