package com.wizeline.selenium.drivers;

import java.awt.AWTException;
import java.awt.MouseInfo;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.wizeline.selenium.utils.Initialization;


public class BaseWebDriverUtil {

    private static Logger log = Logger.getLogger(BaseWebDriverUtil.class);

    // **** Basic operation methods section ****//
    /**
     * Checks if an element exists in the DOM
     * 
     * @param driver
     *            WebDriver element
     * @param selector
     *            By element
     * @return True if the element exists in the DOM and false in the opposite
     *         case
     */
    public static boolean existsElement(BaseWebDriver driver, By selector) {
        log.info(
                "[log-Utils] BaseWebDriverUtil - Start existsElement method");

        boolean exists = false;

        try {
            exists = (driver.findElement(selector) != null);
        } catch (Exception ex) {
            exists = false;
        }

        log.info("[log-Utils] BaseWebDriverUtil - End existsElement method");

        return exists;
    }

    /**
     * Checks if an element exists in the DOM and is displayed
     * 
     * @param driver
     *            WebDriver element
     * @param selector
     *            By element
     * @return True if the element exists in the DOM and is displayed and false
     *         in the opposite case
     */
    public static boolean isElementDisplayed(BaseWebDriver driver,
            By selector) {
        log.info(
                "[log-Utils] BaseWebDriverUtil - Start isElementDisplayed method");

        boolean isDisplayed = false;

        try {
            WebElement element = driver.findElement(selector);
            isDisplayed = (element != null && element.isDisplayed());
        } catch (Exception ex) {
            isDisplayed = false;
        }

        log.info(
                "[log-Utils] BaseWebDriverUtil - End isElementDisplayed method");

        return isDisplayed;
    }

    /**
     * Clicks on an element after wait and if it is displayed
     * 
     * @param driver
     *            WebDriver element
     * @param selector
     *            By element
     */
    public static void clickIfExists(BaseWebDriver driver, By selector) {
        log.info(
                "[log-Utils] BaseWebDriverUtil - Start clickIfExists method");

        wait(driver, selector, 20);

        if (isElementDisplayed(driver, selector)) {
            driver.findElement(selector).click();
        } else {
            log.error("The element " + selector.toString()
                    + " is not displayed.");
        }

        log.info("[log-Utils] BaseWebDriverUtil - End clickIfExists method");
    }

    // **** Javascript methods section ****//
    /**
     * Executes JavaScript in the context of the currently window
     * 
     * @param driver
     *            WebDriver element
     * @param script
     *            The JavaScript to execute
     * @return Boolean, Long, String, List, WebElement Or null
     */
    public static Object executeJavaScript(BaseWebDriver driver,
            String script) {
        log.info(
                "[log-Utils] BaseWebDriverUtil - Start executeJavaScript method");
        log.info(
                "[log-Utils] BaseWebDriverUtil - End executeJavaScript method");

        return ((JavascriptExecutor) driver).executeScript(script);
    }

    /**
     * Puts the focus on an element through its id
     * 
     * @param driver
     *            WebDriver element
     * @param id
     *            string with the id of an element
     */
    public static void focus(BaseWebDriver driver, String id) {
        log.info("[log-Utils] BaseWebDriverUtil - Start focus method");

        driver.executeJavaScript(
                "document.getElementById('" + id + "').focus();");

        log.info("[log-Utils] BaseWebDriverUtil - End focus method");
    }

    // **** Screenshot methods section ****//
    /**
     * Saves a screenshot in a path with a timestamp
     * 
     * @param driver
     *            WebDriver element
     * @param folderPath
     *            to save the screenshot
     * @param baseFileName
     *            file name
     */
    public static void saveScreenshotPath(BaseWebDriver driver,
            String folderPath, String baseFileName) {
        log.info(
                "[log-Utils] BaseWebDriverUtil - Start saveScreenshotPath method");

        String timeStamp = getTimeStamp();
        File scrFile = ((TakesScreenshot) driver)
                .getScreenshotAs(OutputType.FILE);

        try {
            FileUtils.copyFile(scrFile, new File(folderPath + File.separator
                    + baseFileName + "_" + timeStamp + ".png"));
        } catch (IOException e) {
            log.error("Error creating screenshot", e);
        }

        log.info(
                "[log-Utils] BaseWebDriverUtil - End saveScreenshotPath method");
    }

    /**
     * Saves a screenshot in the default path
     * 
     * @param driver
     *            WebDriver element
     */
    public static void saveScreenshotDefault(BaseWebDriver driver) {
        log.info(
                "[log-Utils] BaseWebDriverUtil - Start saveScreenshotDefault method");

        String folderPath = Initialization.getInstance().getScreenshotPath();

        saveScreenshotPath(driver, folderPath, "");

        log.info(
                "[log-Utils] BaseWebDriverUtil - End saveScreenshotDefault method");
    }

