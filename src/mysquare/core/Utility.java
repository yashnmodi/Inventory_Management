package mysquare.core;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;

public class Utility {

	InputStream inputStream;

	public HashMap<String, String> getProperties() throws IOException {
		Properties prop = new Properties();
		HashMap<String, String> properties = new HashMap<String, String>();

		try {
			FileInputStream ip = new FileInputStream("C:/ims_files/config.properties");
			if (ip != null) {
				prop.load(ip);
				System.out.println(System.getProperty("os.name"));
			} else {
				throw new FileNotFoundException("Property file not found.");
			}

			properties.put("dbSource", prop.getProperty("DB_PATH"));
			properties.put("dbDriver", prop.getProperty("DB_DRIVER"));

		} catch (Exception e) {
			System.out.println("Exception: " + e);
		}
		return properties;
	}
}
