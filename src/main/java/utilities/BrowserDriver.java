package utilities;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.SystemUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BrowserDriver {

	public static WebDriver driver = null;
	public static WebDriverWait waitVar = null;
	String baseUrl;
	
	public void browser(String browser) {
		
		System.out.println("***************************************");
		System.out.println("Launching Browser");
		String driverRelativePath = "";
		String chrome = "";
		try {
			if (SystemUtils.IS_OS_WINDOWS) {
				driverRelativePath = "win";
				chrome = "/chromedriver.exe";
			}
			else if (SystemUtils.IS_OS_LINUX) {
				driverRelativePath = "linux";
				chrome = "/chromedriver.exe";
			}
			
			if (browser.equals("chrome")) {
				File file = new File("browser_drivers/" + driverRelativePath + chrome);
				System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
				System.setProperty("webdriver.chrome.logfile", "browser_drivers/log/chromedriver.log");
				driver = new ChromeDriver();
				System.out.println("Using Chrome");
			}
			else if (browser.equals("firefox")) {
				driver = new FirefoxDriver();
				System.out.println("Using Firefox");
			}
			else if (browser.equals("ie")) {
				File file = new File("browser_drivers//" + driverRelativePath + "/IEDriverServer.exe");
				System.setProperty("webdriver.ie.driver", file.getAbsolutePath());
				driver = new InternetExplorerDriver();
				System.out.println("Using IE");
			}
			
			waitVar = new WebDriverWait(driver, 180);
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	
	public void goTo(String url) {
		try {
			baseUrl = url;
			driver.navigate().to(baseUrl);
			System.out.println("Web portal is: " + baseUrl);
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public void clearBrowserCache() {
		try {
			driver.manage().deleteAllCookies();
			System.out.println("Cleared browser cache");
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void shutDownBrowser() {
		try {
			driver.quit();
			System.out.println("Shutdown browser");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void closeBrowser() {
		try {
			driver.close();;
			System.out.println("Browser closed");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
}