    // **** Sleep method ****//
    /**
     * Stops the execution during some seconds
     * 
     * @param seconds
     *            time to stop the execution
     */
    public static void sleep(int seconds) {
        log.info("[log-Utils] BaseWebDriverUtil - Start sleep method");

        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            log.error("A thread has interrupted the current thread", e);
        }

        log.info("[log-Utils] BaseWebDriverUtil - End sleep method");
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
    public static void pressKey(int key, int sleepTime) {
        log.info("[log-Utils] BaseWebDriverUtil - Start pressKey method");

        Robot r;

        try {
            r = new Robot();

            Thread.sleep(sleepTime * 1000);
            r.keyPress(key);
            Thread.sleep(sleepTime * 1000);
        } catch (AWTException e) {
            log.error(
                    "The platform configuration does not allow low-level input control",
                    e);
        } catch (InterruptedException e) {
            log.error("A thread has interrupted the current thread", e);
        }

        log.info("[log-Utils] BaseWebDriverUtil - End pressKey method");
    }

    /**
     * Releases a keyboard key
     * 
     * @param key
     *            to release
     * @param sleepTime
     *            time to wait before and after to release the key
     */
    public static void releaseKey(int key, int sleepTime) {
        log.info("[log-Utils] BaseWebDriverUtil - Start releaseKey method");

        Robot r;

        try {
            r = new Robot();

            Thread.sleep(sleepTime * 1000);
            r.keyRelease(key);
            Thread.sleep(sleepTime * 1000);
        } catch (AWTException e) {
            log.error(
                    "The platform configuration does not allow low-level input control",
                    e);
        } catch (InterruptedException e) {
            log.error("A thread has interrupted the current thread", e);
        }

        log.info("[log-Utils] BaseWebDriverUtil - End releaseKey method");
    }

    /**
     * Presses and releases a keyboard key
     * 
     * @param key
     *            to press and to release
     */
    public static void pressReleaseKey(int key) {
        log.info(
                "[log-Utils] BaseWebDriverUtil - Start pressReleaseKey method");

        Robot r;

        try {
            r = new Robot();

            r.keyPress(key);
            Thread.sleep(100);
            r.keyRelease(key);
            Thread.sleep(100);
        } catch (AWTException e) {
            log.error(
                    "The platform configuration does not allow low-level input control",
                    e);
        } catch (InterruptedException e) {
            log.error("A thread has interrupted the current thread", e);
        }

        log.info(
                "[log-Utils] BaseWebDriverUtil - End pressReleaseKey method");
    }

    // **** Mouse events methods section ****//
    /**
     * Moves the mouse over an element
     * 
     * @param driver
     *            WebDriver element
     * @param selector
     *            By element
     */
    public static void moveMouseOverElement(BaseWebDriver driver,
            By selector) {
        log.info(
                "[log-Utils] BaseWebDriverUtil - Start moveMouseOverElement method");

        Robot r;

        try {
            r = new Robot();
            Point point = getPositionToClick(driver, selector);
            java.awt.Point location = MouseInfo.getPointerInfo().getLocation();

            if (((int) location.getX()) != point.getX()
                    || ((int) location.getY()) != point.getY()) {
                r.mouseMove(point.getX(), point.getY());
                driver.sleep(2);
            }
        } catch (AWTException e) {
            log.error(
                    "The platform configuration does not allow low-level input control",
                    e);
        }

        log.info(
                "[log-Utils] BaseWebDriverUtil - End moveMouseOverElement method");
    }

    /**
     * Moves the mouse out of an element
     * 
     * @param driver
     *            WebDriver element
     * @param selector
     *            By element
     */
    public static void moveMouseOutElement(BaseWebDriver driver,
            By selector) {
        log.info(
                "[log-Utils] BaseWebDriverUtil - Start moveMouseOutElement method");

        Robot r;
        int x = 0, y = 0;

        try {
            r = new Robot();

            if (existsElement(driver, selector)) {
                WebElement element = driver.findElement(selector);
                Point position = element.getLocation();

                x = position.getX() - 10;
                y = position.getY() - 10;
            }

            Point toMove = new Point(x, y);
            java.awt.Point location = MouseInfo.getPointerInfo().getLocation();

            if (((int) location.getX()) != toMove.getX()
                    && ((int) location.getY()) != toMove.getY()) {
                r.mouseMove(toMove.getX(), toMove.getY());
                driver.sleep(2);
            }
        } catch (AWTException e) {
            log.error(
                    "The platform configuration does not allow low-level input control",
                    e);
        }

        log.info(
                "[log-Utils] BaseWebDriverUtil - End moveMouseOutElement method");
    }

