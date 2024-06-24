package org.pages;

import org.apache.logging.log4j.Logger;
import org.elements.BaseElement;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.utils.LoggerSingleton;
import org.utils.Waiter;
import org.utils.driver.DriverFactory;

public class BasePage {
    protected String name;
    protected static Logger logger = LoggerSingleton.getInstance().getLogger();
    protected final Waiter waiter = Waiter.getInstance();
    private final String jsScriptScrollDown = "window.scrollTo(0, document.body.scrollHeight)";
    private final String jsScriptScrollUp = "window.scrollTo({top: 0})";
    private final String jsScriptScrollElement = "arguments[0].scrollIntoView(true);";

    public BasePage(String name) {
        this.name = name;
        logger.info("creating page named {}",name);
        PageFactory.initElements(DriverFactory.getDriver(), this);
    }


    public boolean isRightPage(BaseElement element) {
        logger.info("checking if we are on the right page {} by element\n",name);
        return element.isVisible();
    }

    public void scrollDownPage() {
        logger.info("scrolling down page {}", name);
        ((JavascriptExecutor) DriverFactory.getDriver()).executeScript(jsScriptScrollDown);
    }

    public void scrollUpPage() {
        logger.info("scrolling up page {}", name);
        ((JavascriptExecutor) DriverFactory.getDriver()).executeScript(jsScriptScrollUp);
    }

    public void scrollElement(WebElement element) {
        logger.info("scrolling to element on page {}",name);
        ((JavascriptExecutor) DriverFactory.getDriver()).executeScript(jsScriptScrollElement, element);

    }



}
