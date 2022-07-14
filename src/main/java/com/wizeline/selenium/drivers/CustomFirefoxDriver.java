package com.wizeline.selenium.drivers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;


public class CustomFirefoxDriver extends FirefoxDriver
        implements BaseWebDriver {

    /**
     * Main window handler to perform windows switch later.
     */
    private String mainWindowHandler;

    /**
     * Constructor
     */
    public CustomFirefoxDriver() {
        super();
        mainWindowHandler = BaseWebDriverUtil.getCurrentWindowHandler(this);
    }

    public CustomFirefoxDriver(FirefoxOptions options) {
        super(options);
        mainWindowHandler = BaseWebDriverUtil.getCurrentWindowHandler(this);
    }

    // **** Basic operation methods section ****//
    /**
     * Checks if an element exists in the DOM
     * 
     * @param selector
     *            By element
     * @return True if the element exists in the DOM and false in the opposite
     *         case
     */
    public boolean existsElement(By selector) {
        return BaseWebDriverUtil.existsElement(this, selector);
    }

    /**
     * Checks if an element exists in the DOM and is displayed
     * 
     * @param selector
     *            By element
     * @return True if the element exists in the DOM and is displayed and false
     *         in the opposite case
     */
    public boolean isElementDisplayed(By selector) {
        return BaseWebDriverUtil.isElementDisplayed(this, selector);
    }

    /**
     * Clicks on an element after wait and if it is displayed
     * 
     * @param selector
     *            By element
     */
    public void clickIfExists(By selector) {
        BaseWebDriverUtil.clickIfExists(this, selector);
    }

    // **** Javascript methods section ****//
    /**
     * Executes JavaScript in the context of the currently window
     * 
     * @param script
     *            The JavaScript to execute
     * @return Boolean, Long, String, List, WebElement Or null
     */
    public Object executeJavaScript(String script) {
        return BaseWebDriverUtil.executeJavaScript(this, script);
    }

    /**
     * Puts the focus on an element through its id
     * 
     * @param driver
     *            WebDriver element
     * @param id
     *            string with the id of an element
     */
    public void focus(String id) {
        BaseWebDriverUtil.focus(this, id);
    }

    // **** Screenshot methods section ****//
    /**
     * Saves a screenshot in a path with a timestamp
     * 
     * @param folderPath
     *            to save the screenshot
     * @param baseFileName
     *            file name
     */
    public void saveScreenshotPath(String folderPath, String baseFileName) {
        BaseWebDriverUtil.saveScreenshotPath(this, folderPath, baseFileName);
    }

    /**
     * Saves a screenshot in the default path
     * 
     * @param driver
     *            WebDriver element
     */
    public void saveScreenshotDefault() {
        BaseWebDriverUtil.saveScreenshotDefault(this);
    }

    // **** Sleep method ****//
    /**
     * Stops the execution during some seconds
     * 
     * @param seconds
     *            time to stop the execution
     */
    public void sleep(int seconds) {
        BaseWebDriverUtil.sleep(seconds);
    }

    // **** Keyboard events methods section ****//
    /**
     * Presses a keyboard key
     * 
     * @param key
     *            to press
     * @param sleepTime
     *            time to wait before and after to press the key
     */
    public void pressKey(int key, int sleepTime) {
        BaseWebDriverUtil.pressKey(key, sleepTime);
    }

    /**
     * Releases a keyboard key
     * 
     * @param key
     *            to release
     * @param sleepTime
     *            time to wait before and after to release the key
     */
    public void releaseKey(int key, int sleepTime) {
        BaseWebDriverUtil.releaseKey(key, sleepTime);
    }

    /**
     * Presses and releases a keyboard key
     * 
     * @param key
     *            to press and release
     */
    public void pressReleaseKey(int key) {
        BaseWebDriverUtil.pressReleaseKey(key);
    }

    // **** Mouse events methods section ****//
    /**
     * Moves the mouse over an element
     * 
     * @param selector
     *            By element
     */
    public void moveMouseOverElement(By selector) {
        BaseWebDriverUtil.moveMouseOverElement(this, selector);
    }

    /**
     * Moves the mouse out of an element
     * 
     * @param selector
     *            By element
     */
    public void moveMouseOutElement(By selector) {
        BaseWebDriverUtil.moveMouseOutElement(this, selector);
    }

    /**
     * Clicks with the mouse on an element
     * 
     * @param selector
     *            By element
     */
    public void clickOnWithMouse(By selector) {
        BaseWebDriverUtil.clickOnWithMouse(this, selector);
    }

    /**
     * Clicks with the mouse out of an element
     * 
     * @param selector
     *            By element
     */
    public void clickOutWithMouse(By selector) {
        BaseWebDriverUtil.clickOutWithMouse(this, selector);
    }

    /**
     * Double clicks with the mouse on an element
     * 
     * @param selector
     *            By element
     */
    public void doubleClickOnWithMouse(By selector) {
        BaseWebDriverUtil.doubleClickOnWithMouse(this, selector);

    }

    // **** Wait methods section ****//
    /**
     * It sleeps the driver for X seconds. If the element is visible in the
     * page, the execution continue without waiting X seconds
     * 
     * @param selector
     *            By element for wait
     * @param seconds
     *            to be slept
     * @return true if the element exist in the DOM and false in the opposite
     *         case
     */
    public boolean wait(By selector, long seconds) {
        return BaseWebDriverUtil.wait(this, selector, seconds);
    }

    /**
     * It sleeps the driver for X seconds. If the element is visible in the
     * page, the execution continue without waiting X seconds
     * 
     * @param selector
     *            By element for wait
     * @param seconds
     *            to be slept
     * @return true if the element is visible in the page and false in the
     *         opposite case
     */
    public boolean waitUntilVisible(By selector, long seconds) {
        return BaseWebDriverUtil.waitUntilVisible(this, selector, seconds);
    }

    /**
     * It sleeps the driver for X seconds. If the element is clickable in the
     * page, the execution continue without waiting X seconds
     * 
     * @param selector
     *            By element for wait
     * @param seconds
     *            to be slept
     * @return true if the element is clickable in the page and false in the
     *         opposite case
     */
    public boolean waitUntilElementClickable(By selector, long seconds) {
        return BaseWebDriverUtil.waitUntilElementClickable(this, selector,
                seconds);
    }

    /**
     * It sleeps the driver for X seconds. If the text is present in element,
     * the execution continue without waiting X seconds
     * 
     * @param selector
     *            By element for wait
     * @param seconds
     *            to be slept
     * @param text
     *            to be find
     * @return true If the text is present in element, and false in the opposite
     *         case
     */
    public boolean waitUntilTextPresent(By selector, long seconds,
            String text) {
        return BaseWebDriverUtil.waitUntilTextPresent(this, selector,
                seconds, text);
    }

    /**
     * @see BaseWebDriver#switchToMainwindow()
     */
    @Override
    public void switchToMainwindow() {
        this.sleep(1);
        this.switchTo().window(mainWindowHandler);
        this.sleep(1);
    }

    /**
     * @see BaseWebDriver#isCurrentWindowOpen()
     */
    @Override
    public Boolean isCurrentWindowOpen() {
        return BaseWebDriverUtil.isCurrentWindowOpen(this);
    }
    
    /**
     * @see BaseWebDriver#getLastElement()
     */
	@Override
	public WebElement getLastElement(By selector) {
		return BaseWebDriverUtil.getLastElement(this,selector);
	}

}
