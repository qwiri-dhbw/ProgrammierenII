package io.d2a.java.exercise.threads;

import java.util.ArrayList;
import java.util.List;

public class Letters {

    // different modes
    public static final int MODE_SIN = 0;
    public static final int MODE_TAN = 1;
    public static final int MODE_DIGITAL_SIN = 2;
    public static final int[] MODES = {MODE_SIN, MODE_TAN, MODE_DIGITAL_SIN};
    public final String text;
    private final List<Letter> letters = new ArrayList<>();
    // added to `cycle` every cycle
    public double multiplierFreq = .5;
    // x multiplied by `multiplierCp`
    public double multiplierCp = 1.0;
    // amplitude
    public double multiplierAmp = 1.0;

    public boolean sizer = false;
    private int modeIndex = MODE_SIN;
    // cycle is the x-axis for the formular
    private double cycle = 0;

    public Letters(final String text) {
        this.text = text;

        // add separate letters
        int i = 0;
        for (final char c : text.toCharArray()) {
            this.letters.add(new Letter(++i, c));
        }
    }

    public double f(final double charIndex) {
        return switch (MODES[this.modeIndex]) {
            case MODE_SIN -> Math.sin((charIndex + this.cycle) / 2.0);
            case MODE_TAN -> Math.tan((charIndex + this.cycle) / 2.0);
            case MODE_DIGITAL_SIN -> 1.5 * Math.sin(2.7 * Math.sin(2.4 * Math.sin((charIndex + this.cycle) / 2)));
            default -> 0;
        } * this.multiplierAmp;
    }

    public void setMode(final int mode) {
        for (int i = 0; i < MODES.length; i++) {
            if (MODES[i] == mode) {
                this.modeIndex = i;
                return;
            }
        }
        this.modeIndex = 0;
    }

    public void cycle() {
        this.cycle += multiplierFreq;
        this.letters.forEach(Letter::cycle);
    }

    public void toggle() {
        this.modeIndex = (this.modeIndex + 1) % MODES.length;
    }

    public int getModeIndex() {
        return modeIndex;
    }

    public List<Letter> getLetters() {
        return letters;
    }

    public class Letter {
        private final char c;
        private final int index;

        public int prevY;
        public int fontSize = 32;
        private int y = Integer.MIN_VALUE;

        public Letter(final int index, final char c) {
            this.index = index;
            this.c = c;
        }

        public void cycle() {
            this.prevY = this.y;
            this.y = (int) (f(this.index * multiplierCp) * 20);
            if (sizer) {
                this.fontSize = Math.abs(this.y);
            } else {
                this.fontSize = 32;
            }
        }

        public char getC() {
            return c;
        }

        public int getY() {
            return y;
        }
    }
}
