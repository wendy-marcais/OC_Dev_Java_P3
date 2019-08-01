package com.gameplay.wendy.Players;

import java.io.IOException;

/**
 * abstract class which define players methods
 */
public abstract class Player {

    public abstract String chosenCombination() throws IOException;
    public abstract String proposeNewCombination(String answer) throws IOException;
    public abstract String answerToNewCombinationProposition(String chooseCombination, String proposition) throws IOException;
}
