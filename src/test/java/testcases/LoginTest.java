package testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseClass;
import dataprovider.CustomDataProvider;
import pages.HomePage;
import pages.LoginPage;

public class LoginTest extends BaseClass {
	
	
	@Test(dataProvider = "LoginDataSet",dataProviderClass = CustomDataProvider.class)
	public void loginToApplication(String uname, String pass) {
		
		LoginPage loginPage = new LoginPage(driver);
		loginPage.loginToApplication(uname, pass);
		HomePage homePage = new HomePage(driver);
		Assert.assertTrue(homePage.getWelcomeMsg().contains("Welcome"), "WelCome message is not present");
		homePage.clickOnSignOut();
		Assert.assertTrue(loginPage.isSignInPresent());
	}

}
