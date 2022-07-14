package com.wizeline.selenium.utils;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;

import com.wizeline.selenium.drivers.BaseWebDriver;
import com.wizeline.selenium.drivers.CustomChromeDriver;
import com.wizeline.selenium.drivers.CustomFirefoxDriver;


public class Initialization {

    private String properties;
    private String browser;
    private String loginURL;
    private String os;
    private String screenshotPath;
    private String downloadPath;
    private String webdriverFirefox;
    private String webdriverChrome;
    private int widthBeforeMaximize;
    private int widthAfterMaximize;
    private int heightBeforeMaximize;
    private int heightAfterMaximize;
    private static Initialization instance = null;
    private static Logger log = Logger.getLogger(Initialization.class);

    BaseWebDriver driver;

    /**
     * Singleton pattern
     * 
     * @return a single instance
     */
    public static Initialization getInstance() {
        if (instance == null) {
            instance = new Initialization();
        }
        return instance;
    }

    /**
     * Reads properties when the class is instanced
     */
    private Initialization() {
        this.readProperties();
    }

    // **** Read properties method ****//
    public void readProperties() {
        log.info("[log-Properties] " + this.getClass().getSimpleName()
                + "- Start readProperties test");

        properties = "test.properties";
        Properties prop = new Properties();
        log = Logger.getLogger(this.getClass());

        try {
            ClassLoader loader = Thread.currentThread().getContextClassLoader();

            // Load a properties file
            prop.load(loader.getResourceAsStream(properties));

            // Get the property value
            browser = prop.getProperty("browser");
            loginURL = prop.getProperty("environment");
            os = prop.getProperty("OS");
            screenshotPath = prop.getProperty("screenshotPath");
            downloadPath = prop.getProperty("downloadPath");
            webdriverFirefox = prop.getProperty("webdriverFirefox");
            webdriverChrome = prop.getProperty("webdriverChrome");
            
            
            if(os.equalsIgnoreCase("MacOs") ) {
            	webdriverFirefox= "/usr/local/bin/geckodriver";
            	webdriverChrome= "/usr/local/bin/chromedriver";
            }

            // Create download path
            log.info("Download path: " + getDownloadPath());
            File file = new File(this.getDownloadPath());
            if (!file.exists()) {
                file.mkdir();
            }

            log.info("Auto detected operative System = " + os);
        } catch (IOException ex) {
            log.error("test.properties file is not found. If this is the first time you excuted your test you can copy the settings properties file in the test folder in svn and customized it to match your environment");
        }

        log.info("[log-Properties] " + this.getClass().getSimpleName()
                + "- End readProperties test");
    }

    // **** Driver initialization method ****//
    public BaseWebDriver initialize() {
        log.info("[log-Properties] " + this.getClass().getSimpleName()
                + "- Start initialize test");

        BaseWebDriver tmpDriver = null;

        // Driver initialization
        if (browser.equalsIgnoreCase("Firefox")) {

        	FirefoxProfile firefoxProfile = new FirefoxProfile();
        	// profile.setPreference("browser.download.useDownloadDir", true); This is true by default. Add it if it's not working without it.

        	firefoxProfile.setPreference("browser.download.folderList",2); //Use for the default download directory the last folder specified for a download
        	firefoxProfile.setPreference("browser.download.dir", System.getProperty("user.dir")
                    + System.getProperty("file.separator")
                    + this.getDownloadPath()); //Set the last directory used for saving a file from the "What should (browser) do with this file?" dialog.
        	firefoxProfile.setPreference("browser.helperApps.neverAsk.saveToDisk", "application/pdf"); //list of MIME types to save to disk without asking what to use to open the file
        	firefoxProfile.setPreference("pdfjs.disabled", true);  // disable the built-in PDF viewer
            
            FirefoxOptions options = new FirefoxOptions();
            
            
            options.setProfile(firefoxProfile);
          
            System.setProperty("webdriver.gecko.driver", webdriverFirefox);
            
            tmpDriver = new CustomFirefoxDriver(options);

        } else if (browser.equalsIgnoreCase("Chrome")) {
        	
        	Map<String, Object> prefs = new HashMap<String, Object>();
        	prefs.put("profile.default_content_settings.popups", 0);
            prefs.put("download.default_directory",System.getProperty("user.dir")
                    + System.getProperty("file.separator")
                    + this.getDownloadPath());
            prefs.put("safebrowsing.enabled", "false");
            
            ChromeOptions options = new ChromeOptions();
            options.setExperimentalOption("prefs", prefs);
            
            System.setProperty("webdriver.chrome.driver", webdriverChrome);
            
          
            tmpDriver = new CustomChromeDriver(options);

        }

        // Common functions
        driver = tmpDriver;

        log.info("Browser initialized with dimensions: "
                + driver.manage().window().getSize().getWidth() + "px - "
                + driver.manage().window().getSize().getHeight() + "px");

        widthBeforeMaximize = driver.manage().window().getSize().getWidth();
        heightBeforeMaximize = driver.manage().window().getSize().getHeight();

        driver.get(loginURL);
        driver.manage().window().maximize();
     
        driver.sleep(1);

        widthAfterMaximize = driver.manage().window().getSize().getWidth();
        heightAfterMaximize = driver.manage().window().getSize().getHeight();

        if ((widthBeforeMaximize == widthAfterMaximize)
                && (heightBeforeMaximize == heightAfterMaximize)) {
            log.info("Not maximized first time...try again");

            driver.sleep(1);

            driver.manage().window().maximize();

            driver.sleep(2);
        }

        this.cleanDownloadDirectory();

        log.info("Browser resized with dimensions: "
                + driver.manage().window().getSize().getWidth() + "px - "
                + driver.manage().window().getSize().getHeight() + "px");

        log.info("[log-Properties] " + this.getClass().getSimpleName()
                + "- End initialize test");

        return driver;
    }


    // **** Getters methods section ****//
    public String getBrowser() {
        return browser;
    }

    
    public String getLoginURL() {
        return loginURL;
    }

    public String getOS() {
        return os;
    }

    public String getScreenshotPath() {
        return this.screenshotPath;
    }

        
    public String getDownloadPath() {
        return downloadPath;
    }

    // **** Download methods section ****//
    /**
     * Returns the donwloaded filepath
     * 
     * @param filename
     *            the name of the file
     * @return the donwloaded filepath
     */
    public String getDownloadedFilePath(String filename) {
        String path = this.getDownloadPath() + filename;
        File f = new File(path);
        if (f.exists()) {
            return path;
        } else {
            log.info("Not exists the file");
            return null;
        }
    }

    /**
     * Cleans the download directory.
     */
    public void cleanDownloadDirectory() {
        File f = new File(this.getDownloadPath());
        if (f.exists() && f.isDirectory()) {
            try {
                FileUtils.cleanDirectory(f);
            } catch (IOException e) {
                log.error(e.getStackTrace());
            }
        }
    }

}
