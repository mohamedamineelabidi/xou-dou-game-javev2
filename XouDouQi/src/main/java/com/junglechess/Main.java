package com.junglechess;

import com.junglechess.controller.GameController;
import com.junglechess.db.DatabaseManager;
import com.junglechess.game.Game;
import com.junglechess.model.Player;

/**
 * Main entry point for the Xou Dou Qi (Jungle Chess) game.
 * This class initializes the database, handles player authentication,
 * and starts the game loop.
 */
public class Main {
    public static void main(String[] args) {
        try {
            // Initialize database manager
            DatabaseManager dbManager = new DatabaseManager();
            dbManager.connect();
            dbManager.setupTables();
            
            // Create initial dummy game (will be replaced after authentication)
            Player dummyPlayer = new Player("Dummy");
            Game game = new Game(dummyPlayer, dummyPlayer);
            
            // Create the game controller (it will handle authentication and create a new game)
            GameController controller = new GameController(game);
            
            // Start the game
            controller.run();
            
        } catch (Exception e) {
            System.err.println("Error starting the game: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
