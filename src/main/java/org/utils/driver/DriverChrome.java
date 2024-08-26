package org.utils.driver;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.utils.JSONHandler;
import org.utils.LoggerSingleton;

import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class DriverChrome implements Driver {
    public static JSONHandler jsonConfigHandler = new JSONHandler("src/main/java/org/utils/config.json");

    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    protected static Logger logger = LoggerSingleton.getInstance().getLogger();

    private DriverChrome(){}

    public static WebDriver getInstance() {
        if (driver.get() == null) {
            logger.info("driver is null -  creating new one");
            ChromeOptions options = new ChromeOptions();

            options.addArguments(jsonConfigHandler.getValue("language_chrome"));
            options.addArguments("--headless"); // Add this line to enable headless mode

            Map<String, Object> prefs = new HashMap<>();
            String downloadPath = Paths.get(System.getProperty("user.dir"),
                    jsonConfigHandler.getValue("download_directory")).toString();
            prefs.put("download.default_directory", downloadPath);
            options.setExperimentalOption("prefs", prefs);

            driver.set(new ChromeDriver(options));
        }
        return driver.get();
    }


    public static void quit() {
        logger.info("quitting driver");
        driver.get().quit();
        driver.remove();
    }
}