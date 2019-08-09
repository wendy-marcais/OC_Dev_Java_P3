package com.gameplay.wendy.Modes;

import com.gameplay.wendy.Players.Computer;
import com.gameplay.wendy.Players.Human;
import com.gameplay.wendy.Players.Player;

import com.gameplay.wendy.GamePlayGetPropertyValues;
import org.apache.log4j.Logger;

import java.io.IOException;

/**
 * Challenger Mode
 */
public class ChallengerMode implements PlayMode {

    private static Logger logger = Logger.getLogger(ChallengerMode.class);
    private GamePlayGetPropertyValues property;

    private String randCombination;
    private Player human;
    private Player computer;

    /**
     * @throws IOException if a problem is found in property
     */
    // Constructor
    public ChallengerMode() throws IOException {
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
        // if dev mode is active
        if (property.getPropValues("dev.mode").equals("true")) {
            logger.info("\n(Mode développeur activé -> la combinaison de l'IA est : " + randCombination + ")");
        }

        // 5 chances for the human to find the good combination
        String newProposal = null;

        for (int t = 1; t <= Integer.parseInt(property.getPropValues("trials")); t++) {
            logger.info("\n-> essai n° " + t + " : ");
            newProposal = human.proposeNewCombination("");

            // if chooseCombination = randCombination -> end of the game
            if (randCombination.equals(newProposal)) {
                logger.info("\nBravo, la bonne combinaison était : " + randCombination + " !");
                logger.info("Vous avez trouvé la bonne réponse en " + t + " essais !");
                break;
            }
            computer.answerToNewCombinationProposition(newProposal, randCombination);
        }
        if (!randCombination.equals(newProposal)) {
            logger.info("\nDésolé, vous n'avez pas trouvé la bonne réponse... \nLa bonne combinaison était : " +
                 randCombination + " !\n");
        }
    }
}
