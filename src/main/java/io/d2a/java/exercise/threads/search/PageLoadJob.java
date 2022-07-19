package io.d2a.java.exercise.threads.search;

public record PageLoadJob(PageLoader loader, Thread thread) {
}
