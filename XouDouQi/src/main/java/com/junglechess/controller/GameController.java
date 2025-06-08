package com.junglechess.controller;

import com.junglechess.game.Game;
import com.junglechess.model.Player;
import com.junglechess.view.ConsoleView;

import java.util.Scanner;

/**
 * Controller component that manages user input and coordinates between the Game logic and ConsoleView.
 * This class handles the main game loop and input parsing.
 */
public class GameController {
    private Game game;
    private Scanner scanner;

    /**
     * Constructor to initialize the GameController with a Game instance.
     * @param game the game instance to control
     */
    public GameController(Game game) {
        this.game = game;
        this.scanner = new Scanner(System.in);
    }

    /**
     * Main game loop that runs the entire game session.
     */
    public void run() {
        // Initialization
        displayWelcomeMessage();
        
        // Optional: For now, we'll skip authentication until database integration
        ConsoleView.showMessage("Starting game with Player 1 (Red) and Player 2 (Blue)...");
        ConsoleView.showMessage("");
          // Main game loop
        while (!game.isGameOver()) {
            // Display current game state
            ConsoleView.displayBoard(game.getBoard());
            ConsoleView.displayCurrentPlayer(game.getCurrentPlayer());
            
            // Prompt for input with enhanced formatting
            ConsoleView.displayInputPrompt();
            String input = scanner.nextLine().trim();
            
            // Parse and handle input
            if (!handleUserInput(input)) {
                // User chose to quit
                break;
            }
        }
        
        // Game over - display final state
        if (game.isGameOver()) {
            ConsoleView.displayBoard(game.getBoard());
            
            // Determine winner based on current player (they just made the winning move)
            Player winner = game.getCurrentPlayer();
            ConsoleView.displayGameOver(winner);
        } else {
            ConsoleView.showMessage("Game ended by user. Thanks for playing!");
        }
        
        // Clean up
        scanner.close();
    }    /**
     * Displays the welcome message and initial instructions.
     */
    private void displayWelcomeMessage() {
        System.out.println();
        System.out.println("========================================");
        System.out.println("    Welcome to Xou Dou Qi (Jungle Chess)");
        System.out.println("========================================");
        System.out.println();
        System.out.println("Game Setup Complete!");
        System.out.println("Type 'help' at any time to see instructions.");
        System.out.println();
        
        // Display the legend once at startup
        ConsoleView.displayGameLegend();
    }

    /**
     * Handles user input and executes the appropriate command.
     * @param input the user's input string
     * @return true to continue the game, false to quit
     */
    private boolean handleUserInput(String input) {
        if (input.isEmpty()) {
            return true;
        }

        String[] tokens = input.toLowerCase().split("\\s+");
        String command = tokens[0];

        switch (command) {            case "help":
                ConsoleView.displayHelp();
                return true;

            case "quit":
            case "exit":
                return handleQuitCommand();

            case "move":
                return handleMoveCommand(tokens);

            default:
                ConsoleView.showMessage("Unknown command: " + command);
                ConsoleView.showMessage("Type 'help' to see available commands.");
                return true;
        }
    }    /**
     * Handles the quit command with confirmation.
     * @return false to quit the game, true to continue
     */
    private boolean handleQuitCommand() {
        System.out.println();
        System.out.print("Are you sure you want to quit? (y/n): ");
        String confirmation = scanner.nextLine().trim().toLowerCase();
        
        if (confirmation.equals("y") || confirmation.equals("yes")) {
            System.out.println("Thanks for playing Jungle Chess!");
            return false; // Quit the game
        } else {
            System.out.println("Continuing game...");
            return true; // Continue the game
        }
    }    /**
     * Handles the move command by parsing coordinates and executing the move.
     * @param tokens the tokenized input including the move command
     * @return true to continue the game
     */
    private boolean handleMoveCommand(String[] tokens) {
        if (tokens.length != 3) {
            System.out.println();
            System.out.println("Invalid move format. Use: move <from> <to>");
            System.out.println("Example: move A1 A2");
            return true;
        }

        String fromStr = tokens[1].toUpperCase();
        String toStr = tokens[2].toUpperCase();

        // Parse coordinates
        int[] fromCoords = parseCoordinates(fromStr);
        int[] toCoords = parseCoordinates(toStr);

        if (fromCoords == null || toCoords == null) {
            System.out.println();
            System.out.println("Invalid coordinates. Use format like A1, B2, etc.");
            System.out.println("Columns: A-G, Rows: 1-9");
            return true;
        }

        int fromRow = fromCoords[0];
        int fromCol = fromCoords[1];
        int toRow = toCoords[0];
        int toCol = toCoords[1];

        // Attempt to execute the move
        Player currentPlayer = game.getCurrentPlayer();
        boolean moveSuccessful = game.movePiece(currentPlayer, fromRow, fromCol, toRow, toCol);

        if (moveSuccessful) {
            ConsoleView.displayMoveSuccess(fromStr, toStr);
            
            // Check if game is over after the move
            if (game.isGameOver()) {
                // Game loop will handle the game over display
                return true;
            }
        } else {
            ConsoleView.displayMoveError("Move validation failed");
        }

        return true;
    }

    /**
     * Parses coordinate string (e.g., "A1") into row and column indices.
     * @param coordStr the coordinate string to parse
     * @return an array [row, col] with 0-based indices, or null if invalid
     */
    private int[] parseCoordinates(String coordStr) {
        if (coordStr == null || coordStr.length() != 2) {
            return null;
        }

        char colChar = coordStr.charAt(0);
        char rowChar = coordStr.charAt(1);

        // Validate column (A-G)
        if (colChar < 'A' || colChar > 'G') {
            return null;
        }

        // Validate row (1-9)
        if (rowChar < '1' || rowChar > '9') {
            return null;
        }

        // Convert to 0-based indices
        int col = colChar - 'A';  // A=0, B=1, ..., G=6
        int row = rowChar - '1';  // 1=0, 2=1, ..., 9=8

        return new int[]{row, col};
    }
}
