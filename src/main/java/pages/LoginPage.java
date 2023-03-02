package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import utils.Utility;

public class LoginPage {

	WebDriver driver;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
	}

	By userEmail = By.name("email1");
	By userPassword = By.name("password1");
	By loginBtn = By.xpath("//button[normalize-space()='Sign in']");
	
	By loginHeader = By.xpath("//h2[normalize-space()='Sign In']");
	
	By errorMsg = By.xpath("//h2[@class='errorMessage']");
	
	

	public HomePage loginToApplication(String username, String password) {
		driver.findElement(userEmail).sendKeys(username);
		driver.findElement(userPassword).sendKeys(password);
		driver.findElement(loginBtn).click();
		Utility.waitForSeconds(3);
		return new HomePage(driver);
	}
	
	public void loginToApplicationWithInvalid(String username, String password) {
		driver.findElement(userEmail).sendKeys(username);
		driver.findElement(userPassword).sendKeys(password);
		driver.findElement(loginBtn).click();
		Utility.waitForVisibilityOfElement(errorMsg, 5);
	}
	
	
	public boolean isSignInPresent() {
		return driver.findElement(loginHeader).isDisplayed();
	}
	
	public String getErrorMsg() {
		String errorMsgText = driver.findElement(errorMsg).getText();
		return errorMsgText;
	}
	
	public void loginToApplicationWithAllCombination(String username, String password) {
		driver.findElement(userEmail).sendKeys(username);
		driver.findElement(userPassword).sendKeys(password);
		driver.findElement(loginBtn).click();
		Utility.waitForSeconds(3);
		
	}
	

}
