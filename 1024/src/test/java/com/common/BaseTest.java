package com.common;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import reusableComponents.Excel;

public class BaseTest {

	public String number;
	public WebDriver driver1;
	// public Excel excel;

	public WebDriver initilize(String number, String strBrowserType,WebDriver driver) {
		this.driver1=driver;
		this.number = number;
		System.out.println(number);
		if (strBrowserType.equalsIgnoreCase("CHROME")) {
			System.setProperty("webdriver.chrome.driver",
			System.getProperty("user.dir") + "\\src\\test\\resources\\chromedriver.exe");
			driver1 = new ChromeDriver();
		}
		return driver1;

	}

	@BeforeClass
	public void beforeClass() {
		System.out.println("Before class ");
	}

	@BeforeSuite
	public void beforeSuite() {
		System.out.println("Before suite ");
	}

	@AfterSuite
	public void afterSuite() {
		System.out.println("After suite ");
	}

	@AfterClass
	public void afterClass() {
		System.out.println("After class ");
	}

	@BeforeMethod
	public void beforeMethod() {
		System.out.println("Before Method ");
	}

	@AfterMethod
	public void afterMethod() {
		System.out.println("After Method ");
	}

}
