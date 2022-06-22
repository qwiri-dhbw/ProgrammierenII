package io.d2a.java.exercise.threads;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Letters {

    public static final int MIN_Y = 50;
    public static final int MAX_Y = 200;
    public static final Random random = new Random();

    public class Letter {
        private final char c;
        private int y;
        private boolean direction;

        public Letter(final char c) {
            this.c = c;
        }

        public void cycle() {
            if (random.nextBoolean()) {
                this.y += random.nextInt(4);
            } else {
                this.y -= random.nextInt(4);
            }
        }

        public char getC() {
            return c;
        }

        public int getY() {
            return y;
        }
    }

    private final List<Letter> letters = new ArrayList<>();

    public Letters(final String text) {
        for (final char c : text.toCharArray()) {
            this.letters.add(new Letter(c));
        }
    }

    public void cycle() {
        this.letters.forEach(Letter::cycle);
    }

    public List<Letter> getLetters() {
        return letters;
    }
}
