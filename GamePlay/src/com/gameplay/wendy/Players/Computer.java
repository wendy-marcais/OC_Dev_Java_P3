package com.gameplay.wendy.Players;

import com.gameplay.wendy.GamePlayGetPropertyValues;
import com.gameplay.wendy.Modes.RandomCombination;

import java.io.IOException;
import java.util.Arrays;

public class Computer extends Player {

    // Class variables
    private GamePlayGetPropertyValues property;
    private String lastProposition;
    private int[] lastProposition2 = new int[4];
    private int[] lower_bound = {0, 0, 0, 0};
    private int[] upper_bound = {9, 9, 9, 9};

    // Constructor
    public Computer() {
        property = new GamePlayGetPropertyValues();
    }


    @Override
    // For all modes -> create te AI random combination
    public String chooseCombination() {
        return RandomCombination.generate();
    }

    @Override
    // For Defender and Duel modes -> first round AI proposition = 5555, other rounds evolution of bounds
    public String proposeNewCombination (String answer) {
        if (answer == null) {
            String firstCombination = "5555";
            System.out.print("Proposition de l'IA : " + firstCombination + "\n");
            lastProposition = firstCombination;
        } else {
            for (int i = 0; i < lastProposition.length(); i++) {
                if (answer.charAt(i) == '+') {
                    this.lower_bound[i] = Character.getNumericValue(lastProposition.charAt(i));
                } else if (answer.charAt(i) == '-') {
                    this.upper_bound[i] = Character.getNumericValue(lastProposition.charAt(i));
                } else {
                    this.lower_bound[i] = Character.getNumericValue(lastProposition.charAt(i));
                    this.upper_bound[i] = Character.getNumericValue(lastProposition.charAt(i));
                }

                lastProposition2[i] = this.lower_bound[i] + ((this.upper_bound[i] - this.lower_bound[i]) / 2);
            }
            lastProposition = Arrays.toString(lastProposition2).replace("[", "").replace("]", "").replace(",", "").replaceAll(
                    "\\s", "");
            System.out.print("Proposition de l'IA : " + lastProposition + "\n");
        }
        return lastProposition;
    }

    @Override
    // For Challenger and Duel modes -> answer given character by character
    public String answerToNewCombinationProposition(String chooseCombination, String proposition) throws IOException {
        System.out.print("Réponse de l'IA : ");
        for (int i = 0; i < Integer.parseInt(property.getPropValues("digits.in.combination")); i++) {
            if (proposition.charAt(i) > chooseCombination.charAt(i)) {
                System.out.print("+");
            } else if (proposition.charAt(i) < chooseCombination.charAt(i)) {
                System.out.print("-");
            } else {
                System.out.print("=");
            }
        }
        System.out.print("\n");

        return null;
    }
}
