package com.gameplay.wendy.Modes;

import java.util.Random;

public final class RandomCombination {

    public static String generate() {
        // Load a combination with java.util.Random class
        Random rand = new Random();
        // Created var random for the random combination between 0000 and 9999
        int random = rand.nextInt(9999);
        // String format for comparison (commas and square brackets deleted)
        return String.format("%04d", random);
    }
}