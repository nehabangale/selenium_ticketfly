package com.ticketfly.tests;

import java.net.MalformedURLException;
import java.util.HashMap;

import org.junit.Assert;
import org.junit.Test;

import com.ticketfly.pageobjects.DeliveryMethodPage;
import com.ticketfly.pageobjects.GoogleHomePage;
import com.ticketfly.pageobjects.PurchaseTicketsPage;
import com.ticketfly.pageobjects.TicketflyHomePage;

public class InterviewTest extends BaseTest {

	@Test
	public void test1() throws InterruptedException, MalformedURLException {
		
		GoogleHomePage googleSearch = getBasePage();
		googleSearch.search("TicketFly Inc");
		Assert.assertTrue("First Link text or URL is not as expected",googleSearch.validateFirstUrl("TicketFly", "www.ticketfly.com"));
		TicketflyHomePage ticketfly = googleSearch.goToFirstLink();
		ticketfly.search("The Hood Internet");
		Assert.assertEquals("The first link text is not valid","The Hood Internet" ,ticketfly.getFirstLinkText());
		PurchaseTicketsPage purchase = ticketfly.getTickets();
		Assert.assertEquals("Event Name does not match", "The Hood Internet", purchase.verifyEventName());
		HashMap<String,String> ticketinformation = purchase.incrementTicketQuantity();
		DeliveryMethodPage deliveryPage = purchase.purchaseTickets();
		Assert.assertTrue("Ticket type and quantity do not match",deliveryPage.verifyTicketInformation(ticketinformation));
		String selctedDeliveryMethod = deliveryPage.selectRandomDeliveryMethod();
		Assert.assertTrue("The delivery method does not match or Checkout page not displayed", deliveryPage.verifyDeliveryMethodAtCheckout(selctedDeliveryMethod));
		deliveryPage.checkout();
		Assert.assertTrue("Submit button is not present", deliveryPage.checkIfSubmitIsPresent());
		Thread.sleep(5000);
	}

}
