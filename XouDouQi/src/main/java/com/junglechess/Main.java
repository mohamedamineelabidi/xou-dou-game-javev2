package com.junglechess;

import com.junglechess.controller.GameController;
import com.junglechess.game.Game;
import com.junglechess.model.Player;

/**
 * Main entry point for the Xou Dou Qi (Jungle Chess) game
 */
public class Main {
    public static void main(String[] args) {
        // Create two players
        Player player1 = new Player("Red");
        Player player2 = new Player("Blue");
        
        // Create a new game
        Game game = new Game(player1, player2);
        
        // Create the game controller
        GameController controller = new GameController(game);
        
        // Start the game
        controller.run();
    }
}