    /**
     * Clicks with the mouse on an element
     * 
     * @param driver
     *            WebDriver element
     * @param selector
     *            By element
     */
    public static void clickOnWithMouse(BaseWebDriver driver, By selector) {
        log.info(
                "[log-Utils] BaseWebDriverUtil - Start clickOnWithMouse method");

        Robot r;

        try {
            r = new Robot();

            moveMouseOverElement(driver, selector);

            r.mousePress(InputEvent.BUTTON1_DOWN_MASK);
            driver.sleep(1);
            r.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
            driver.sleep(2);
        } catch (AWTException e) {
            log.error(
                    "The platform configuration does not allow low-level input control",
                    e);
        }

        log.info(
                "[log-Utils] BaseWebDriverUtil - End clickOnWithMouse method");
    }

    /**
     * Clicks with the mouse out of an element
     * 
     * @param driver
     *            WebDriver element
     * @param selector
     *            By element
     */
    public static void clickOutWithMouse(BaseWebDriver driver, By selector) {
        BaseWebDriverUtil.moveMouseOutElement(driver, selector);
        log.info(
                "[log-Utils] BaseWebDriverUtil - Start clickOutWithMouse method");

        try {
            Robot r = new Robot();

            moveMouseOutElement(driver, selector);

            r.mousePress(InputEvent.BUTTON1_DOWN_MASK);
            driver.sleep(1);
            r.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
            driver.sleep(2);
        } catch (AWTException e) {
            log.error(
                    "The platform configuration does not allow low-level input control",
                    e);
        }

        log.info(
                "[log-Utils] BaseWebDriverUtil - End clickOutWithMouse method");
    }

    /**
     * Double clicks with the mouse on an element
     * 
     * @param driver
     *            WebDriver element
     * @param selector
     *            By element
     */
    public static void doubleClickOnWithMouse(BaseWebDriver driver,
            By selector) {
        log.info(
                "[log-Utils] BaseWebDriverUtil - Start doubleClickOnWithMouse method");

        Robot r;

        try {
            r = new Robot();

            moveMouseOverElement(driver, selector);

            r.mousePress(InputEvent.BUTTON1_DOWN_MASK);
            r.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
            r.mousePress(InputEvent.BUTTON1_DOWN_MASK);
            r.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
            driver.sleep(2);
        } catch (AWTException e) {
            log.error(
                    "The platform configuration does not allow low-level input control",
                    e);
        }

        log.info(
                "[log-Utils] BaseWebDriverUtil - End doubleClickOnWithMouse method");
    }

    // **** Wait methods section ****//
    /**
     * It sleeps the driver for X seconds. If the element exist in the DOM, the
     * execution continue without waiting X seconds
     * 
     * @param driver
     *            WebDriver element for sleep
     * @param selector
     *            By element for wait
     * @param seconds
     *            to be slept
     * @return true if the element exist in the DOM and false in the opposite
     *         case
     */
    public static boolean wait(BaseWebDriver driver, By selector,
           long seconds) {
        log.info("[log-Utils] BaseWebDriverUtil - Start wait method");

        WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(seconds));
        
        boolean retVal = true;

        try {
            w.until(ExpectedConditions.presenceOfElementLocated(selector));
        } catch (TimeoutException e) {
            retVal = false;

            log.error("The element: " + selector.toString()
                    + " is missing in the DOM. Waiting time: " + seconds
                    + " seconds");
        }

        log.info("[log-Utils] BaseWebDriverUtil - End wait method");

