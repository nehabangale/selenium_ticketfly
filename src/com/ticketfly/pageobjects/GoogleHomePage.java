package com.ticketfly.pageobjects;

import java.net.URL;

import org.openqa.selenium.*;

public class GoogleHomePage extends BasePage
{
	WebDriver driver;
	URL firstURL=null;
	By searchlocator = By.xpath("//input[@id='gbqfq']");
	By searchbuttonlocator = By.xpath("//button[@id='gbqfb']");
	By firstlinklocator = By.xpath("//div[@id='search']/div/ol/li[1]/div/div/h3/a");
	By firstlinkText = By.xpath("//div[@id='search']/div/ol/li[1]/div/div/h3/a/em");
	
	public GoogleHomePage(WebDriver mydriver)
	{
		super(mydriver);
		this.driver=mydriver;
	}
	
	public GoogleHomePage search(String searchtext)
	{
		driver.findElement(searchlocator).sendKeys(searchtext);
		driver.findElement(searchbuttonlocator).click();
		return this;
	}
	
	public By pageLoadCondition(){
		return By.xpath("//input[@id='gbqfq']");
	}
	
	public boolean validateFirstUrl(String name,String url){
		if (getFirstLinkText().equalsIgnoreCase(name) && getFirstLinkURL().equalsIgnoreCase(url)){
			return true;
		}else{
			return false;
		}	
	}
	
	public String getFirstLinkText()
	{
		return driver.findElement(firstlinkText).getText().trim();
		
	}
	
	public String getFirstLinkURL()
	{
		try{
		firstURL = new URL (driver.findElement(firstlinklocator).getAttribute("href").trim());
		return firstURL.getHost();
		}catch (Exception e){
			System.out.println("The first search link is returning a malformed url");
			return null;
		}	
	}

	public TicketflyHomePage goToFirstLink(){
		driver.findElement(firstlinklocator).click();
		return new TicketflyHomePage(driver);
	}

}
