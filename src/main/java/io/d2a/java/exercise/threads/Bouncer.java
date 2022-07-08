package io.d2a.java.exercise.threads;

public class Bouncer {

    public final Vector2D position;
    private final SpeedVector2D velocity;

    public Bouncer(final Vector2D position, final SpeedVector2D velocity) {
        this.position = position;
        this.velocity = velocity;
    }

    public boolean check(
            final int width,
            final int height,
            final int xMin,
            final int xMax,
            final int yMin,
            final int yMax
    ) {
        boolean resp = false;
        final double wH = width / 2.0, hH = height / 2.0;
        if (this.position.x - wH <= xMin || this.position.x + wH >= xMax) {
            this.velocity.x *= -1;
            resp = true;
        }
        if (this.position.y - hH <= yMin || this.position.y + hH >= yMax) {
            this.velocity.y *= -1;
            resp = true;
        }
        return resp;
    }

    public void tick(
            final int width,
            final int height,
            final int xMin,
            final int xMax,
            final int yMin,
            final int yMax
    ) {
        this.position.x += this.velocity.sX();
        this.position.y += this.velocity.sY();
        if (this.check(width, height, xMin, xMax, yMin, yMax)) {
        }
    }

}
