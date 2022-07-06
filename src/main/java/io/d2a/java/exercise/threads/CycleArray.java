package io.d2a.java.exercise.threads;

public class CycleArray<T> {

    private final T[] array;
    private int index;

    public CycleArray(final T[] array) {
        if (array.length == 0) {
            throw new IllegalArgumentException("array cannot be empty");
        }
        this.array = array;
    }

    public T cycle() {
        return array[this.index = (this.index + 1) % this.array.length];
    }

    public T get() {
        return this.array[index];
    }

}
