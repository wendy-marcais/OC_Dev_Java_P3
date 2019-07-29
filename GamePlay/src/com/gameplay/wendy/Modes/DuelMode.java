package com.gameplay.wendy.Modes;

import com.gameplay.wendy.GamePlayGetPropertyValues;
import com.gameplay.wendy.Players.Computer;
import com.gameplay.wendy.Players.Human;
import com.gameplay.wendy.Players.Player;

import java.io.IOException;

/**
 * Duel Mode
 */
public class DuelMode implements PlayMode {

    // Class variables
    private String randCombination;
    private Player human;
    private Player computer;
    private GamePlayGetPropertyValues property;

    /**
     * @throws IOException if a problem is found in property
     */
    // Constructor
    public DuelMode() throws IOException {
        human = new Human();
        computer = new Computer();
        randCombination = computer.chooseCombination();
        property = new GamePlayGetPropertyValues();
    }

    /**
     * @throws IOException if a problem is found in property
     */
    @Override
    public void play() throws IOException {
        // Register human combination
        String chooseCombination = human.chooseCombination();
        String answer = null;
        String answer2 = "";

        // if dev mode is active
        GamePlayGetPropertyValues gamePlayGetPropertyValues = new GamePlayGetPropertyValues();
        if (gamePlayGetPropertyValues.getPropValues("dev.mode").equals("true")) {
            System.out.println("\n(Mode développeur activé -> la combinaison de l'IA est : " + randCombination + ")");
        }

        for (int t = 1; t <= Integer.parseInt(property.getPropValues("trials")); t++) {
            // When Human play
            System.out.print("\n-> Votre tour, essai n° " + t + " : \n");
            String humanProposal = human.proposeNewCombination(answer2);
            answer2 = computer.answerToNewCombinationProposition(humanProposal, randCombination);

            // When Computer play
            String computerProposal;
            System.out.print("\n-> Tour de l'IA, essai n° " + t + " : \n");
            computerProposal = computer.proposeNewCombination(answer);

            answer = human.answerToNewCombinationProposition(chooseCombination, computerProposal);

            if (humanProposal.equals(randCombination)) {
                System.out.println("\nBravo, la bonne combinaison était : " + randCombination + ".");
                System.out.println("Vous avez trouvé la bonne réponse avant l'IA, en " + t + " essais !");
                break;
            } else if (computerProposal.equals(chooseCombination)) {
                System.out.println("\nDommage, l'IA a trouvé votre combinaison (" + chooseCombination + ") avant vous, en " + t + " essais !");
                break;
            }
        }
    }
}
