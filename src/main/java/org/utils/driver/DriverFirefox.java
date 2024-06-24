package org.utils.driver;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.utils.JSONHandler;
import org.utils.LoggerSingleton;

import java.nio.file.Paths;

public class DriverFirefox implements Driver {
    public static JSONHandler jsonConfigHandler = new JSONHandler("src/main/java/org/utils/config.json");

    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    protected static Logger logger = LoggerSingleton.getInstance().getLogger();

    private DriverFirefox() {
    }

    public static WebDriver getInstance() {
        if (driver.get() == null) {
            logger.info("driver is null - creating new one");
            FirefoxOptions options = new FirefoxOptions();


            options.addPreference(jsonConfigHandler.getValue("language_firefox_accept"),
                    jsonConfigHandler.getValue("language_firefox"));

            String downloadPath = Paths.get(System.getProperty("user.dir"),
                    jsonConfigHandler.getValue("download_directory")).toString();
            FirefoxProfile profile = new FirefoxProfile();
            profile.setPreference("browser.download.dir", downloadPath);
            profile.setPreference("browser.download.folderList", 2);
            profile.setPreference("browser.helperApps.neverAsk.saveToDisk", "application/octet-stream");
            options.setProfile(profile);


//            options.addArguments("-headless");
            driver.set(new FirefoxDriver(options));
        }
        return driver.get();
    }

    public static void quit() {
        logger.info("quitting driver");
        driver.get().quit();
        driver.remove();
    }
}
