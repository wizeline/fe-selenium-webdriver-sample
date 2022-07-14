package com.wizeline.selenium.pageObject;

import org.openqa.selenium.By;

import com.wizeline.selenium.drivers.BaseWebDriver;

public class ResultsPage extends BasePageObject {

	private static final By IMG_HEADER = new By.ByCssSelector("img[src*='Logotipo-Sevilla']");

	public ResultsPage(BaseWebDriver driver) {
		super(driver);
	}

	@Override
	public boolean isReady() {

		boolean status = false;
		this.driver.waitUntilVisible(IMG_HEADER, 2);

		status = driver.isElementDisplayed(IMG_HEADER);

		return status;
	}


}
