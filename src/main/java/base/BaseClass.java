package base;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import browserfactory.BrowserFactory;
import dataprovider.ConfigurationReader;

public class BaseClass {

	public WebDriver driver;

	@BeforeMethod
	public void setUp() {

		// Approach 1- Config - it does not work for cross browser approach
		driver = BrowserFactory.startBrowser(ConfigurationReader.getProperty("browserName"),
				ConfigurationReader.getProperty("url"));

	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}
