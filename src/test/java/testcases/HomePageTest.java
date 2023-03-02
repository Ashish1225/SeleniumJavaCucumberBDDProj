package testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseClass;
import dataprovider.CustomDataProvider;
import pages.HomePage;
import pages.LoginPage;

public class HomePageTest extends BaseClass{
	HomePage homePage;
	LoginPage loginPage;
	
	@Test(dataProvider = "LoginDataSet",dataProviderClass = CustomDataProvider.class,priority = 1)
	public void loginWithValidCred(String userEmail,String pass) {
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

}
