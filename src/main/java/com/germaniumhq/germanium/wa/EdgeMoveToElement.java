package com.germaniumhq.germanium.wa;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.CompositeAction;

public class EdgeMoveToElement {
    public static boolean isEdgeBrowser() {
        //capabilities = germanium.web_driver.capabilities
        //return capabilities['browserName'].lower() == 'microsoftedge'
        return false;
    }



    public static void moveToElement(CompositeAction actionChain, WebElement element) {
    }
}
