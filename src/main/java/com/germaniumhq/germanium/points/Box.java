package com.germaniumhq.germanium.points;

import com.germaniumhq.germanium.all.GermaniumApi;
import com.germaniumhq.germanium.impl.ScriptLoader;
import org.openqa.selenium.WebElement;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Box {
    private Map<String, Float> box;
    private Object selector;

    public Box(Object selector) {
        this.selector = selector;
    }

    public Point topLeft() {
        if (this.box == null) {
            this.getBox();
        }

        return new Point(this.box.get("left"),
                         this.box.get("top"));
    }

    public Point topCenter() {
        if (this.box == null) {
            this.getBox();
        }

        return new Point(this.box.get("center"), this.box.get("top"));
    }

    public Point topRight() {
        if (this.box == null) {
            this.getBox();
        }

        return new Point(this.box.get("right"), this.box.get("top"));
    }

    public Point middleLeft() {
        if (this.box == null) {
            this.getBox();
        }

        return new Point(this.box.get("left"), this.box.get("middle"));
    }

    public Point middleRight() {
        if (this.box == null) {
            this.getBox();
        }

        return new Point(this.box.get("right"), this.box.get("middle"));
    }

    public Point bottomLeft() {
        if (this.box == null) {
            this.getBox();
        }

        return new Point(this.box.get("left"), this.box.get("bottom"));
    }

    public Point bottomCenter() {
        if (this.box == null) {
            this.getBox();
        }

        return new Point(this.box.get("center"), this.box.get("bottom"));
    }

    public Point bottomRight() {
        if (this.box == null) {
            this.getBox();
        }

        return new Point(this.box.get("right"), this.box.get("bottom"));
    }

    public Point center() {
        if (this.box == null) {
            this.getBox();
        }

        return new Point(this.box.get("center"), this.box.get("middle"));
    }

    public float width() {
        if (this.box == null) {
            this.getBox();
        }

        return this.box.get("width");
    }

    public float height() {
        if (this.box == null) {
            this.getBox();
        }

        return this.box.get("height");
    }

    public float left() {
        if (this.box == null) {
            this.getBox();
        }

        return this.box.get("left");
    }

    public float right() {
        if (this.box == null) {
            this.getBox();
        }

        return this.box.get("right");
    }

    public float top() {
        if (this.box == null) {
            this.getBox();
        }

        return this.box.get("top");
    }

    public float bottom() {
        if (this.box == null) {
            this.getBox();
        }

        return this.box.get("bottom");
    }

    public Box getBox() {
        String code = ScriptLoader.getScript("/germanium/points/box.min.js");

        List<Float> positions = GermaniumApi.js(code, GermaniumApi.getGermanium()
                .<WebElement>S(this.selector));

        this.box = new HashMap<>();

        this.box.put("top", positions.get(0));
        this.box.put("right", positions.get(1));
        this.box.put("bottom", positions.get(2));
        this.box.put("left", positions.get(3));
        this.box.put("center", positions.get(4));
        this.box.put("middle", positions.get(5));
        this.box.put("width", positions.get(6));
        this.box.put("height", positions.get(7));

        return this;
    }
}
