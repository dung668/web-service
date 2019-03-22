package edu.ptit.util;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class DatabaseProperties {
	
	static private Map<String, String> data = new HashMap<>();
	
	static {
		Properties prop = new Properties();
		try {
			prop.load(DatabaseProperties.class.getResourceAsStream("/database.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Enumeration<String> en = (Enumeration<String>) prop.propertyNames();
		
		while(en.hasMoreElements()) {
			String key = en.nextElement();
			data.put(key, prop.getProperty(key));
		}
	}
	
	public static String getData(String key) {
		String string = "";
		if(data.containsKey(key)) {
			string = data.get(key);
		}
		return string;
	}
	
}