        return retVal;
    }

    /**
     * It sleeps the driver for X seconds. If the element is visible in the
     * page, the execution continue without waiting X seconds
     * 
     * @param driver
     *            WebDriver element for sleep
     * @param selector
     *            By element for wait
     * @param seconds
     *            to be slept
     * @return true if the element is visible in the page and false in the
     *         opposite case
     */
    public static boolean waitUntilVisible(BaseWebDriver driver, By selector,
            long seconds) {
        log.info(
                "[log-Utils] BaseWebDriverUtil - Start waitUntilVisible method");

        WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(seconds));
        boolean retVal = true;

        try {
            w.until(ExpectedConditions.visibilityOfElementLocated(selector));
        } catch (TimeoutException e) {
            retVal = false;

            log.error("The element: " + selector.toString()
                    + " is not visible in the page. Waiting time: " + seconds
                    + " seconds");
        }

        log.info(
                "[log-Utils] BaseWebDriverUtil - End waitUntilVisible method");

        return retVal;
    }

    /**
     * It sleeps the driver for X seconds. If the element is clickable in the
     * page, the execution continue without waiting X seconds
     * 
     * @param driver
     *            WebDriver element for sleep
     * @param selector
     *            By element for wait
     * @param seconds
     *            to be slept
     * @return true if the element is clickable in the page and false in the
     *         opposite case
     */
    public static boolean waitUntilElementClickable(BaseWebDriver driver,
            By selector, long seconds) {
        log.info(
                "[log-Utils] BaseWebDriverUtil - Start waitUntilElementClickable method");

        WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(seconds));
        boolean retVal = true;

        try {
            w.until(ExpectedConditions.elementToBeClickable(selector));
        } catch (TimeoutException e) {
            retVal = false;

            log.error("The element: " + selector.toString()
                    + " is not clickable. Waiting time: " + seconds
                    + " seconds");
        }

        log.info(
                "[log-Utils] BaseWebDriverUtil - End waitUntilElementClickable method");

        return retVal;
    }

    /**
     * It sleeps the driver for X seconds. If the text is present in element,
     * the execution continue without waiting X seconds
     * 
     * @param driver
     *            WebDriver element for sleep
     * @param selector
     *            By element for wait
     * @param seconds
     *            to be slept
     * @param text
     *            to be find
     * @return true If the text is present in element, and false in the opposite
     *         case
     */
    public static boolean waitUntilTextPresent(BaseWebDriver driver,
            By selector, long seconds, String text) {
        log.info(
                "[log-Utils] BaseWebDriverUtil - Start waitUntilTextPresent method");

        WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(seconds));
        boolean retVal = true;

        try {
            w.until(ExpectedConditions.textToBePresentInElementLocated(selector,
                    text));
        } catch (TimeoutException e) {
            retVal = false;

            log.error("The text: " + text + " in the element: "
                    + selector.toString()
                    + " is missing in the DOM. Waiting time: " + seconds
                    + " seconds");
        }

        log.info(
                "[log-Utils] BaseWebDriverUtil - End waitUntilTextPresent method");

        return retVal;
    }

    /**
     * Method to obtain the current window handler.
     * @param @see BaseWebDriver
     * @return the current window handler.
     */
    public static String getCurrentWindowHandler(BaseWebDriver driver) {
        return driver.getWindowHandle();
    }

    /**
     * Method to check if the current windows is open or closed.
     * @param @see BaseWebDriver
     * @return true if the current windows is open, false if it is closed.
     */
    public static Boolean isCurrentWindowOpen(BaseWebDriver driver) {
        Boolean isOpen = false;
        try {
            isOpen = driver.getWindowHandles()
                    .contains(driver.getWindowHandle());
        } catch (Exception e) {
            isOpen = false;
        }
        return isOpen;
    }
    
    /**
     * Method to get last element of the list by selector
     * @param driver
     * 			WebDriver element for sleep
     * @param selector
     *           By element for look for
     * @return
     */
    public static WebElement getLastElement(BaseWebDriver driver, By selector) {
        log.info(
                "[log-Utils] BaseWebDriverUtil - Start getLastElement method");

        
        List<WebElement> list = driver.findElements(selector);
		
		int lastInd =list.size() -1;
		
    
        log.info(
                "[log-Utils] BaseWebDriverUtil - End getLastElement method");

        return list.get(lastInd);
    }

    // **** Private methods section ****//
    /**
     * Generates a timestamp
     * 
     * @return string with a timestamp
     */
    private static String getTimeStamp() {
        log.info(
                "[log-Utils] BaseWebDriverUtil - Start getTimeStamp method");

        String timeStamp = new SimpleDateFormat("dd-MM-yyyy_HH-mm-ss")
                .format(Calendar.getInstance().getTime());

        log.info("[log-Utils] BaseWebDriverUtil - End getTimeStamp method");

        return timeStamp;

    }

    /**
     * Retrieves the center of an element to click it.
     * 
     * @param driver
     *            WebDriver element
     * @param selector
     *            By element
     * @return Point the position of the center of the element
     */
    private static Point getPositionToClick(BaseWebDriver driver,
            By selector) {
        log.info(
                "[log-Utils] BaseWebDriverUtil - Start getPositionToClick method");

        Point toReturn = null;
        int x = 0, y = 0;

        if (existsElement(driver, selector)) {
            WebElement element = driver.findElement(selector);
            Point position = element.getLocation();

            x = position.getX() + (element.getSize().getWidth() / 2);
            y = position.getY() + (element.getSize().getHeight() / 2);

            toReturn = new Point(x, y);
        }

        log.info(
                "[log-Utils] BaseWebDriverUtil - End getPositionToClick method");

        return toReturn;
    }
    
    
}
