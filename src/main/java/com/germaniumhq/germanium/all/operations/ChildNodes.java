package com.germaniumhq.germanium.all.operations;

import com.germaniumhq.germanium.all.GermaniumApi;
import com.germaniumhq.germanium.impl.ScriptLoader;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ChildNodes {
    public static enum ElementType {
        /**
         * This will match only nodes that are tags. Comments, text nodes,
         * etc, are filtered out from the output.
         */
        ONLY_ELEMENTS,

        /**
         * This will match all the nodes, including comments, text nodes, etc.
         */
        ALL_NODES
    }

    /**
     * Return the children nodes of the element that will be resolved
     * from the selector.
     *
     * @param selector
     * @param elementType
     * @return
     */
    public static List<WebElement> getChildNodes(Object selector, ElementType elementType) {
        String code = ScriptLoader.getScript("/germanium/util/child-nodes.min.js");
        WebElement element = GermaniumApi.getGermanium().<WebElement>S(selector).element();

        return GermaniumApi.getGermanium().js(
                code,
                element,
                elementType == ElementType.ONLY_ELEMENTS);
    }
}
