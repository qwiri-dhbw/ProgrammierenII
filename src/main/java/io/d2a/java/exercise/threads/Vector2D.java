package io.d2a.java.exercise.threads;

public class Vector2D {

    public double x;
    public double y;

    public Vector2D(double x, double y) {
        this.update(x, y);
    }

    public void update(final double x, final double y) {
        this.x = x;
        this.y = y;
    }

    public double distance(final Vector2D other) {
        final double dX = Math.max(this.x, other.x) - Math.min(this.x, other.x);
        final double dY = Math.max(this.y, other.y) - Math.min(this.y, other.y);
        return Math.sqrt(dX * dX + dY * dY);
    }

    public Vector2D sub(final Vector2D other) {
        return new Vector2D(
                this.x - other.x,
                this.y - other.y
        );
    }

    public Vector2D add(final Vector2D other) {
        return new Vector2D(
                this.x + other.x,
                this.y + other.y
        );
    }

    public Vector2D mult(final double mult) {
        return new Vector2D(this.x * mult, this.y * mult);
    }

    public Vector2D normalize() {
        final double max = Math.max(Math.abs(this.x), Math.abs(this.y));
        return new Vector2D(
                this.x / max,
                this.y / max
        );
    }

    public int iX() {
        return (int) this.x;
    }

    public int iY() {
        return (int) this.y;
    }

    public Vector2D clone() {
        return new Vector2D(this.x, this.y);
    }

    @Override
    public String toString() {
        return "Vector2D{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

}
