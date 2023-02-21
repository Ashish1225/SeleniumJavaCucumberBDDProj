package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {

	WebDriver driver;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
	}

	By userEmail = By.name("email1");
	By userPassword = By.name("password1");
	By loginBtn = By.xpath("//button[normalize-space()='Sign in']");
	
	By loginHeader = By.xpath("//h2[normalize-space()='Sign In']");

	public void loginToApplication(String username, String password) {
		driver.findElement(userEmail).sendKeys(username);
		driver.findElement(userPassword).sendKeys(password);
		driver.findElement(loginBtn).click();
		// return new DashboardPage(driver);
	}
	
	public boolean isSignInPresent() {
		return driver.findElement(loginHeader).isDisplayed();
	}

}
