package com.testing;

import org.testng.Reporter;
import org.testng.annotations.Test;

public class TC005 {
	
	@Test
	public void sample(){
		Reporter.log("Sample Line 1;PASS;//");
		Reporter.log("Sample Line 2;INFO;//");
		Reporter.log("Sample Line 3;INFO//");
		Reporter.log("Sample Line 4;INFO//");
		Reporter.log("Sample Line 5;INFO//");
	}
	
	@Test
	public void sample1(){
		Reporter.log("Sample Line 6");
		Reporter.log("Sample Line 7");
		Reporter.log("Sample Line 8");
		Reporter.log("Sample Line 9");
		Reporter.log("Sample Line 10");
	}
}
