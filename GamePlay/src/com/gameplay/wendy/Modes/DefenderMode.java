package com.gameplay.wendy.Modes;

import com.gameplay.wendy.GamePlayGetPropertyValues;
import com.gameplay.wendy.Players.Computer;
import com.gameplay.wendy.Players.Human;
import com.gameplay.wendy.Players.Player;

import java.io.IOException;

public class DefenderMode implements PlayMode {

    // Class variables
    private Player human;
    private Player computer;
    private String proposition = "";
    private GamePlayGetPropertyValues property;

    // Constructor
    public DefenderMode() {
        human = new Human();
        computer = new Computer();
        property = new GamePlayGetPropertyValues();

    }

    @Override
    public void play() throws IOException {
        // Register human combination
        String chooseCombination = human.chooseCombination();
        String answer = null;

        for (int t = 1; t <= Integer.parseInt(property.getPropValues("trials")); t++) {
            System.out.print("\n-> essai n° " + t + "\n");
            proposition = computer.proposeNewCombination(answer);

            answer = human.answerToNewCombinationProposition(chooseCombination, proposition);

            // if iaProposition = registeredCombination -> end of the game
            if (proposition.equals(chooseCombination)) {
                System.out.println("\nDommage, l'IA a trouvé la bonne combinaison en " + t + " essais !");
                break;
            }
        }
        if (!proposition.equals(chooseCombination)) {
            System.out.println("\nBravo, l'IA n'a pas trouvé la bonne réponse... \nLa bonne combinaison était : " +
                    chooseCombination + " !\n");
        }
    }
}
