package io.d2a.java.exercise.threads;

import io.d2a.util.ColorRainbow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class LSD extends JFrame {

    public LSD() throws HeadlessException {
        this.setSize(720, 480);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.add(new Pane());
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new LSD();
    }

    class Pane extends JPanel implements MouseMotionListener {

        private float hueOff = 0;
        private int size = 20;
        public Pane() {
            this.addMouseMotionListener(this);
        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);

            float sat = 0;
            float hue = 0;

            float satM = (float) this.size / (float) getHeight();
            float hueM = (float) this.size / (float) getWidth();

            for (int j = 0; j < getHeight(); j += this.size) {
                hue = 0;
                for (int i = 0; i < getWidth(); i += this.size) {
                    g.setColor(ColorRainbow.hslColor(hue + hueOff, sat, .5f));
                    g.fillRect(i, j, this.size, this.size);
                    hue += hueM;
                }
                sat += satM;
            }
        }

        @Override
        public void mouseMoved(MouseEvent e) {
            this.hueOff = (float) e.getX() / (float) this.getWidth();
            this.size = (int) Math.max(5, (((double)e.getY() / (double) this.getHeight()) *
                    Math.min(this.getHeight(), this.getWidth())/8));
            this.repaint();
        }

        @Override
        public void mouseDragged(MouseEvent e) {

        }
    }

}
