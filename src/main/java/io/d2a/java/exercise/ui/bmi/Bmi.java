package io.d2a.java.exercise.ui.bmi;

public class Bmi {

    private static final int[] MALE = {0, 20, 25, 30, 40};

    private static final int[] FEMALE = {0, 19, 24, 30, 40};

    public static final String[] STATES = {
        "Untergewicht",
        "Normalgewicht",
        "Übergewicht",
        "Adipositas",
        "Massive Adipositas (Luca)"
    };

    private static int getIndex(final int[] ex, final double num) {
        for (int i = ex.length - 1; i >= 0; i--) {
            if (num >= ex[i]) {
                return i;
            }
        }
        return 0;
    }

    public static int getIndex(final double weight, final boolean male) {
        final int[] ex = male ? MALE : FEMALE;
        return Bmi.getIndex(ex, weight);
    }

    public static String getState(final double weight, final boolean male) {
        return STATES[Bmi.getIndex(weight, male)];
    }

}
