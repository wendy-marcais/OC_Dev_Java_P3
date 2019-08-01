package com.gameplay.wendy.Players;

import com.gameplay.wendy.GamePlayGetPropertyValues;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;


/**
 * Human player
 */
public class Human extends Player {

    private static Logger logger = Logger.getLogger(Human.class);
    private GamePlayGetPropertyValues property;
    private int digits;


    private Scanner scanner;

    // Constructor
    public Human() throws IOException {
        property = new GamePlayGetPropertyValues();
        digits = Integer.parseInt(property.getPropValues("digits.in.combination"));
        scanner = new Scanner(System.in);
    }

    /**
     * @return chosenCombination
     * @throws IOException if the combination format written is not correct
     */
    @Override
    // For Defender and Duel modes -> save the human combination in the variable chooseCombination
    public String chosenCombination() throws IOException {
        String chosenCombination = null;

        boolean responseIsGood;
        do {
            try {
                System.out.print("\nMerci d'entrer une combinaison à " +  property.getPropValues("digits.in.combination") + " chiffres compris entre 0 et 9 : ");
                //logger.info("\nMerci d'entrer une combinaison à " +  property.getPropValues("digits.in.combination") + " chiffres compris entre 0 et 9 : ");
                // Finds and returns the next complete token from this scanner
                chosenCombination = scanner.next();
                // Regex to verify the combination's content
                responseIsGood = chosenCombination.matches("[0-9]{" + digits + "}");
            } catch (InputMismatchException e) {
                scanner.next();
                responseIsGood = false;
            }
            if (!responseIsGood) {
                System.out.print("\n-> Votre combinaison n'est pas valide !");
                //logger.error("\n-> Votre combinaison n'est pas valide !");
            }
        } while (!responseIsGood);
        return chosenCombination;
    }

    /**
     * @param answer equal what the human has written
     * @return chosenCombination
     * @throws IOException if the combination format written is not correct
     */
    @Override
    // For Challenger and Duel modes -> save the human proposition in the variable chooseCombination
    public String proposeNewCombination(String answer) throws IOException {
        String chosenCombination = null;

        boolean responseIsGood;
        do {
            try {
                System.out.print("Votre proposition : ");
                //logger.info("Votre proposition : ");
                // Finds and returns the next complete token from this scanner
                chosenCombination = scanner.next();
                // Regex to verify the combination's content
                responseIsGood = chosenCombination.matches("[0-9]{" + digits + "}");
            } catch (InputMismatchException e) {
                scanner.next();
                responseIsGood = false;
            }
            if (!responseIsGood) {
                System.out.print("\n-> Votre proposition doit être composée de " + property.getPropValues("digits.in.combination") + " chiffres compris entre 0 et 9 : ");
                //logger.error("\n-> Votre proposition doit être composée de " + property.getPropValues("digits.in.combination") + " chiffres compris entre 0 et 9 : ");
            }
        } while (!responseIsGood);
        return chosenCombination;
    }

    /**
     * @param chosenCombination equal the human chosen combination
     * @param proposition equal to the computer proposal
     * @return human answer
     */
    @Override
    // For Defender and Duel modes -> answer given in a string
    public String answerToNewCombinationProposition(String chosenCombination, String proposition ) {
        System.out.println("Votre combinaison : " + chosenCombination);
        //logger.info("Votre combinaison : " + chosenCombination);
        System.out.print("Votre réponse : ");
        //logger.info("Votre réponse : ");
        return scanner.next();
    }
}
