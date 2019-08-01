package com.gameplay.wendy;

import com.gameplay.wendy.Modes.ChallengerMode;
import com.gameplay.wendy.Modes.DefenderMode;
import com.gameplay.wendy.Modes.DuelMode;
import com.gameplay.wendy.Modes.PlayMode;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * RunGame that defines menus related to human choice
 */
class RunGame {
    private static Logger logger = Logger.getLogger(RunGame.class);
    private GamePlayGetPropertyValues property;

    private Scanner scanner;
    private String ruleGo;
    private String ruleHumanAnswer;
    private String ruleComputerAnswer;
    private String ruleDigits;

    /**
     * @throws IOException if a problem is found in property
     */
    // Constructor
    RunGame() throws IOException {
        property = new GamePlayGetPropertyValues();
        scanner = new Scanner(System.in);

        ruleGo = "\n-> Les règles sont simples :\n";
        ruleHumanAnswer = "- À chacune des propositions de l'IA vous indiquerez pour chaque chiffre si le chiffre de sa combinaison est plus grand (+), plus petit (-) ou si c’est le bon (=)";
        ruleComputerAnswer = "- À chacune de vos propositions l'IA vous indiquera pour chaque chiffre si le chiffre de sa combinaison est plus grand (+), plus petit (-) ou si c’est le bon (=)";
        ruleDigits = "- Vous avez " + property.getPropValues("trials") + " essais pour trouver la combinaison de l'IA à " + property.getPropValues("digits.in.combination") + " chiffres !";
    }

    /**
     * Display a selected mode.
     * @param userChoice The selected mode.
     */
    private PlayMode startGameAccordingUserChoice(int userChoice) throws IOException {
        PlayMode game;
        switch (userChoice) {
            case 2:
                game = new DefenderMode();
                System.out.println(ruleGo + ruleHumanAnswer);
                //logger.info(ruleGo + ruleHumanAnswer);
                break;
            case 3:
                game = new DuelMode();
                System.out.println(ruleGo + ruleComputerAnswer + "\n" + ruleHumanAnswer);
                //logger.info(ruleGo + ruleComputerAnswer + "\n" + ruleHumanAnswer);
                break;
            default:
                game = new ChallengerMode();
                System.out.println(ruleGo + ruleDigits + "\n" + ruleComputerAnswer);
                //logger.info(ruleGo + ruleDigits + "\n" + ruleComputerAnswer);
                break;
        }
        return game;
    }

    /**
     * Run modes
     */
    void runModes() throws IOException {
        int userChoice = offerFirstMenuChoiceToUser();
        PlayMode game = startGameAccordingUserChoice(userChoice);
        game.play();

        int nextChosenMode = offerSecondMenuChoiceToUser();
        while (nextChosenMode <= 3) {
            switch (nextChosenMode) {
                case 1:
                    game = startGameAccordingUserChoice(userChoice);
                    game.play();
                    nextChosenMode = offerSecondMenuChoiceToUser();
                    break;
                case 2:
                    runModes();
                    break;
                case 3:
                    System.exit(0);
            }
        }
    }

    /**
     * Display the modes in the method askChosenModes, first menu with modes choice
     * @return the different modes
     */
    private int offerFirstMenuChoiceToUser() {
        String[] modes = {"Challenger : Trouvez la bonne combinaison !", "Défenseur : Définissez la combinaison et " +
                "tentez de piéger l'IA !", "Duel : Trouvez la bonne combinaison avant l'IA !\n"};
        return askChosenMode(modes);
    }

    /**
     * Display the modes in the method askChosenMode, second menu : play again, play same mode, quit
     * @return the different modes
     */
    private int offerSecondMenuChoiceToUser() {
        String[] modes = {"Rejouer le même mode", "Choisir un autre mode", "Quitter le jeu"};
        return askChosenMode(modes);
    }

    /**
     * Display a question about the modes in the standard input, get response and display it
     * @param responses available responses
     * @return the number of the selected choice
     */
    private int askChosenMode(String[] responses) {
        System.out.println("\n\nMerci de choisir un mode de jeu parmis les choix suivants :\n");
        //logger.info("Merci de choisir un mode de jeu parmis les choix suivants :\n");
        for (int i = 1; i <= responses.length; i++) {
            System.out.println(i + " - " + responses[i - 1]);
            //logger.info(i + " - " + responses[i - 1]);
        }
        int nbResponse = 0;
        boolean responseIsGood;
        do {
            try {
                System.out.print("Quel mode avez-vous choisi ? ");
                //logger.info("Quel mode avez-vous choisi ? ");
                nbResponse = scanner.nextInt();
                responseIsGood = (nbResponse >= 1 && nbResponse <= responses.length);
            } catch (InputMismatchException e) {
                scanner.next();
                responseIsGood = false;
            }
            if (responseIsGood) {
                String choice = "Vous avez choisi le mode -> " + responses[nbResponse - 1] + "\n";
                System.out.println(choice);
                //logger.info(choice);
            } else {
                System.out.println("\n-> Vous n'avez pas choisi de mode parmi les choix proposés");
                //logger.error("\n-> Vous n'avez pas choisi de mode parmi les choix proposés");
            }
        } while (!responseIsGood);
        return nbResponse;
    }
}
