package com.gameplay.wendy.Players;

import com.gameplay.wendy.Modes.RandomCombination;

import com.gameplay.wendy.GamePlayGetPropertyValues;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.Arrays;


/**
 * Computer player
 */
public class Computer extends Player {

    private static Logger logger = Logger.getLogger(Computer.class);
    private GamePlayGetPropertyValues property;

    private int[] lastProposition;
    private int[] firstCombination;
    private int[] lastProposition2;
    private int[] lower_bound;
    private int[] upper_bound;

    // Constructor
    public Computer() throws IOException {
        property = new GamePlayGetPropertyValues();
        firstCombination = new int[Integer.parseInt(property.getPropValues("digits.in.combination"))];
        lastProposition2 = new int[Integer.parseInt(property.getPropValues("digits.in.combination"))];
        lower_bound = new int[Integer.parseInt(property.getPropValues("digits.in.combination"))];
        upper_bound = new int[Integer.parseInt(property.getPropValues("digits.in.combination"))];
        Arrays.fill(upper_bound,10);
    }

    /**
     * @return random combination
     */
    @Override
    // For all modes -> create te AI random combination
    public String chosenCombination() throws IOException {
        return RandomCombination.generate();
    }

    /**
     * @param answer equal the human answer
     * @return lastProposition
     */
    @Override
    // For Defender and Duel modes -> first round AI proposition = 5*propValue, other rounds evolution of bounds
    public String proposeNewCombination (String answer) {
        if (answer == null) {
            // Give value 5 for each character in the first proposition
            Arrays.fill(firstCombination,5);
            logger.info("Proposition de l'IA : " + Arrays.toString(firstCombination).replace("[", "").replace("]", "")
                    .replace(",", "").replaceAll("\\s", ""));
            lastProposition = firstCombination;
        } else {
            for (int i = 0; i < lastProposition.length; i++) {
                if (answer.charAt(i) == '+') {
                    this.lower_bound[i] = lastProposition[i];
                } else if (answer.charAt(i) == '-') {
                    this.upper_bound[i] = lastProposition[i];
                } else {
                    this.lower_bound[i] = lastProposition[i];
                    this.upper_bound[i] = lastProposition[i];
                }
                lastProposition2[i] = this.lower_bound[i] + ((this.upper_bound[i] - this.lower_bound[i]) / 2);
            }
            lastProposition = lastProposition2;

            logger.info("Proposition de l'IA : " + Arrays.toString(lastProposition).replace("[", "").replace(
                    "]", "").replace(",", "").replaceAll("\\s", ""));
        }
        return Arrays.toString(lastProposition).replace("[", "").replace("]", "").replace(",", "").replaceAll("\\s", "");
    }

    /**
     * @param chosenCombination equal the computer random combination
     * @param proposition equal the human proposition
     * @return null
     * @throws IOException if a problem is found in property
     */
    @Override
    // For Challenger and Duel modes -> answer given character by character
    public String answerToNewCombinationProposition(String chosenCombination, String proposition) throws IOException {
        logger.info("Réponse de l'IA : ");
        for (int i = 0; i < Integer.parseInt(property.getPropValues("digits.in.combination")); i++) {
            if (proposition.charAt(i) > chosenCombination.charAt(i)) {
                System.out.print("+");
            } else if (proposition.charAt(i) < chosenCombination.charAt(i)) {
                System.out.print("-");
            } else {
                System.out.print("=");
            }
        }
        System.out.print("\n");
        return null;
    }
}
