package io.d2a.java.exercise.threads;

import io.d2a.java.exercise.threads.Letters.Letter;
import io.d2a.java.exercise.ui.data.util.presets.PaddedFrame;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class DancingText extends PaddedFrame implements Runnable {

    public static void main(String[] args) throws InterruptedException {
        final DancingText dancingText = new DancingText();
        dancingText.thread.join();
    }

    private final Letters letters = new Letters("Dancing Text :-)");
    private final Thread thread;

    public DancingText() {
        super("Dancing Text");
        this.setSize(600, 300);

        this.thread = new Thread(this);
        this.thread.start();
    }


    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException ignored) {
            }
            this.letters.cycle();
            this.repaint();
        }
    }

    static public Color hslColor(float h, float s, float l) {
        float q, p, r, g, b;

        if (s == 0) {
            r = g = b = l; // achromatic
        } else {
            q = l < 0.5 ? (l * (1 + s)) : (l + s - l * s);
            p = 2 * l - q;
            r = hue2rgb(p, q, h + 1.0f / 3);
            g = hue2rgb(p, q, h);
            b = hue2rgb(p, q, h - 1.0f / 3);
        }
        return new Color(Math.round(r * 255), Math.round(g * 255), Math.round(b * 255));
    }
    private static float hue2rgb(float p, float q, float h) {
        if (h < 0) {
            h += 1;
        }

        if (h > 1) {
            h -= 1;
        }

        if (6 * h < 1) {
            return p + ((q - p) * 6 * h);
        }

        if (2 * h < 1) {
            return q;
        }

        if (3 * h < 2) {
            return p + ((q - p) * 6 * ((2.0f / 3.0f) - h));
        }

        return p;
    }

    float hue = 0;

    @Override
    public void paint(final Graphics g) {
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(0, 0, getWidth(), getHeight());

        g.setColor(Color.BLACK);
        g.setFont(new Font(
            "Comic Code Ligatures", Font.BOLD, 32
        ));

        if (this.letters == null) {
            return;
        }

        int x = 30;
        final int yStart = getHeight()/2+20;

        for (final Letter letter : this.letters.getLetters()) {
            g.setColor(hslColor(Math.min(1.0f, (hue += .004)*1.4f), 1f, .5f));
            g.drawString(
                String.valueOf(letter.getC()),
                x += 30,
                yStart + letter.getY()
            );
        }
        if (hue >= 1) {
            hue = 0;
        }
    }
}
