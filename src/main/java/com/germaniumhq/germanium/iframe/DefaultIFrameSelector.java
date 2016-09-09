package com.germaniumhq.germanium.iframe;

import com.germaniumhq.germanium.GermaniumDriver;
import com.germaniumhq.germanium.IFrameSelector;

/**
 * The default IFrame selector always switches to the `default`
 * IFrame.
 */
public class DefaultIFrameSelector implements IFrameSelector {
    @Override
    public String selectIFrame(GermaniumDriver germanium, String iFrameName) {
        if (!"default".equalsIgnoreCase(iFrameName)) {
            throw new IllegalArgumentException(String.format(
                    "Unknown iframe name: '%s'. Make sure you create an IFrame Selector " +
                    "that you will pass when creating the GermaniumDriver, e.g.:\n" +
                    "new GermaniumDriver(wd, MyIFrameSelector())",
                    iFrameName
            ));
        }

        germanium.switchTo().defaultContent();

        return iFrameName;
    }
}
