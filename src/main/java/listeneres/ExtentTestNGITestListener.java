package listeneres;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;

import browserfactory.BrowserFactory;
import utils.Utility;

public class ExtentTestNGITestListener implements ITestListener{
	
	ExtentReports extentReport = ExtentManager.getInstance();
	
	ThreadLocal<ExtentTest> parentThread = new ThreadLocal<>();
	
	

	@Override
	public void onTestStart(ITestResult result) {
		ExtentTest extentTest = extentReport.createTest(result.getMethod().getMethodName());
		parentThread.set(extentTest);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		parentThread.get().pass("Test Passed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		WebDriver driver= BrowserFactory.getBrowserInstance();
		String base64 = Utility.captureScreenShotInBase64(driver);
		parentThread.get().fail("Test Failed" + result.getThrowable().getMessage(),MediaEntityBuilder.createScreenCaptureFromBase64String(base64).build());
		
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		parentThread.get().skip("Test Skipped "+result.getThrowable().getMessage());
	}

	@Override
	public void onFinish(ITestContext context) {
		extentReport.flush();
	}
	

}
