package com.germaniumhq.germanium.points;

import com.germaniumhq.germanium.all.GermaniumApi;
import com.germaniumhq.germanium.impl.ScriptLoader;
import org.openqa.selenium.WebElement;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Box {
    private Map<String, Integer> box;
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

    public int width() {
        if (this.box == null) {
            this.getBox();
        }

        return this.box.get("width");
    }

    public int height() {
        if (this.box == null) {
            this.getBox();
        }

        return this.box.get("height");
    }

    public int left() {
        if (this.box == null) {
            this.getBox();
        }

        return this.box.get("left");
    }

    public int right() {
        if (this.box == null) {
            this.getBox();
        }

        return this.box.get("right");
    }

    public int top() {
        if (this.box == null) {
            this.getBox();
        }

        return this.box.get("top");
    }

    public int bottom() {
        if (this.box == null) {
            this.getBox();
        }

        return this.box.get("bottom");
    }

    public Box getBox() {
        String code = ScriptLoader.getScript("/germanium/points/box.min.js");

        List<Long> positions = GermaniumApi.js(code, GermaniumApi.getGermanium()
                .<WebElement>S(this.selector).element());

        this.box = new HashMap<>();

        this.box.put("top", positions.get(0).intValue());
        this.box.put("right", positions.get(1).intValue());
        this.box.put("bottom", positions.get(2).intValue());
        this.box.put("left", positions.get(3).intValue());
        this.box.put("center", positions.get(4).intValue());
        this.box.put("middle", positions.get(5).intValue());
        this.box.put("width", positions.get(6).intValue());
        this.box.put("height", positions.get(7).intValue());

        return this;
    }
}
