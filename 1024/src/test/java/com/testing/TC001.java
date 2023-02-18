package com.testing; 

import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import reusableComponents.Excel;

import com.common.BaseTest;
import com.pageObjectModel.HomePage;

public class TC001 extends BaseTest {
	HomePage homePage ;
	public WebDriver driver;
	public Excel excel;
	
	
  @BeforeTest
  public void setup() throws IOException{
	  driver =initilize("TC001","CHROME",driver );
	  excel =new Excel(System.getProperty("user.dir")+"\\src\\test\\java\\testData\\TestScripts.xlsx","TestData","TC001");
      driver.get(excel.getData("URL"));
      homePage = new HomePage(driver);
	 
  }
	
  @Test
  public void TC1() throws IOException {	 
	  //String str = RandomStringUtils.randomAlphabetic(10);
	 
	//2016-11-16 06:43:19.77

	  System.out.println();
	  
	  System.out.println(homePage.getTitle());
	  System.out.println(excel.getData("Assertion_Text1"));
	  if(homePage.getTitle().contains(excel.getData("Assertion_Text1"))){
		  Assert.assertTrue(true,"Navigated to homePage");		  
	  }else{
		  Assert.assertTrue(false,"Issue Navigating to homePage");
	  }
	  
	  try {
		Thread.sleep(5000);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  driver.findElement(By.id("hp-widget__sfrom")).click();;
	  driver.findElement(By.id("hp-widget__sfrom")).clear();
	  try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  
	  driver.findElement(By.id("hp-widget__sfrom")).sendKeys("Srinagar (SXR)");;
	  try {
		Thread.sleep(5000);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  
	  
	  String strFrom =excel.getData("FlightFrom");
	  String strTo =excel.getData("FlightTo");
	  System.out.println(strFrom  + strTo);
      homePage.seachFlights(strFrom,strTo);

	 // Assert.assertEquals(homePage.getTitle(),excel.getData("Assertion_Text2"));
  }

  @AfterTest
  public void tearDown(){
	  driver.quit();
  }
  
}
