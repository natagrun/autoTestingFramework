package org.elements;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.utils.LoggerSingleton;
import org.utils.Waiter;
import org.utils.driver.DriverFactory;

import java.util.List;

public class BaseElement {
    protected String name;
    protected By locator;
    protected final Waiter waiter = Waiter.getInstance();
    protected static Logger logger = LoggerSingleton.getInstance().getLogger();

    public BaseElement(String name, String locator) {
        this.name = name;
        this.locator = By.xpath(locator);
        logger.info("Created element name '{}' by locator :'{}'", name, locator);
    }

    public WebElement findElement() {
        logger.info("finding '{}' element", name);
        return DriverFactory.getDriver().findElement(locator);
    }

    public String getLocator(){
        return locator.toString();
    }

    public List<WebElement> findChildElement(String locator) {
        logger.info("finding children of '{}' element", name);
        return this.findElement().findElements(By.xpath(locator));
    }

    public void click() {
        logger.info("clicking on '{}' element\n", name);

        waiter.waitClickable(findElement());
        findElement().click();
    }

    public void moveCursorElement() {
        logger.info("moving cursor on '{}' element", name);
        Actions action = new Actions(DriverFactory.getDriver());
        action.moveToElement(findElement());
        action.perform();
    }

    public String getAttribute(String attr) {
        logger.info("getting {} attribute of '{}' element", attr, name);
        waiter.waitVisibility(findElement());
        return findElement().getAttribute(attr);
    }

    public boolean isVisible() {
        logger.info("checking if '{}' element is visible", name);
        waiter.waitVisibility(findElement());
        return findElement().isDisplayed();
    }


    public boolean isSelected() {
        logger.info("checking if '{}' element is selected", name);
        waiter.waitVisibility(findElement());
        return findElement().isSelected();
    }

    public boolean hasAttribute(String attributeName) {
        logger.info("checking {} element has '{}' attribute", name, attributeName);
        waiter.waitVisibility(findElement());
        return findElement().getAttribute(attributeName).isEmpty();
    }


    public String getText() {
        waiter.waitTextAny(findElement());
        logger.info("getting text from '{}' element\n ", name);
        return findElement().getText();
    }


}
