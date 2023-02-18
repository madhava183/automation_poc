package com.testing;

import java.net.URI;
import java.sql.Timestamp;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;

import com.common.BaseTest;


import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class TC003 {
	
	
		 
		/*  public static final String USERNAME = "hemanthkrishna1821@gmail.com";
		  public static final String ACCESS_KEY = "17bdd28a-3515-4562-8e02-f4350e7fa498";
		  public static final String URL = "https://" + USERNAME + ":" + ACCESS_KEY + "@ondemand.saucelabs.com:80/wd/hub";
		 
		  public static void main(String[] args) throws Exception {
		 
		    DesiredCapabilities caps = DesiredCapabilities.chrome();
			//   DesiredCapabilities caps = DesiredCapabilities.internetExplorer();  
		       caps.setCapability("platform", "Windows 10");
		    //caps.setCapability("version", "55.0");
		   // caps.setCapability("version", "11.103");
		    caps.setCapability("name", "First Test");
		    caps.setCapability("tags", "creating");
		    caps.setCapability("build", "build-1234");
   		    
		    WebDriver driver = new RemoteWebDriver(caps);
		 
		    *//**
		     * Goes to Sauce Lab's guinea-pig page and prints title
		     *//*
		 
		    driver.get("https://google.com");
		    System.out.println("title of page is: " + driver.getTitle());
		 
		    driver.quit();
		  }*/
	
	

//	public class App {
	  public static void main(String[] args) {

		Properties prop = new Properties();
		InputStream input = null;

		try {

			input = new FileInputStream(System.getProperty("user.dir")+"/src/test/resources/config.properties");

			// load a properties file
			prop.load(input);

			// get the property value and print it out
			System.out.println(prop.getProperty("database"));
			System.out.println(prop.getProperty("dbuser"));
			System.out.println(prop.getProperty("dbpassword"));

		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	  }
//	}

		
}
