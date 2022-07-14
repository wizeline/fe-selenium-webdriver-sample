package com.wizeline.selenium.pageObject;

import org.openqa.selenium.By;

import com.wizeline.selenium.drivers.BaseWebDriver;

public class LandingPage extends BasePageObject {

	private static final By ACCEPT_BTN = new By.ByCssSelector("#W0wltc");
	private static final By SEARCH_INPUT = new By.ByCssSelector("input[name=q]");
	private static final By SUBMIT_BTN = new By.ByXPath("//input[@name='btnI']");
	

	public LandingPage(BaseWebDriver driver) {
		super(driver);
	}

	@Override
	public boolean isReady() {

		boolean status = false;

		status = driver.isElementDisplayed(SEARCH_INPUT);

		return status;
	}
	
	public void clickOnAcceptBtn() {
		
		this.driver.clickIfExists(ACCEPT_BTN);
		
		this.driver.waitUntilVisible(SEARCH_INPUT, 2);
	}
	
	public ResultsPage doSearch() {
		
		this.fillField(SEARCH_INPUT, "Sevilla Fc");
		
		this.driver.waitUntilVisible(SUBMIT_BTN, 2);
		
		this.driver.clickIfExists(SUBMIT_BTN);
		
		return new ResultsPage(driver);
	}

}
