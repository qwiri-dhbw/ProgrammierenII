package io.d2a.java.exercise.threads;

import java.util.ArrayList;
import java.util.List;

public class Letters {

    private double cycle = 0;
    private double multiplierFreq = .5;
    private double multiplierClench = 1.0;

    public Letters(final String text) {
        int i = 1;
        for (final char c : text.toCharArray()) {
            this.letters.add(new Letter(i++,c));
        }
    }

    private static double f(final double charIndex, final double cycle) {
        return Math.sin((charIndex + cycle) / 2.0);
    }

    private final List<Letter> letters = new ArrayList<>();

    public void setMultiplierFreq(double multiplier) {
        this.multiplierFreq = multiplier;
    }

    public void setMultiplierClench(double multiplierClench) {
        this.multiplierClench = multiplierClench;
    }

    public void cycle() {
        this.cycle += multiplierFreq;
        this.letters.forEach(Letter::cycle);
    }

    public class Letter {
        private final char c;
        private final int index;
        private int y;

        public Letter(final int index, final char c) {
            this.index = index;
            this.c = c;
        }

        public void cycle() {
            this.y = (int) (f(this.index * multiplierClench, cycle) * 20);
        }

        public char getC() {
            return c;
        }

        public int getY() {
            return y;
        }
    }

    public List<Letter> getLetters() {
        return letters;
    }
}
