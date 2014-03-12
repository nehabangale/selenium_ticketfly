package com.ticketfly.pageobjects;

import java.util.HashMap;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.*;

public class DeliveryMethodPage extends BasePage {

	WebDriver driver;

	By deliverymethods = By.cssSelector("section.delivery-methods>div>div");
	By deliveryMethodAtCheckout = By.cssSelector("ul.cart-wrapper>li>ul>li.delivery-type>ul>li.right");
	By continuelocator = By.className("continue");
	By checkoutlocator = By.cssSelector("div.action-button>input#checkout");
	By submitlocator = By.className("btn");

	String deliveryMethodsDiv = "//section[@class='delivery-methods']/div/div";
	String allTicketTypesAndQty = "//section[@class='inventory-access']/div";
	String eachTicketType = "/div[@class='ticket-type']";
	String eachTicketTypeQty = "/div[@class='ticket-qty']/span";

	public DeliveryMethodPage(WebDriver mydriver)
	{
		super(mydriver);
		this.driver=mydriver;
	}

	public By pageLoadCondition(){
		return By.cssSelector("section.delivery-methods>div>div");
	}

	//this method verifies ticket types and quantities are same as what was selected on the Purchase Tickets page. 
	public boolean verifyTicketInformation(HashMap<String,String> ticketinfo)
	{
		List<WebElement> tickets = driver.findElements(By.xpath(allTicketTypesAndQty));
		for(int i=1;i<=tickets.size();i++)
		{
			String ticketQty = driver.findElement(By.xpath(allTicketTypesAndQty+"["+i+"]"+eachTicketTypeQty)).getText();
			String ticketType = driver.findElement(By.xpath(allTicketTypesAndQty+"["+i+"]"+eachTicketType)).getText();
			if(ticketinfo.get(ticketType)==null)
				return false;
			else if(!(ticketinfo.get(ticketType).equals(ticketQty)))
			{
				return false;
			}
		}
		return true;
	}

	public String selectRandomDeliveryMethod()
	{
		int randomInt;
		List<WebElement> allDeliveryMethods = driver.findElements(deliverymethods);
		Random randomGenerator = new Random();
		int numberOfMethods = allDeliveryMethods.size();
		if(numberOfMethods==1)
			randomInt=1;
		else
			randomInt = randomGenerator.nextInt(numberOfMethods)+1;
		driver.findElement(By.xpath(deliveryMethodsDiv+"["+randomInt+"]"+"/label/div")).click();
		String deliveryMethodSelected = driver.findElement(By.xpath(deliveryMethodsDiv+"["+randomInt+"]"+"/label/div/p")).getText();
		driver.findElement(continuelocator).click();
		return deliveryMethodSelected;
	}

	public boolean verifyDeliveryMethodAtCheckout(String deliverymethod)
	{
		try{
			if(deliverymethod.equals(driver.findElement(deliveryMethodAtCheckout).getText()))
				return true;
			else
			{
				System.out.println("Delivery method does not match");
				return false;
			}
		}
		catch(Exception e)
		{
			System.out.println("Checkout page not displayed");
			return false;
		}
	}

	public void checkout()
	{
		try{
			driver.findElement(checkoutlocator).click();
		}
		catch(Exception e)
		{
			System.out.println("Checkout button is not present");
		}
	}

	public boolean checkIfSubmitIsPresent()
	{
		try{
			if(driver.findElement(submitlocator).isDisplayed())
				return true;
			else
				return false;
		}
		catch(Exception e)
		{
			return false;
		}
	}	
}
