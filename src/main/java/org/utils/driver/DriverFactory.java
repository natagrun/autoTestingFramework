package org.utils.driver;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.utils.JSONHandler;
import org.utils.LoggerSingleton;

public class DriverFactory {
    protected static Logger logger = LoggerSingleton.getInstance().getLogger();
    public static JSONHandler jsonConfigHandler = new JSONHandler("src/main/java/org/utils/config.json");
    private static final String browser = jsonConfigHandler.getValue("browser");
    private DriverFactory() {}

    public static WebDriver getDriver() {
        if (browser.equalsIgnoreCase("Chrome")) {
            logger.info("creating chrome driver");
            return DriverChrome.getInstance();
        } else if (browser.equalsIgnoreCase("Firefox")) {
            logger.info("creating firefox driver");
            return DriverFirefox.getInstance();
        } else {
            logger.error("Invalid driver type: {}", browser);
            throw new IllegalArgumentException();
        }
    }
}
