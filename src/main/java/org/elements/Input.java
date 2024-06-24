package org.elements;

public class Input extends BaseElement {

    public Input(String name, String locator) {
        super(name, locator);
    }

    public void insertText(String text){
        findElement().sendKeys(text);
    }

    public void clearText(){
        findElement().clear();
    }
}
