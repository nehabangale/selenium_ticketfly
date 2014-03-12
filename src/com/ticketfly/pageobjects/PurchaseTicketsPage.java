package com.ticketfly.pageobjects;

import java.util.HashMap;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PurchaseTicketsPage extends BasePage {

	WebDriver driver;

	By ticketTypeslocator = By.cssSelector("div.products>div");
	By getTicketsButton = By.id("bestSeats");
	By eventnamelocator = By.className("event-name");

	String ticketType = "//div[@class='products']/div";
	String ticketQuantityPlus = "/div[@class='quantity ']/p/button[@class='plus']";

	public PurchaseTicketsPage(WebDriver mydriver)
	{
		super(mydriver);
		this.driver=mydriver;
	}

	public By pageLoadCondition(){
		return By.cssSelector("div.products>div");
	}
	
	public List<WebElement> getTicketTypes()
	{
		List<WebElement> tickettypes = driver.findElements(ticketTypeslocator);
		return tickettypes;
	}

	public HashMap<String,String> incrementTicketQuantity()
	{
		List<WebElement> allTicketTypes = getTicketTypes();
		HashMap<String,String> ticketinfo=new HashMap<String,String>();
		Random randomGenerator = new Random();

		for(int i=1;i<=allTicketTypes.size();i++)
		{
			int randomInt = randomGenerator.nextInt(10)+1;
			ticketinfo = incrementTicketQuatity (i,randomInt,ticketinfo);
		}
		return ticketinfo;
	}

	private HashMap<String,String> incrementTicketQuatity(int ticketNumber,int randomInt,HashMap<String,String> ticketinfo) {
		
		for(int j=1;j<=randomInt;j++)
		{
			try{
				driver.findElement(By.xpath(ticketType+"["+ticketNumber+"]"+ticketQuantityPlus)).click();
			}catch (Exception e){
				break;
			}
		}
		String tickettype = (driver.findElement(By.xpath(ticketType+"["+ticketNumber+"]"+"/div[@class='name']/div")).getText().split("\n")[0]);
		String ticketqty = (driver.findElement(By.xpath(ticketType+"["+ticketNumber+"]"+"/div[@class='quantity ']/p/input")).getAttribute("value"));
		
		ticketinfo.put(tickettype, ticketqty);
		
		return ticketinfo;	
	}

	public DeliveryMethodPage purchaseTickets()
	{
		driver.findElement(getTicketsButton).click();
		return new DeliveryMethodPage(driver);
	}

	public String verifyEventName()
	{
		return driver.findElement(eventnamelocator).getText();
	}


}
