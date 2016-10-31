package com.germaniumhq.germanium.points;

public class Point {
    private float x;
    private float y;

    public Point(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public Point adjustX(float x) {
        return new Point(this.x + x, this.y);
    }

    public Point adjustY(float y) {
        return new Point(this.x, this.y + y);
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }
}
