package dataprovider;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigurationReader {

	public static String getProperty(String key) {
		File file = new File(System.getProperty("user.dir") + "//config//config.properties");
		Properties property = new Properties();
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(file);
			property.load(fis);
		} catch (FileNotFoundException e) {
			System.out.println("Could not find the file :"+e.getMessage());
		} catch (IOException e) {
			System.out.println("Could not load the file :"+e.getMessage());
		}
		
		String value = property.getProperty(key);
		return value;
	}

}
