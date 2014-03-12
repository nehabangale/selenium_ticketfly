package com.ticketfly.pageobjects;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public abstract class BasePage {
	WebDriver driver;

	public BasePage(WebDriver driver) {
		this.driver = driver;
		verifyPageLoaded(pageLoadCondition());
	}

	public void verifyPageLoaded(By by){
		Assert.assertTrue("Verifying is the page is loaded", isElementPresent(driver, by));
	}

	public boolean isElementPresent(WebDriver driver, By by) {

		try {
			if(driver.findElement(by).isDisplayed());
				return true;
		} catch (Exception e) {
			return false;
		}
	}

	abstract public By pageLoadCondition();
}
