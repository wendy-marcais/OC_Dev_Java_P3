package com.gameplay.wendy.Modes;

import com.gameplay.wendy.Players.Computer;
import com.gameplay.wendy.Players.Human;
import com.gameplay.wendy.Players.Player;

import com.gameplay.wendy.GamePlayGetPropertyValues;
import org.apache.log4j.Logger;

import java.io.IOException;

/**
 * Defender Mode
 */
public class DefenderMode implements PlayMode {

    private static Logger logger = Logger.getLogger(DefenderMode.class);
    private GamePlayGetPropertyValues property;

    private Player human;
    private Player computer;
    private String proposition;

    // Constructor
    public DefenderMode() throws IOException {
        property = new GamePlayGetPropertyValues();
        human = new Human();
        computer = new Computer();
    }

    /**
     * @throws IOException if a problem is found in property
     */
    @Override
    public void play() throws IOException {
        // Register human combination
        String chosenCombination = human.chosenCombination();
        String answer = null;

        for (int t = 1; t <= Integer.parseInt(property.getPropValues("trials")); t++) {
            System.out.print("\n-> essai n° " + t + "\n");
            //logger.info("\n-> essai n° " + t + "\n");
            proposition = computer.proposeNewCombination(answer);

            answer = human.answerToNewCombinationProposition(chosenCombination, proposition);

            // if iaProposition = chosenCombination -> end of the game
            if (proposition.equals(chosenCombination)) {
                System.out.println("\nDommage, l'IA a trouvé la bonne combinaison en " + t + " essais !");
                //logger.info("\nDommage, l'IA a trouvé la bonne combinaison en " + t + " essais !");
                break;
            }
        }
        if (!proposition.equals(chosenCombination)) {
            System.out.println("\nBravo, l'IA n'a pas trouvé la bonne réponse... \nLa bonne combinaison était : " +
                    chosenCombination + " !\n");
            //logger.info("\nBravo, l'IA n'a pas trouvé la bonne réponse... \nLa bonne combinaison était : " +
            //      chooseCombination + " !\n");

        }
    }
}
