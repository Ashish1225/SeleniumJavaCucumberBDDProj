package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class CreateCoursePage {
	
	WebDriver driver ;
	
	public CreateCoursePage(WebDriver driver) {
		this.driver = driver;
	}
	
	By addCourseTitle = By.xpath("//div[contains(@class,'title modal-title h4')]");
	
	By thumbnail = By.id("thumbnail");
	By courseNameField = By.id("name");
	By descriptionField = By.id("description");
	By instructorField = By.id("instructorNameId");
	By priceField = By.id("price");
	By startDateField = By.name("startDate");
	By endDateField = By.name("endDate");
	By selectCategoryField = By.xpath("//div[text()='Select Category']");
	By categoryItems = By.xpath("//div[contains(@class,'menu-items')]/button");
	By saveBtn = By.xpath("//button[text()='Save']");
	
	By month_yearField = By.xpath("//div[contains(@class,'react-datepicker__current-month')]");
	
	
	public boolean verifyAddCourseTitle() {
		boolean status = driver.findElement(addCourseTitle).isDisplayed();
		return status;
	}
	

	public boolean enterCourseDetails(String courseName,String description,String instructorName,String price,String strtDate,String endDate,String categoryName) throws Exception {
		driver.findElement(thumbnail).sendKeys("D:\\HybridTestNGFramework\\HybridFramework\\testdata\\imgae1.png");
		driver.findElement(courseNameField).sendKeys(courseName);
		driver.findElement(descriptionField).sendKeys(description);
		driver.findElement(instructorField).sendKeys(instructorName);
		driver.findElement(priceField).sendKeys(price);
		driver.findElement(startDateField).click();
		
		String startMonth = getMonthText(month_yearField);
		
		valiateDateMonth(strtDate,startMonth);

		try {
			driver.findElement(By.xpath("//div[@role='listbox']//div[contains(@aria-label,'"+startMonth+"') and @aria-disabled='false' and text()='"+strtDate+"']")).click();
		}
		catch (Exception e) {
			throw new Exception("Invalid Date :"+startMonth+ "::"+strtDate);
		}
		
		driver.findElement(endDateField).click();
		
		String endMonth = getMonthText(month_yearField);
		
		valiateDateMonth(endDate,endMonth);
		try {
			driver.findElement(By.xpath("//div[@role='listbox']//div[contains(@aria-label,'"+endMonth+"') and @aria-disabled='false' and text()='"+endDate+"']")).click();
		}
		catch (Exception e) {
			throw new Exception("Invalid Date :"+endMonth+ "::"+strtDate);
		}
		
		driver.findElement(selectCategoryField).click();
		
		try {
			driver.findElement(By.xpath("//div[@class='menu-items']//button[text()='"+categoryName+"']")).click();
		}catch (Exception e) {
			throw new Exception("Course Name is not present"+ e.getMessage());
		}
		
		driver.findElement(saveBtn).click();
		
		boolean statusOfCourseName = verifyAddedCourse(courseName);
		return statusOfCourseName;
	
	}
	
	
	public boolean verifyAddedCourse(String courseNameSearch) {
		boolean status = driver.findElement(By.xpath("//td[normalize-space()='"+courseNameSearch+"']")).isDisplayed();
		return status;
	}
	
	
	public void valiateDateMonth(String expDay, String expMonth) {
		
		if(expMonth.contains("February")&&Integer.parseInt(expDay)>29) {
			System.out.println("Wrong Date :"+ expMonth+ ":: "+expDay);
			return;
		}
		if(Integer.parseInt(expDay)>31) {
			System.out.println("Wrong Date :"+ expMonth+ ":: "+expDay);
			return;
		}
		
	}
	
	public String getMonthText(By locator) {
		WebElement monthYearElement = driver.findElement(locator);
		String monthYearText = monthYearElement.getText().trim();
		String[] monthYearArray = monthYearText.split(" ");
		return monthYearArray[0];
	}
	
	

}
