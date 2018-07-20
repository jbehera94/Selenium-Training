package com.ebay.pagobjects;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.AppiumDriver;

public class Ebay_Search 
{
	AppiumDriver driver = null;
	
	
	final static Logger log=Logger.getLogger(Ebay_Search.class);
	

	public Ebay_Search(AppiumDriver driver) 
	{
		// TODO Auto-generated constructor stub
		this.driver = driver;
		PageFactory.initElements(driver, this);
		

	}

	@FindBy(id = "com.ebay.mobile:id/search_box")
	private  WebElement searchtxtbox;

	public  WebElement search_Box() 
	{
		return searchtxtbox;
	}

	@FindBy(id = "com.ebay.mobile:id/search_src_text")
	private static WebElement second_searchbox;

	public   WebElement second_searchbox() {
		return second_searchbox;
	}

	@FindBy(xpath= "//android.widget.TextView[@text='cycle?Cycling Equipment'  and @index='0']")
	private   WebElement first_Product;

	public  WebElement first_Product() {
		return first_Product;
	}

	@FindBys(@FindBy(id = "com.ebay.mobile:id/cell_collection_item" ))
	private static List< WebElement> products;

	public static  List< WebElement> products() {
		return products;
	}

	@FindBy(id = "com.ebay.mobile:id/button_bin")
	private static WebElement buynowbtn;

	public static WebElement buynowbtn() {
		return buynowbtn;
	}
	
	
	

	
	public  void enter_ProductToSearch(AppiumDriver driver,String Product)
	{
		
		try {
			search_Box().click();
			second_searchbox().sendKeys(Product);
			Thread.sleep(15000);

		} catch (Exception e) 
		{
			
			log.info("Unable to Enter The product");
			
		}
		
	}

	
	public  void click_On_firstProduct(AppiumDriver driver)
	{
		
		try {
			first_Product().click();

		} catch (Exception e) 
		{
			
			log.info("Unable to locate firstProduct");
			
		}
		
	}
	
	
	public  void  clickon_ProductDetailsPage(AppiumDriver driver)
	{
		boolean flag = false;
		try {
			products().get(0).click();

		} catch (Exception e) 
		{
			
			log.info("products  not displayed ");
			
		}
	}
	
	
	public  void  clickon_BuyNowBtn(AppiumDriver driver)
	{
		
		try {
			buynowbtn().click();

		} catch (Exception e) 
		{
			
			log.info("buy now  button not displayed");
			
		}
	}
	
	


	
	


}
