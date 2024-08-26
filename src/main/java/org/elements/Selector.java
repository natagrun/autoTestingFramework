package org.elements;

import org.openqa.selenium.support.ui.Select;

public class Selector extends BaseElement{
    public Selector(String name, String locator) {
        super(name, locator);
    }
    public void selectValue(int value) {
        findElement().isDisplayed();
        Select select = new Select(findElement());
        select.selectByValue(String.valueOf(value));
    }
}
