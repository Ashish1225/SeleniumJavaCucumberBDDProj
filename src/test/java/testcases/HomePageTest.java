package testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseClass;
import dataprovider.CustomDataProvider;
import pages.CreateCoursePage;
import pages.HomePage;
import pages.LoginPage;

public class HomePageTest extends BaseClass{
	HomePage homePage;
	LoginPage loginPage;
	CreateCoursePage createCoursePage;
	
	@Test(dataProvider = "LoginDataSet",dataProviderClass = CustomDataProvider.class,priority = 1)
	public void loginWithValidCredTest(String userEmail,String pass) {
		loginPage = new LoginPage(driver);
		homePage = loginPage.loginToApplication(userEmail, pass);
		String welcomeMsg = homePage.getWelcomeMsg();
		Assert.assertTrue(welcomeMsg.contains("Welcome"),"Welcome Message is not displayed");
	}
	
	@Test(priority = 2)
	public void clickOnManageCourseAndVerifyManageHeadingTest() {
		homePage = new HomePage(driver);
		homePage.clickOnManageCourseDropDown();
		String actualText = homePage.getManageCourseText();
		Assert.assertTrue(actualText.contains("Manage Courses"), "Manage Courses heading is not displaying");
	}
	
	@Test(priority = 3)
	public void clickOnAddCourseTest() {
		homePage = new HomePage(driver);
		homePage.clickOnAddNewCourseBtn();
		createCoursePage= new CreateCoursePage(driver);
		boolean statusOfTitle = createCoursePage.verifyAddCourseTitle();
		Assert.assertTrue(statusOfTitle,"Add New Course title is not displayed");
	}
	
	@Test(priority = 4)
	public void addCourseTestAndVerifyTest() throws Exception {
		createCoursePage= new CreateCoursePage(driver);
		boolean statusOfCourseName = createCoursePage.enterCourseDetails("Java", "This is learning", "Mukesh", "10000", "22", "25", "Automation Testing");
		Assert.assertTrue(statusOfCourseName,"Course Name is not displayed");
	}
	
	
	@Test(priority = 5)
	public void deleteAddedCourseAndVerifyTest() {
		homePage =new HomePage(driver);
		boolean statusOfDeletedCourse = homePage.deleteCourse("Java");
		Assert.assertTrue(statusOfDeletedCourse,"Course is not deleted..");
	}
	
	@Test(priority = 6)
	public void logOutTest() {
		homePage =new HomePage(driver);
		homePage.clickOnSignOut();
		loginPage = new LoginPage(driver);
		boolean statusOfSignInHeader = loginPage.isSignInPresent();
		Assert.assertTrue(statusOfSignInHeader,"Sign Out Failed- Sign In header is not displayed..");
	}

}
