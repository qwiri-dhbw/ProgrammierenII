package io.d2a.java.exercise.ui.data;

import java.security.SecureRandom;
import java.util.Random;
import java.util.Set;
import java.util.Stack;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Lottery {

    public static void main(String[] args) {
        final Random random = new SecureRandom();
        final Set<Integer> result = new TreeSet<>();

        while (result.size() != 6) {
            result.add(random.nextInt(49) + 1);
        }

        int extra;
        do {
            extra = random.nextInt(49) + 1;
        } while (result.contains(extra));

        System.out.println(result + " Zusartz: " + extra);
    }

    // golf version
    private void a() {
        final Random random = new SecureRandom();
        final Stack<Integer> result = new Stack<>();
        for (int rnd = 0; result.size() != 7; result.add(random.nextInt(49) + 1));
        System.out.println(result.stream().filter(i -> i < 6).map(String::valueOf).collect( Collectors.joining()));
    }

}
