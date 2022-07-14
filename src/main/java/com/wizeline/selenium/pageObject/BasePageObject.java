package com.wizeline.selenium.pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.wizeline.selenium.drivers.BaseWebDriver;

public abstract class BasePageObject {
	
	
	protected final BaseWebDriver driver;
	
	
	public BasePageObject(BaseWebDriver driver) {
		this.driver = driver;
	}
	
	public abstract boolean isReady();
	
	
	public WebElement getElement(By selector) {
		
		return driver.findElement(selector);
		
	}
	
	public void fillField(By selector, String text) {
		
		this.getElement(selector).sendKeys(text);
		
	}

}
