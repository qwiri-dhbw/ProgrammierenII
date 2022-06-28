package io.d2a.java.exercise.threads;

public record LightPhase(String name, int color, int duration) {

    public static final short GREEN = 0x1; // 1 << 0
    public static final short YELLOW = 0x2; // 1 << 1
    public static final short RED = 0x4; // 1 << 2

}
