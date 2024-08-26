package org.utils;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.utils.driver.DriverFactory;

import java.io.File;
import java.time.Duration;

import static org.openqa.selenium.support.ui.ExpectedConditions.numberOfWindowsToBe;


public class Waiter {

    public static JSONHandler jsonConfigHandler = new JSONHandler("src/main/java/org/utils/config.json");
    protected static Logger logger = LoggerSingleton.getInstance().getLogger();
    protected static int timeout = Integer.parseInt(jsonConfigHandler.getValue("timeout"));
    public static WebDriverWait wait = new WebDriverWait(DriverFactory.getDriver(), Duration.ofSeconds(timeout));

    private final String blankPageUrl = "about:blank";

    private Waiter() {
    }

    private static final class InstanceHolder {
        private static final Waiter instance = new Waiter();
    }

    public static Waiter getInstance() {
        return InstanceHolder.instance;
    }

    public void waitClickable(WebElement element) {
        logger.info("waiting for element to be clickable");
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public void waitVisibility(WebElement element) {
        logger.info("waiting for element to be visible");
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void waitAttrBe(WebElement element, String attr, String value) {
        logger.info("waiting for elements attribute {} get {} value", attr, value);
        wait.pollingEvery(Duration.ofMillis(50));
        wait.until(ExpectedConditions.attributeToBe(element, attr, value));
    }

    public void waitTextBe(String locator, String value) {
        logger.info("waiting for elements text to be {}", value);
        wait.until(ExpectedConditions.textToBe(By.xpath(locator), value));
    }

    public void waitTextAny(WebElement element) {
        logger.info("waiting for element to have text");
        wait.until(ExpectedConditions.textToBePresentInElement(element, ""));
    }

    public void waitInvisibility(WebElement element) {
        logger.info("waiting for element to be invisible");
        wait.until(ExpectedConditions.invisibilityOf(element));
    }

    public Alert waitAlertVisible() {
        logger.info("waiting for alerts to be visible");
        return wait.until(ExpectedConditions.alertIsPresent());
    }

    public void waitNumberPagesBe(int num) {
        logger.info("waiting for number of pages be {}", num);
        wait.until(numberOfWindowsToBe(num));
    }


    public static File waitForFileDownload(String downloadDirectory) {
        return wait.until(
                webDriver -> {
                    File downloadedDir = new File(downloadDirectory);
                    if (downloadedDir.exists() && downloadedDir.isDirectory()) {
                        File[] files = downloadedDir.listFiles();
                        if (files != null && files.length > 0) {
                            return files[0];
                        }
                    }
                    return null;
                }
        );
    }

    public void waitPageLoaded() {
        wait.until(ExpectedConditions.not(ExpectedConditions.urlToBe(blankPageUrl)));
    }

}

