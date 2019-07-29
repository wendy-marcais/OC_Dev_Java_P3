package com.gameplay.wendy;


//import org.apache.log4j.Logger;

import java.io.IOException;

public class Main {
    //private static Logger logger = Logger.getLogger(Main.class);

    /**
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        //logger.debug("test!");

        // Run the game
        RunGame runGame = new RunGame();
        runGame.runModes();
    }
}