package com.gameplay.wendy.Modes;

import com.gameplay.wendy.GamePlayGetPropertyValues;
import com.gameplay.wendy.Players.Computer;
import com.gameplay.wendy.Players.Human;
import com.gameplay.wendy.Players.Player;

import java.io.IOException;

/**
 * Challenger Mode
 */
public class ChallengerMode implements PlayMode {

    // Class variables
    private String randCombination;
    private Player human;
    private Player computer;
    private GamePlayGetPropertyValues property;

    /**
     * @throws IOException
     */
    // Constructor
    public ChallengerMode() throws IOException {
        human = new Human();
        computer = new Computer();
        randCombination = computer.chooseCombination();
        property = new GamePlayGetPropertyValues();
    }

    /**
     * @throws IOException
     */
    @Override
    public void play() throws IOException {
        // if dev mode is active
        if (property.getPropValues("dev.mode").equals("true")) {
            System.out.println("\n(Mode développeur activé -> la combinaison de l'IA est : " + randCombination + ")");
        }

        // 5 chances for the human to find the good combination
        String newProposal = null;

        for (int t = 1; t <= Integer.parseInt(property.getPropValues("trials")); t++) {
            System.out.println("\n-> essai n° " + t + " : ");
            newProposal = human.proposeNewCombination("");

            // if chooseCombination = randCombination -> end of the game
            if (randCombination.equals(newProposal)) {
                System.out.println("\nBravo, la bonne combinaison était : " + randCombination);
                System.out.println("Vous avez trouvé la bonne réponse en " + t + " essais !");
                break;
            }
            computer.answerToNewCombinationProposition(newProposal, randCombination);
        }
        if (!randCombination.equals(newProposal)) {
            System.out.println("\nDésolé, vous n'avez pas trouvé la bonne réponse... \nLa bonne combinaison était : " +
                    randCombination + " !\n");
        }
    }
}
