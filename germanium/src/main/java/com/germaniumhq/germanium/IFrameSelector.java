package com.germaniumhq.germanium;

/**
 * Strategy to allow selecting the IFrame that it's going
 * to be active.
 */
public interface IFrameSelector {
    String selectIFrame(GermaniumDriver germanium, String iFrameName);
}
