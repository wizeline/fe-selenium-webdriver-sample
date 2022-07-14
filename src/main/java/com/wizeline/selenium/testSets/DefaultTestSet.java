package com.wizeline.selenium.testSets;

import java.io.File;
import java.lang.reflect.Method;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import com.wizeline.selenium.drivers.BaseWebDriver;
import com.wizeline.selenium.utils.Initialization;
import com.wizeline.selenium.testSets.DefaultTestSet;
import com.wizeline.selenium.pageObject.BasePageObject;
import com.wizeline.selenium.pageObject.LandingPage;
import com.wizeline.selenium.pageObject.ResultsPage;


public class DefaultTestSet {
	
	protected static BaseWebDriver driver;
    protected static Initialization config = Initialization.getInstance();

    protected String tcName = "";
    protected String tcClass = "";
       
    //PageObjetc instances
    protected LandingPage landingPage;
    protected ResultsPage resultsPage;

    protected static Logger log = Logger.getLogger(DefaultTestSet.class);

    @BeforeClass(description = "beforeClassMethod")
    public void beforeClass() {
        this.tcClass = this.getClass().getSimpleName();
        log.info("*************** " + tcClass + " - Start ***************");
    }

    @BeforeMethod(alwaysRun = true)
    public void before1(Method method) {
        this.tcName = method.getName();
        log.info("--------------- Class: " + tcClass + " Method: " + tcName
                + " - Start ---------------");
    }
    
    @BeforeMethod(alwaysRun =true)
    public void before2() {
        log.info(
                "CLASS: DefaultTestSet - TESTNG: @BeforeMethod - METHOD: before() ---- Start");
        
        driver = config.initialize();
        
        log.info(
                "CLASS: DefaultTestSet - TESTNG: @BeforeMethod - METHOD: before() ---- End");
    }

    @AfterMethod(alwaysRun =true)
    public void after1(ITestResult result) {
        log.info("----- checkResultTest - Start -----");
        log.info(
                "TestSuite Name: " + result.getMethod().getXmlTest().getName());
        log.info("Test Result Status: " + result.getStatus());
        
        if (result.getStatus() == 2) {
            log.info("TakeScreenshot ");
            driver.saveScreenshotPath(config.getScreenshotPath(),tcClass+"_"+tcName);
            
        } 

        log.info("----- checkResultTest - End -----");
    }

    @AfterMethod(alwaysRun =true)
    public void after2() {
        log.info("--------------- Class: " + tcClass + " Method: " + tcName
                + " - End ---------------");
        
        deleteDownloadFolder();

        if (driver != null) {
        	driver.manage().deleteAllCookies();
            driver.quit();
        }
    }

    // *** Public methods ***//
    /**
     * Obtains the initialization configuration
     *
     * @return initialization instance
     */
    public static Initialization getConfig() {
        return config;
    }

    /**
     * Delete download folder
     */
    private void deleteDownloadFolder() {
        File dir = new File(Initialization.getInstance().getDownloadPath());

        if (dir.isDirectory()) {
            File[] files = dir.listFiles();
            for (File file : files) {
                if (file.isFile()) {
                    file.delete();
                }
            }

            dir.delete();
        }
    }

    /**
     * Checks that the PO is ready
     *
     * @param pageObject
     *            page object to be used
     */
    protected void isReady(BasePageObject pageObject) {
     
        Assert.assertTrue(pageObject.isReady(), "The PO " + pageObject.getClass().getName() + " is not ready");
    }

}
