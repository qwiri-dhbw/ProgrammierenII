package io.d2a.java.exercise.threads;

import io.d2a.java.exercise.ui.data.util.presets.PaddedFrame;
import java.awt.Color;
import java.awt.Graphics;

public class TrafficLight extends PaddedFrame implements Runnable {

    private static final LightPhase[] phases = {
        new LightPhase("Rot", LightPhase.RED, 4000),
        new LightPhase("Rot-Gelb", LightPhase.RED | LightPhase.YELLOW, 1000),
        new LightPhase("Grün", LightPhase.GREEN, 2000),
        new LightPhase("Gelb", LightPhase.YELLOW, 1500)
    };
    private LightPhase phase = phases[1];

    private int index = 0;

    public TrafficLight() {
        super("Treffik Leit");

        this.setSize(190, 380);
        this.repaint();

        new Thread(this).start();
    }

    @Override
    public void run() {
        // noinspection InfiniteLoopStatement
        while (true) {
            // get current light phase
            this.phase = phases[this.index++ % phases.length];
            this.repaint();
            try {
                // noinspection BusyWait
                Thread.sleep(this.phase.duration());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void paint(final Graphics g) {
        if (this.phase == null) {
            return;
        }

        g.setColor(Color.WHITE);
        g.fillRect(0, 0, getWidth(), getHeight());
        g.setColor(Color.BLACK);
        g.fillRect(20, 45, 150, 310);

        g.setColor(Color.WHITE);
        g.fillRect(30, 55, 130, 90);
        g.fillRect(30, 155, 130, 90);
        g.fillRect(30, 255, 130, 90);

        if ((this.phase.color() & LightPhase.RED) == LightPhase.RED) {
            g.setColor(Color.RED);
            g.fillRect(35, 60, 120, 80);
        }
        if ((this.phase.color() & LightPhase.YELLOW) == LightPhase.YELLOW) {
            g.setColor(Color.YELLOW);
            g.fillRect(35, 60 + 90 + 10, 120, 80);
        }
        if ((this.phase.color() & LightPhase.GREEN) == LightPhase.GREEN) {
            g.setColor(Color.GREEN);
            g.fillRect(35, 60 + 90 + 10 + 90 + 10, 120, 80);
        }
    }

    public static void main(String[] args) {
        new TrafficLight();
    }

}
