package io.d2a.java.exercise.threads;

public record LightPhase(String name, int color, int duration) {

    public static final short RED = 1 << 2;
    public static final short YELLOW = 1 << 1;
    public static final short GREEN = 1;

}
