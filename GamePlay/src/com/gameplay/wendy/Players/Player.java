package com.gameplay.wendy.Players;

import java.io.IOException;

public abstract class Player {

    public abstract String chooseCombination() throws IOException;
    public abstract String proposeNewCombination(String answer) throws IOException;
    public abstract String answerToNewCombinationProposition(String chooseCombination, String proposition) throws IOException;
}
