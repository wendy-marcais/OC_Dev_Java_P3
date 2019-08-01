package com.gameplay.wendy.Modes;

import com.gameplay.wendy.Players.Computer;
import com.gameplay.wendy.Players.Human;
import com.gameplay.wendy.Players.Player;

import com.gameplay.wendy.GamePlayGetPropertyValues;
import org.apache.log4j.Logger;

import java.io.IOException;

/**
 * Duel Mode
 */
public class DuelMode implements PlayMode {

    private static Logger logger = Logger.getLogger(DuelMode.class);
    private GamePlayGetPropertyValues property;

    private String randCombination;
    private Player human;
    private Player computer;

    /**
     * @throws IOException if a problem is found in property
     */
    // Constructor
    public DuelMode() throws IOException {
        property = new GamePlayGetPropertyValues();
        human = new Human();
        computer = new Computer();
        randCombination = computer.chosenCombination();
    }

    /**
     * @throws IOException if a problem is found in property
     */
    @Override
    public void play() throws IOException {
        // Register human combination
        String chosenCombination = human.chosenCombination();
        String answer = null;
        String answer2 = "";

        // if dev mode is active
        GamePlayGetPropertyValues gamePlayGetPropertyValues = new GamePlayGetPropertyValues();
        if (gamePlayGetPropertyValues.getPropValues("dev.mode").equals("true")) {
            System.out.println("\n(Mode développeur activé -> la combinaison de l'IA est : " + randCombination + ")");
            //logger.info("\n(Mode développeur activé -> la combinaison de l'IA est : " + randCombination + ")");
        }

        for (int t = 1; t <= Integer.parseInt(property.getPropValues("trials")); t++) {
            // When Human play
            System.out.print("\n-> Votre tour, essai n° " + t + " : \n");
            //logger.info("\n-> Votre tour, essai n° " + t + " : \n");
            String humanProposal = human.proposeNewCombination(answer2);
            answer2 = computer.answerToNewCombinationProposition(humanProposal, randCombination);

            // When Computer play
            String computerProposal;
            System.out.print("\n-> Tour de l'IA, essai n° " + t + " : \n");
            //logger.info("\n-> Tour de l'IA, essai n° " + t + " : \n");
            computerProposal = computer.proposeNewCombination(answer);

            answer = human.answerToNewCombinationProposition(chosenCombination, computerProposal);

            if (humanProposal.equals(randCombination)) {
                System.out.println("\nBravo, la bonne combinaison était : " + randCombination + ".");
                //logger.info("\nBravo, la bonne combinaison était : " + randCombination + ".");
                System.out.println("Vous avez trouvé la bonne réponse avant l'IA, en " + t + " essais !");
                //logger.info("Vous avez trouvé la bonne réponse avant l'IA, en " + t + " essais !");
                break;
            } else if (computerProposal.equals(chosenCombination)) {
                System.out.println("\nDommage, l'IA a trouvé votre combinaison (" + chosenCombination + ") avant vous, en " + t + " essais !");
                //logger.info("\nDommage, l'IA a trouvé votre combinaison (" + chooseCombination + ") avant vous, en " + t + " essais !");
                break;
            }
        }
    }
}
