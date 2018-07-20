package com.ebay.test;

import org.openqa.selenium.Dimension;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.ebay.pagobjects.Ebay_Search;
import com.loylty.automationframework.common.TestClassUtil;
import com.loylty.automationframework.util.APP;
import com.loylty.automationframework.util.impl.DefaultDriverManager;
import com.loylty.businessobject.TestCase;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;


public class Ebay_Test extends TestClassUtil
{
	final static Logger log=Logger.getLogger(Ebay_Test.class);
	DefaultDriverManager drivermanager = null;
	AppiumDriver driver = null;
	
	
	
	
	@Test  (dataProvider = "testObjDP")
	public void login_ParkNow(TestCase testcase) throws IOException, SQLException, InterruptedException
	{
		driver = new DefaultDriverManager().getMobileDriver(APP.EBAY);
		
		Ebay_Search searchpo=new Ebay_Search(driver);
		
		
		Dimension dSize = driver.manage().window().getSize();
		int startx = dSize.width / 2;
		int starty = (int) (dSize.height * 0.10);
		int endy=(int) (dSize.height * 0.60);
		
	
		
		searchpo.enter_ProductToSearch(driver, "Cycle");
		
		searchpo.click_On_firstProduct(driver);

		driver.swipe(startx, endy, startx, starty, 3000);
		searchpo.clickon_ProductDetailsPage(driver);
		searchpo.clickon_BuyNowBtn(driver);
	
		
		
	}
	
	
	
	
	
	
	@AfterMethod
	public void teardown()
	{
		driver.quit();
	}
	

}
