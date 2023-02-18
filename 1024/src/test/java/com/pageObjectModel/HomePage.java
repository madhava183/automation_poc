package com.pageObjectModel;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class HomePage {

protected String number;	
public WebDriver driver1;

@FindBy(id="hp-widget__sfrom")
public WebElement flightsFrom;

@FindBy(id="hp-widget__sTo")
public WebElement flightsTo;

@FindBy(id="searchBtn")
public WebElement flightSearchButton;

public HomePage(WebDriver driver){
	this.driver1=driver;
	PageFactory.initElements(driver1,this);
}

public void seachFlights(String strFrom,String strTo){
	try{
		Thread.sleep(5000);	
	flightsFrom.clear();
	flightsFrom.sendKeys(strFrom);
	Thread.sleep(5000);
	flightsTo.clear();
	flightsTo.sendKeys(strTo);
	flightSearchButton.click();	
	//return this;
	}catch(Exception e){
		System.out.println(e.getMessage());
	}
}
public String getTitle(){
	String title = driver1.getTitle();
	return title;
}


}
