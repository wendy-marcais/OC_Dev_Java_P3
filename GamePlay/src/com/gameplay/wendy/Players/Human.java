package com.gameplay.wendy.Players;

import com.gameplay.wendy.GamePlayGetPropertyValues;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Human extends Player {

    // Class variables
    private Scanner scanner;
    private GamePlayGetPropertyValues property;

    // Constructor
    public Human() {
        scanner = new Scanner(System.in);
        property = new GamePlayGetPropertyValues();
    }

    @Override
    // For Defender and Duel modes -> save the human combination in the variable chooseCombination
    public String chooseCombination() throws IOException {
        String chooseCombination = null;

        boolean responseIsGood;
        do {
            try {
                System.out.print("\nMerci d'entrer une combinaison à " +  property.getPropValues("digits.in.combination") + " chiffres compris entre 0 et 9 : ");
                // Finds and returns the next complete token from this scanner
                chooseCombination = scanner.next();
                // Regex to verify the combination's content
                responseIsGood = chooseCombination.matches("[0-9]{4}");
            } catch (InputMismatchException e) {
                scanner.next();
                responseIsGood = false;
            }
            if (!responseIsGood) {
                System.out.print("\n-> Votre combinaison n'est pas valide !");
            }
        } while (!responseIsGood);

        return chooseCombination;
    }

    @Override
    // For Challenger and Duel modes -> save the human proposition in the variable chooseCombination
    public String proposeNewCombination(String answer) throws IOException {
        String chooseCombination = null;

        boolean responseIsGood;
        do {
            try {
                System.out.print("Votre proposition : ");
                // Finds and returns the next complete token from this scanner
                chooseCombination = scanner.next();
                // Regex to verify the combination's content
                responseIsGood = chooseCombination.matches("[0-9]{4}");
            } catch (InputMismatchException e) {
                scanner.next();
                responseIsGood = false;
            }
            if (!responseIsGood) {
                System.out.print("\n-> Votre proposition doit être composée de " + property.getPropValues("digits.in.combination") + " chiffres compris entre 0 et 9 : ");
            }
        } while (!responseIsGood);

        return chooseCombination;
    }

    @Override
    // For Defender and Duel modes -> answer given in a string
    public String answerToNewCombinationProposition(String chooseCombination, String proposition ) {
        System.out.println("Votre combinaison : " + chooseCombination);
        System.out.print("Votre réponse : ");

        return scanner.next();
    }
}
