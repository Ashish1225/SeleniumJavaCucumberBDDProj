package testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseClass;
import dataprovider.CustomDataProvider;
import pages.HomePage;
import pages.LoginPage;

public class LoginTest extends BaseClass {
	
	HomePage homePage;
	LoginPage loginPage;
	
	
	@Test(dataProvider = "LoginDataSet",dataProviderClass = CustomDataProvider.class,priority = 1)
	public void loginToApplication(String uname, String pass) {
		
		loginPage = new LoginPage(driver);
		homePage = loginPage.loginToApplication(uname, pass);
		//homePage = new HomePage(driver);
		Assert.assertTrue(homePage.getWelcomeMsg().contains("Welcome"), "WelCome message is not present");
		homePage.clickOnSignOut();
		Assert.assertTrue(loginPage.isSignInPresent());
	}
	
	
	
	
	@Test(dataProvider = "InvalidLoginDataSet",dataProviderClass = CustomDataProvider.class,priority = 2)
	public void inValidloginToApplication(String uname, String pass) {
		
		loginPage = new LoginPage(driver);
		loginPage.loginToApplicationWithInvalid(uname, pass);
		String actualErrorMsg = loginPage.getErrorMsg();
		
		if(actualErrorMsg.contains("Email and Password Doesn't match")) {
			Assert.assertTrue(true, "Email and Password Doesn't match error message is not displayed");
		}
		else if(actualErrorMsg.contains("USER Email Doesn't Exist")) {
			Assert.assertTrue(true, "USER Email Doesn't Exist error message is not displayed");
		}
		else if(actualErrorMsg.contains("Password is required")) {
			Assert.assertTrue(true, "Password is required error message is not displayed");
		}
		else if(actualErrorMsg.contains("Email is required")) {
			Assert.assertTrue(true, "Email is required error message is not displayed");
		}
	}
	
	@Test(dataProvider = "ValidAndInvalidLoginDataSet",dataProviderClass = CustomDataProvider.class,priority = 3)
	public void loginWithAllCombination(String status,String email, String pass) {
		loginPage = new LoginPage(driver);
		loginPage.loginToApplicationWithAllCombination(email, pass);
		if(status.equals("ValidCred")) {
			homePage = new HomePage(driver);
			Assert.assertTrue(homePage.getWelcomeMsg().contains("Welcome"), "WelCome message is not present");
			homePage.clickOnSignOut();
			Assert.assertTrue(loginPage.isSignInPresent());
		}
		else if(status.equals("BothInvalid")) {
			String errorMsg = loginPage.getErrorMsg();
			Assert.assertEquals(errorMsg, "USER Email Doesn't Exist","USER Email Doesn't Exist warning not displayed");
		}
		else if(status.equals("ValidEmailInvalidPass")) {
			String errorMsg = loginPage.getErrorMsg();
			Assert.assertEquals(errorMsg, "Email and Password Doesn't match","Email and Password Doesn't match warning not displayed");
		}
		else if(status.equals("InvalidEmailValidPass")) {
			String errorMsg = loginPage.getErrorMsg();
			Assert.assertEquals(errorMsg, "USER Email Doesn't Exist","USER Email Doesn't Exist warning not displayed");
		}
		else if(status.equals("BlankEmailValidPass")) {
			String errorMsg = loginPage.getErrorMsg();
			Assert.assertEquals(errorMsg, "Email is required","Email is required warning not displayed");
		}
		else if(status.equals("ValidEmailBlankPass")) {
			String errorMsg = loginPage.getErrorMsg();
			Assert.assertEquals(errorMsg, "Password is required","Password is required warning not displayed");
		}
	}

}
