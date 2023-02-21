package listeneres;

import java.io.File;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import utils.Utility;

public class ExtentManager {
	
	public static ExtentReports extentReport;
	
	public static ExtentReports getInstance() {
		if(extentReport==null) {
			extentReport = createInstance();
			return extentReport;
		}
		else {
			return extentReport;
		}
	}
	
	
	public static ExtentReports createInstance() {
		
		File file = new File(System.getProperty("user.dir")+"//reports//AutomationReport_"+Utility.getCustomDateFormat()+".html");
		
		ExtentSparkReporter sparkReporter = new ExtentSparkReporter(file);
		sparkReporter.config().setDocumentTitle("Sprint 1 Report");
		sparkReporter.config().setTheme(Theme.DARK);
		sparkReporter.config().setReportName("Automation Report");
		
		ExtentReports extentReport = new ExtentReports();
		extentReport.attachReporter(sparkReporter);
		return extentReport;
		
	}
}
