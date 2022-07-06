package io.d2a.java.exercise.threads;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

public class DancingBall extends JFrame implements Runnable {

    public static final int FPS = 60;
    public static final boolean DRAW_IMAGE = false;
    private final Image ballImage;
    private final BallComponent comp; // component which displays the text
    private int frame = 0;

    {
        try {
            ballImage = ImageIO.read(new File("dvd-log.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public DancingBall() {
        super("Dancing Balls");
        this.setSize(1280 / 2, 720 / 2);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        this.comp = new BallComponent();
        comp.setPreferredSize(this.getSize());
        this.add(comp);

        this.setBackground(Color.RED);
        this.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));

        this.setVisible(true);

        new Thread(this).start();
    }

    public static void main(String[] args) throws InterruptedException {
        new DancingBall();
    }

    @Override
    @SuppressWarnings({"InfiniteLoopStatement", "BusyWait"})
    public void run() {
        while (true) {
            try {
                Thread.sleep(1000 / FPS);
            } catch (InterruptedException ignored) {
            }
            this.frame = (this.frame + 1) % FPS;
            this.repaint();
        }
    }

    public class BallComponent extends JPanel {

        private MouseListener mouse;
        private double airTime = 0;
        private Point ballLocation;
        private SpeedVector2D ballVelocity;
        private Point trace = null;
        private boolean speedster = false;
        public BallComponent() {
            this.addMouseListener(this.mouse = new MouseListener());
            this.addMouseMotionListener(this.mouse);
        }

        @Override
        public void paintComponent(final Graphics _g) {
            if (!(_g instanceof Graphics2D g)) {
                return;
            }
            this.setPreferredSize(DancingBall.this.getSize());

            // set background color
            g.setColor(Color.BLACK);
            g.fillRect(0, 0, getWidth(), getHeight());

            // draw borders
            g.setColor(Color.BLUE);
            g.fillRect(0, 0, getWidth(), 5);
            g.fillRect(0, 0, 5, getHeight());
            g.fillRect(0, getHeight() - 5, getWidth(), 5);
            g.fillRect(getWidth() - 5, 0, 5, getHeight());

            if (this.mouse.dragging) {
                // calculate length
                final double length = this.mouse.start.distance(this.mouse.end);

                final Color color = new Color(
                        Math.min(255, (int) length / 2),
                        255 - Math.min(255, (int) length / 2),
                        0
                );
                g.setStroke(new BasicStroke(5));
                g.setColor(color);
                g.drawLine(this.mouse.start.iX(), this.mouse.start.iY(), this.mouse.end.iX(), this.mouse.end.iY());
            }

            if (this.ballLocation != null && this.ballVelocity != null) {
                int radius = 60;

                final Vector2D center = new Vector2D(getWidth() / 2.0, getHeight() / 2.0);
                final double distanceToCenter = center.distance(new Vector2D(this.ballLocation.x, this.ballLocation.y));

                radius += (int) ((((getWidth() / 2.0) - distanceToCenter) / (getWidth() / 2.0)) * 50.0);

                // check collisions
                if (this.ballLocation.x <= radius || this.ballLocation.x >= (getWidth() - radius)) {
                    this.ballVelocity.x = -this.ballVelocity.x;
                    this.trace = (Point) this.ballLocation.clone();
                }
                if (this.ballLocation.y <= radius || this.ballLocation.y >= (getHeight() - radius)) {
                    this.ballVelocity.y = -this.ballVelocity.y;
                    this.trace = (Point) this.ballLocation.clone();
                }

                // speed control
                if (this.speedster) {
                    this.ballVelocity.decrease(-.05);
                } else {
                    this.ballVelocity.decrease(.005);
                }

                // move ball
                this.ballLocation.setLocation(
                        this.ballLocation.x + this.ballVelocity.sX(),
                        this.ballLocation.y + this.ballVelocity.sY()
                );

                // draw ball
                g.setColor(Color.RED);
                if (DRAW_IMAGE) {
                    g.drawImage(
                            ballImage,
                            this.ballLocation.x - radius,
                            this.ballLocation.y - radius,
                            2*radius,
                            2*radius,
                            null
                    );
                } else {
                    g.fillOval(
                            this.ballLocation.x - radius,
                            this.ballLocation.y - radius,
                            2 * radius,
                            2 * radius
                    );
                }

                if (trace != null) {
                    g.setColor(Color.GRAY);
                    g.drawLine(trace.x, trace.y, this.ballLocation.x, this.ballLocation.y);

                    if (this.mouse.mouse != null) {
                        g.setColor(Color.YELLOW);
                        g.drawLine(this.mouse.mouse.iX(), this.mouse.mouse.iY(), this.ballLocation.x, this.ballLocation.y);
                    }
                }
            }
        }

        class MouseListener extends MouseAdapter {
            public Vector2D start, end;
            public Vector2D mouse = null;
            public boolean dragging = false;

            @Override
            public void mousePressed(MouseEvent e) {
                // give more speed
                if (e.getButton() == MouseEvent.BUTTON3) {
                    speedster = true;
                    return;
                }
                if (e.isShiftDown()) {
                    if (ballLocation != null) {
                        this.end = new Vector2D(ballLocation.x, ballLocation.y);
                        this.start = new Vector2D(e.getX(), e.getY());

                        this.startBall(this.start, this.end);
                        this.dragging = false;
                    }
                    return;
                }
                this.start = new Vector2D(e.getX(), e.getY());
                this.end = null;
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                if (e.getButton() != MouseEvent.BUTTON1) {
                    return;
                }
                this.dragging = true;
                if (this.end == null) {
                    this.end = new Vector2D(e.getX(), e.getY());
                } else {
                    this.end.update(e.getX(), e.getY());
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON3) {
                    speedster = false;
                    return;
                }
                if (e.isShiftDown()) {
                    return;
                }
                this.dragging = false;

                this.end = new Vector2D(e.getX(), e.getY());
                this.startBall(this.start, this.end);
            }

            private void startBall(final Vector2D start, final Vector2D end) {
                ballLocation = new Point(start.iX(), start.iY());
                ballVelocity = SpeedVector2D.wrap(start.sub(end).normalize(), start.distance(end) / 10.0);
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                if (this.mouse == null) {
                    this.mouse = new Vector2D(e.getX(), e.getY());
                } else {
                    this.mouse.update(e.getX(), e.getY());
                }
            }
        }
    }


}
