package org.utils;

import org.apache.logging.log4j.Logger;
import org.utils.driver.DriverFactory;

public class PagesSwitch {
    private final Waiter waiter = Waiter.getInstance();
    private String originalWindow;
    private String tempWindow;

    protected static Logger logger = LoggerSingleton.getInstance().getLogger();

    public void getOriginTab(){
        logger.info("memorizing origin tab");
        originalWindow = DriverFactory.getDriver().getWindowHandle();
    }
    public void getTempTab(){
        logger.info("memorizing temporary tab");
        tempWindow = DriverFactory.getDriver().getWindowHandle();
    }
    public void closeTempTab(){
        logger.info("closing temporary tab");
        int num = DriverFactory.getDriver().getWindowHandles().size()-1;
        closeActiveTab();
        numPagesGot(num);
        DriverFactory.getDriver().switchTo().window(originalWindow);
    }

    public void exploreNewTab(){
        logger.info("searching for new opened tab");
        for (String windowHandle : DriverFactory.getDriver().getWindowHandles()) {
            if(!originalWindow.contentEquals(windowHandle)) {
                logger.info("found new tab, switching to it");
                DriverFactory.getDriver().switchTo().window(windowHandle);
                break;
            }
        }
    }

    public void switchOriginTab(){
        logger.info("switching to origin tab");
        DriverFactory.getDriver().switchTo().window(originalWindow);
    }

    public void switchTempTab(){
        logger.info("switching to temporary tab");
        DriverFactory.getDriver().switchTo().window(tempWindow);
    }
    public void switchChildFrame(){
        logger.info("switching to child frame");
        DriverFactory.getDriver().switchTo().frame(0);
    }
    public void numPagesGot(int num){
        logger.info("number of pages become two");
        waiter.waitNumberPagesBe(num);
    }

    public void closeActiveTab(){
        logger.info("closing active tab");
        DriverFactory.getDriver().close();
    }
}
