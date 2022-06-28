package io.d2a.java.exercise.threads;

import io.d2a.java.exercise.threads.Letters.Letter;
import io.d2a.java.exercise.ui.data.util.presets.PaddedFrame;
import io.d2a.util.ColorRainbow;

import javax.swing.*;
import javax.swing.event.MouseInputListener;
import java.awt.*;
import java.awt.event.MouseEvent;

public class DancingText extends PaddedFrame implements Runnable {

    public static void main(String[] args) throws InterruptedException {
        final DancingText dancingText = new DancingText();
        dancingText.thread.join();
    }

    private final Letters letters = new Letters("Dancing Text :-) Hello World what's up?");
    private final Thread thread;

    public DancingText() {
        super("Dancing Text");
        this.setSize(720, 480);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        final TextComponent comp = new TextComponent();
        this.add(comp);

        this.thread = new Thread(this);
        this.thread.start();
    }


    @Override
    @SuppressWarnings({"InfiniteLoopStatement", "BusyWait"})
    public void run() {
        while (true) {
            try {
                Thread.sleep(30);
            } catch (InterruptedException ignored) {
            }
            this.letters.cycle();
            this.repaint();
        }
    }

    public class TextComponent extends JPanel implements MouseInputListener {
        private static final Font font = new Font("Comic Code Ligatures", Font.BOLD, 32);
        float hue = 0;

        public TextComponent() {
            this.addMouseMotionListener(this);
            this.addMouseListener(this);
        }

        @Override
        public void paintComponent(final Graphics g) {
            if (DancingText.this.letters == null) {
                return;
            }
            this.setPreferredSize(DancingText.this.getSize());

            // set background color
            g.setColor(Color.LIGHT_GRAY);
            g.fillRect(0, 0, getWidth(), getHeight());

            // set default color to black and font for text
            g.setColor(Color.BLACK);
            g.setFont(font);

            int x = 30;
            final int yStart = getHeight() / 2;

            // draw separate letters
            for (final Letter letter : DancingText.this.letters.getLetters()) {
                // cycle rainbow color
                g.setColor(ColorRainbow.hslColor(Math.min(1.0f, (hue += .0003) * 1.4f), 1f, .5f));
                g.drawString(
                        String.valueOf(letter.getC()),
                        x += 30,
                        yStart + letter.getY()
                );
            }

            // reset rainbow cycle
            if (hue >= 1) {
                hue = 0;
            }
        }


        @Override
        public void mouseDragged(final MouseEvent e) {
            // hier könnte Ihre Werbung stehen
            // https://github.com/darmiel/sponsors
        }

        @Override
        public void mouseMoved(final MouseEvent e) {
            final double multiplierFreq = (double) e.getX() / (double) getWidth();
            final double multiplierClinch = (double) e.getY() / (getHeight() / 2.0);
            DancingText.this.letters.setMultiplierFreq(multiplierFreq);
            DancingText.this.letters.setMultiplierClench(multiplierClinch);
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            DancingText.this.letters.toggle();
        }

        @Override
        public void mousePressed(MouseEvent e) {
            // hier könnte Ihre Werbung stehen
            // https://github.com/darmiel/sponsors
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            // hier könnte Ihre Werbung stehen
            // https://github.com/darmiel/sponsors
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            // hier könnte Ihre Werbung stehen
            // https://github.com/darmiel/sponsors
        }

        @Override
        public void mouseExited(MouseEvent e) {
            // hier könnte Ihre Werbung stehen
            // https://github.com/darmiel/sponsors
        }

    }

}
