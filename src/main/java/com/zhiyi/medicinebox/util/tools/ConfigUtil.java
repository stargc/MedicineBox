package com.zhiyi.medicinebox.util.tools;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigUtil {
	private static Properties prop = new Properties();
	static {
		try {
			InputStream in = new FileInputStream(ConfigUtil.class
					.getClassLoader().getResource("").getPath()
					+ "/properties/config.properties");
			System.out.println();
			prop.load(in);
			in.close();
		} catch (IOException e) {
			throw new RuntimeException(
					"A error happens when reading config.properties", e);
		}
	}

	public static String getValue(String key) {
		return prop.getProperty(key);
	}
}
