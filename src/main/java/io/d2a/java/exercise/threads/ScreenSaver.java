package io.d2a.java.exercise.threads;

import io.d2a.util.ColorRainbow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ScreenSaver extends JFrame implements Runnable {

    public static final int FPS = 60;
    private final CircleComponent comp;
    private int frame = 0;

    public ScreenSaver() {
        super("Resizing Circle");
        this.setSize(1280 / 2, 720 / 2);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        this.comp = new CircleComponent();
        comp.setPreferredSize(this.getSize());
        this.add(comp);

        this.setBackground(Color.RED);
        this.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));

        new Thread(this).start();

        this.setVisible(true);
    }

    public static void main(String[] args) throws InterruptedException {
        new ScreenSaver();
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

    public class CircleComponent extends JPanel {

        private final Bouncer start = new Bouncer(new Vector2D(
                100,100
        ), new SpeedVector2D(1, -1.2, 1.5));
        private final Bouncer end = new Bouncer(new Vector2D(
                100,100
        ), new SpeedVector2D(-1, -1, 2));
        private MouseListener mouseListener;

        public CircleComponent() {
            this.addMouseListener(this.mouseListener = new MouseListener());
            this.addMouseMotionListener(this.mouseListener);
            this.addComponentListener(new ComponentAdapter() {
                @Override
                public void componentResized(ComponentEvent e) {
                    start.position.update(getWidth()/2.0, getHeight()/2.0);
                    end.position.update(getWidth()/2.0, getHeight()/2.0);
                }
            });
        }

        @Override
        public void paintComponent(final Graphics _g) {
            if (!(_g instanceof Graphics2D g)) {
                return;
            }
            this.setPreferredSize(ScreenSaver.this.getSize());

            int stripes = (int) Math.ceil((this.end.position.distance(this.start.position) / getWidth()) * 100);

            final int maxRadius = Math.min(getHeight(), getWidth()) / 4;

            this.end.tick(maxRadius*2, maxRadius*2, 0, getWidth(), 0, getHeight());

            {
                final int radius = maxRadius - (int) ((stripes) * 1.5);
                this.start.tick(radius*2, radius*2, 0, getWidth(), 0, getHeight());
            }

            System.out.println(this.end.position);

             // final Vector2D center = new Vector2D(getWidth() / 2.0, getHeight() / 2.0);
            // final Vector2D u = this.mouseListener.mouse.sub(center);
            final Vector2D u = this.end.position.sub(this.start.position);

            // set background color
            g.setColor(Color.BLACK);
            g.fillRect(0, 0, getWidth(), getHeight());
            g.setStroke(new BasicStroke(2.0f));


            float hue = .1f;
            for (int i = 0; i < stripes; i++) {
                int radius = maxRadius - (int) ((stripes - i) * 1.5);
                hue += 1.0/(stripes + 1);

                final double m = (double) i / (double) stripes;
                final Vector2D offset = this.start.position.add(u.mult(m));

                final Color color = ColorRainbow.hslColor( hue, 1, .5f);
                g.setColor(color);

                g.drawOval(
                        offset.iX() - radius,
                        offset.iY() - radius,
                        radius * 2,
                        radius * 2);
            }

        }

        class MouseListener extends MouseAdapter {
            public Vector2D mouse = new Vector2D(0, 0);
            @Override
            public void mouseMoved(MouseEvent e) {
                if (e.isShiftDown()) {
                    start.position.update(e.getX(), e.getY());
                } else if (e.isAltDown()) {
                    end.position.update(e.getX(), e.getY());
                }
                if (this.mouse == null) {
                    this.mouse = new Vector2D(e.getX(), e.getY());
                } else {
                    this.mouse.update(e.getX(), e.getY());
                }
            }
        }
    }


}
