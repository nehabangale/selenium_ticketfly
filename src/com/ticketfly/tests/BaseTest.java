package com.ticketfly.tests;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.ticketfly.pageobjects.GoogleHomePage;
import com.ticketfly.utils.PropertyLoader;


public class BaseTest {

	WebDriver driver=null;
	String browser=null;
	String OS=null;
	String baseURL = "http://www.google.com"; //Placeholder for the base url for all the test
	BasePage page=null;
	Properties testProperties=null;
	/*
	 * The the appropriate webdriver instance based on the browser and environment
	 */
	@Before
	public void setup(){
		testProperties=PropertyLoader.loadLinkerProperties();
		browser=testProperties.getProperty("browser");
		OS=testProperties.getProperty("OS");
		
		if (driver == null){
			if (browser.equalsIgnoreCase("chrome")){
				if (OS.equalsIgnoreCase("windows"))
					System.setProperty("webdriver.chrome.driver", "src/chromedriver.exe");
				else
					System.setProperty("webdriver.chrome.driver", "src/chromedriver");
				driver = new ChromeDriver();
				
			}else if (browser.equalsIgnoreCase("firefox")){
				driver = new FirefoxDriver();
			}else{
				throw new RuntimeException("The browser value is incorrect.");
			}
		}
		else{
			System.out.println("Driver instance is already created");
		}
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}
	
	@After
	public void teardown(){
		if (driver!=null){
			driver.quit();
		}
	}
	
	public WebDriver getDriver(){
		return driver;
	}
	
	public GoogleHomePage getBasePage(){
		getDriver().get(baseURL);
		return new GoogleHomePage(getDriver());
	}
}
