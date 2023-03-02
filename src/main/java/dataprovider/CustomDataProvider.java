package dataprovider;

import org.testng.annotations.DataProvider;

public class CustomDataProvider {
	
	@DataProvider(name="LoginDataSet")
	public static Object[][] getData() {
		Object[][] data = ExcelReader.getDataFromSheet("loginCred");
		return data;
	}
	
	@DataProvider(name="InvalidLoginDataSet")
	public static Object[][] getDataForInvalid() {
		Object[][] data = ExcelReader.getDataFromSheet("inValidloginCred");
		return data;
	}
	
	@DataProvider(name="ValidAndInvalidLoginDataSet")
	public static Object[][] getDataForValidAndInvalid() {
		Object[][] data = ExcelReader.getDataFromSheet("loginCredMultiway");
		return data;
	}

}
