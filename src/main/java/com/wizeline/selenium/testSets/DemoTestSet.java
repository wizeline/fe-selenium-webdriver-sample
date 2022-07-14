package com.wizeline.selenium.testSets;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.wizeline.selenium.pageObject.LandingPage;


public class DemoTestSet extends DefaultTestSet{
	
	public DemoTestSet() {
		super();
	}
	
	//*********TESTS**************
	@Test(description= "Scenario Demo")
	public void searchTest() {
		
		landingPage = new LandingPage(driver);
		
		landingPage.clickOnAcceptBtn();
		
		isReady(landingPage);
		
		resultsPage = landingPage.doSearch();
		
		isReady(resultsPage);
	}
	
	@Test(description= "Scenario Demo fail")
	public void searchTest2() {
		
		landingPage = new LandingPage(driver);
		
		landingPage.clickOnAcceptBtn();
		
		isReady(landingPage);
		
		resultsPage = landingPage.doSearch();
		
		isReady(resultsPage);
		
		Assert.assertTrue(false, "FAILED!");
	}
	
}
