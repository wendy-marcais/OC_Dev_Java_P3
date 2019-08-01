package com.gameplay.wendy;

import java.io.IOException;

public class Main {
    /**
     * @param args arguments
     * @throws IOException if a problem is found in property
     */
    public static void main(String[] args) throws IOException {
        // Run the game
        RunGame runGame = new RunGame();
        runGame.runModes();
    }
}

