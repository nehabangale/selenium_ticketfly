package com.ticketfly.pageobjects;

import org.openqa.selenium.*;

public class TicketflyHomePage extends BasePage {
	
	WebDriver driver;
	
	By searchlocator = By.id("q");
	By findeventslocator = By.className("header-search-btn");
	By firstlinkText = By.cssSelector("div.search-results>ul>li>div.event-results-titles>h3");
	By getticketslocator = By.cssSelector("div.search-results>ul>li>div.event-results-ticket-price>span>a");
	By ticketflylogo = By.cssSelector("h2.clearfix>span>a");
	By ticketflysearchlocator = By.cssSelector("form.search>fieldset.clearfix");
	
	
	public TicketflyHomePage(WebDriver mydriver)
	{
		super(mydriver);
		this.driver=mydriver;
		
	}
	
	public By pageLoadCondition(){
		return By.cssSelector("h2.clearfix>span>a");
	}
	public void search(String eventname)
	{
		driver.findElement(searchlocator).sendKeys(eventname);
		driver.findElement(findeventslocator).click();
	}
	
	public String getFirstLinkText()
	{
		return driver.findElement(firstlinkText).getText().trim();	
	}
	
	public PurchaseTicketsPage getTickets()
	{
		driver.findElement(getticketslocator).click();
		return new PurchaseTicketsPage(driver);
	}

}
