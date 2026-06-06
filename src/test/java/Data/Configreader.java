package Data;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class Configreader {
	
	private final String propertiesFilePath = "Config//Configuration.properties";
	private static Properties properties;
	
	public Configreader() {
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(propertiesFilePath));
			properties = new Properties();
			try {
				properties.load(reader);
				reader.close();
			}catch(IOException e){
				e.printStackTrace();
			}
		}catch(FileNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException("File not found in the path "+propertiesFilePath);
		}
	}
	
	public String getUrl() {
		String url = properties.getProperty("url");
		return url;
	}
	
	public String getUserName() {
		String username = properties.getProperty("username");
		return username;
	}
	
	public String getPassword() {
		String password = properties.getProperty("password");
		return password;
	}
	
	public String getInvalidUserName() {
		String username = properties.getProperty("invaliduser");
		return username;
	}

}
