package dataprovider;

import org.testng.annotations.DataProvider;

public class CustomDataProvider {
	
	@DataProvider(name="LoginDataSet")
	public static Object[][] getData() {
		Object[][] data = ExcelReader.getDataFromSheet("loginCred");
		return data;
	}

}
