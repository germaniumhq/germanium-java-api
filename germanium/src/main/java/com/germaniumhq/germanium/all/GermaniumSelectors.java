package com.germaniumhq.germanium.all;

import com.germaniumhq.germanium.points.Box;
import com.germaniumhq.germanium.selectors.Alert;
import com.germaniumhq.germanium.selectors.AnyOfSelector;
import com.germaniumhq.germanium.selectors.Button;
import com.germaniumhq.germanium.selectors.CheckBox;
import com.germaniumhq.germanium.selectors.Css;
import com.germaniumhq.germanium.selectors.Element;
import com.germaniumhq.germanium.selectors.Image;
import com.germaniumhq.germanium.selectors.Input;
import com.germaniumhq.germanium.selectors.InputFile;
import com.germaniumhq.germanium.selectors.InputText;
import com.germaniumhq.germanium.selectors.JsSelector;
import com.germaniumhq.germanium.selectors.Link;
import com.germaniumhq.germanium.selectors.Selector;
import com.germaniumhq.germanium.selectors.StaticElement;
import com.germaniumhq.germanium.selectors.Text;
import com.germaniumhq.germanium.selectors.Window;
import com.germaniumhq.germanium.selectors.XPath;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * Shorthand functions to return selectors instead of being
 * forced to call "new "
 */
public class GermaniumSelectors {
    public static AnyOfSelector AnyOfSelector(Selector<WebElement> ... usedSelectors) {
        return new AnyOfSelector(usedSelectors);
    }

    public static Alert Alert() {
        return new Alert();
    }

    public static Button Button(String searchedText) {
        return new Button(searchedText);
    }

    public static Button Button() {
        return new Button();
    }

    public static CheckBox CheckBox(String name) {
        return new CheckBox(name);
    }
    public static CheckBox CheckBox() {
        return new CheckBox();
    }

    public static Box Box(Object selector) {
        return new Box(selector);
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

    public static InputFile InputFile() {
        return new InputFile();
    }

    public static InputFile InputFile(String name) {
        return new InputFile(name);
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

    public static StaticElement StaticElement(WebElement elements) {
        return new StaticElement(elements);
    }

    public static StaticElement StaticElement(List<WebElement> elements) {
        return new StaticElement(elements);
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
