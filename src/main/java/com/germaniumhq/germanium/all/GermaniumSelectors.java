package com.germaniumhq.germanium.all;

import com.germaniumhq.germanium.selectors.Alert;
import com.germaniumhq.germanium.selectors.Button;
import com.germaniumhq.germanium.selectors.Css;
import com.germaniumhq.germanium.selectors.Element;
import com.germaniumhq.germanium.selectors.Image;
import com.germaniumhq.germanium.selectors.Input;
import com.germaniumhq.germanium.selectors.InputText;
import com.germaniumhq.germanium.selectors.JsSelector;
import com.germaniumhq.germanium.selectors.Link;
import com.germaniumhq.germanium.selectors.Text;
import com.germaniumhq.germanium.selectors.Window;
import com.germaniumhq.germanium.selectors.XPath;

/**
 * Shorthand functions to return selectors instead of being
 * forced to call "new "
 */
public class GermaniumSelectors {
    public static Alert Alert() {
        return new Alert();
    }

    public static Button Button() {
        return new Button();
    }

    public static Css Css(String css) {
        return new Css(css);
    }

    public static Element Element(String tagName) {
        return new Element(tagName);
    }

    public static Image Image(String alt) {
        return new Image(alt);
    }

    public static Image Image() {
        return new Image();
    }

    public static Input Input() {
        return new Input();
    }

    public static InputText InputText() {
        return new InputText();
    }

    public static InputText InputText(String placeholder) {
        return new InputText(placeholder);
    }

    public static JsSelector JsSelector(String code) {
        return new JsSelector(code);
    }

    public static Link Link() {
        return new Link();
    }

    public static Link Link(String searchText) {
        return new Link(searchText);
    }

    public static Text Text(String text) {
        return new Text(text);
    }

    public static Window Window() {
        return new Window();
    }

    public static XPath XPath(String xpath) {
        return new XPath(xpath);
    }
}
