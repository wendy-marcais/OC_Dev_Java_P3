package com.gameplay.wendy.Modes;

import com.gameplay.wendy.GamePlayGetPropertyValues;

import java.io.IOException;
import java.util.Arrays;


/**
 * Random combination
 */
public final class RandomCombination {

    /**
     * @return randomCombination in string format
     */
    public static String generate() throws IOException {
        GamePlayGetPropertyValues property;
        property = new GamePlayGetPropertyValues();

        int[] randomCombiCreation = new int [Integer.parseInt((property.getPropValues("digits.in.combination")))];

        // define the range
        int max = 9;
        int min = 0;
        int range = max - min;

        String[] randomCombination = new String[Integer.parseInt(property.getPropValues("digits.in.combination"))];

        for (int i = 0; i < randomCombiCreation.length; i++) {
            randomCombination[i] = String.valueOf((int)(Math.random() * range) + min);
            // put in randomCombination each number created from loop
            randomCombiCreation[i] = Integer.parseInt(randomCombination[i]);
        }
        return Arrays.toString(randomCombination).replace("[", "").replace("]", "").replace(",", "").replaceAll("\\s", "");
    }
}