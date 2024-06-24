package org.elements;

import org.openqa.selenium.Keys;

public class Slider extends BaseElement {

    public Slider(String name, String locator) {
        super(name, locator);
    }

    public int getSliderValue(){
        return Integer.parseInt(findElement().getAttribute("value"));
    }

    public void dragDrop(int targetValue) {
        logger.info("preparing to drag and drop slider");
        boolean direction = getSliderValue()<targetValue;
        logger.warn("dragging and dropping slider");
        while(getSliderValue()!=(targetValue)){
            if (direction) findElement().sendKeys(Keys.ARROW_RIGHT);
            else findElement().sendKeys(Keys.ARROW_LEFT);
        }


    }
}
