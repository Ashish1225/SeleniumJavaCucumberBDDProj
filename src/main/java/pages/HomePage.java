package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class HomePage {
	
	WebDriver driver;
	Actions action;
	
	public HomePage(WebDriver driver) {
		this.driver = driver;
	}
	
	
	By toggleBtn= By.xpath("//span[@class='navbar-toggler-icon']");
	By signOutBtn = By.xpath("//button[normalize-space()='Sign out']");
	By welcomeMsg = By.xpath("//h4");
	
	By manageBtn = By.xpath("//span[normalize-space()='Manage']");
	By manageCourseBtn = By.xpath("//span[text()='Manage Courses']");
	By manageCourseHeader = By.xpath("//h1[text()='Manage Courses']");
	
	By addNewCourseBtn = By.xpath("//button[text()='Add New Course ']");
	
	
	public void clickOnSignOut() {
		driver.findElement(toggleBtn).click();
		driver.findElement(signOutBtn).click();
	}
	
	public String getWelcomeMsg() {
		return driver.findElement(welcomeMsg).getText();
	}
	
	public void clickOnManageCourseDropDown() {
		action = new Actions(driver);
		WebElement manageTab = driver.findElement(manageBtn);
		action.moveToElement(manageTab).perform();
		driver.findElement(manageCourseBtn).click();
	}
	
	public String getManageCourseText() {
		action = new Actions(driver);
		WebElement manageCourseHeaderText = driver.findElement(manageCourseHeader);
		action.moveToElement(manageCourseHeaderText).perform();
		String manageText = manageCourseHeaderText.getText();
		return manageText;
	}
	
	public void clickOnAddNewCourseBtn() {
		driver.findElement(addNewCourseBtn).click();
	}

}
