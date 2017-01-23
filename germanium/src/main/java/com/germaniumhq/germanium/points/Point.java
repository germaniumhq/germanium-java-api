package com.germaniumhq.germanium.points;

public class Point {
    private int x;
    private int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Point adjustX(int x) {
        return new Point(this.x + x, this.y);
    }

    public Point adjustY(int y) {
        return new Point(this.x, this.y + y);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
