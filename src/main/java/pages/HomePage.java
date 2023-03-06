package pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

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
	By manageCourseBtn = By.xpath("//a[text()=' Manage Courses']");
	By manageCourseHeader = By.xpath("//h1[text()='Manage Courses']");
	
	By addNewCourseBtn = By.xpath("//button[text()='Add New Course ']");
	
	By courseNameListLocator = By.xpath("//tbody//td[2]");
	
	
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
	
	public boolean deleteCourse(String courseName) {
		List<WebElement> allCourseList = driver.findElements(courseNameListLocator);
		WebElement courseSearchElement = driver.findElement(By.xpath("//td[normalize-space()='"+courseName+"']"));
		for(WebElement courseListElement : allCourseList) {
			String courseNameText = courseListElement.getText();
			if(courseNameText.equals(courseName)) {
				driver.findElement(By.xpath("//td[normalize-space()='"+courseNameText+"']/preceding-sibling::td//input")).click();
				WebElement deleteCourse = driver.findElement(By.xpath("//td[normalize-space()='"+courseNameText+"']/preceding-sibling::td//input/parent::div/parent::td//following-sibling::td/button"));
				deleteCourse.click();
				WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
				wait.until(ExpectedConditions.invisibilityOf(courseSearchElement));
				break;
			}			
		}
		boolean status =driver.findElements(By.xpath("//td[normalize-space()='"+courseName+"']")).isEmpty();
		/*
		boolean flag = false;
		if(driver.findElements(By.xpath("//td[normalize-space()='"+courseName+"']")).isEmpty()) {
			flag =true;
		}else {
			flag = false;
		}
		*/
		return status;
	}

}
