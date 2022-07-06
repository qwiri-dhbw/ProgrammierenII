package io.d2a.java.exercise.threads;

public class SpeedVector2D extends Vector2D {

    public double speed;

    public SpeedVector2D(double x, double y, final double speed) {
        super(x, y);
        this.speed = speed;
    }

    public static SpeedVector2D wrap(final Vector2D vec, final double speed) {
        return new SpeedVector2D(vec.x, vec.y, speed);
    }

    public void decrease(final double a) {
        this.speed -= a;
        if (this.speed < 0) {
            this.speed = 0;
        }
    }

    public double sX() {
        return this.speed * this.x;
    }

    public double sY() {
        return this.speed * this.y;
    }

    @Override
    public String toString() {
        return "SpeedVector2D{" +
                "speed=" + speed +
                ", x=" + x +
                ", y=" + y +
                '}';
    }
}
