package com.servicenow.base;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.FileSystemNotFoundException;
import java.util.Properties;

public class TestBase {
	
	public Properties prop;
	
	public TestBase() {
		try {
			prop= new Properties();
			FileInputStream ip= new FileInputStream(System.getProperty("user.dir")+"/src/main/java/com/servicenow/config/config.properties");
			prop.load(ip);
		}catch(FileSystemNotFoundException e) {
			e.printStackTrace();			
		}catch(IOException e){
			e.printStackTrace();
	}

}
}
