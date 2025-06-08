package com.junglechess.view;

import com.junglechess.model.Board;
import com.junglechess.model.Piece;
import com.junglechess.model.Square;
import com.junglechess.model.SquareType;
import com.junglechess.model.Player;
import com.junglechess.model.Rank;

/**
 * View component for displaying the game state in the console.
 * This class is stateless and only contains static methods for rendering.
 */
public class ConsoleView {

    // ANSI color codes for colored output
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_BLUE = "\u001B[34m";
    private static final String ANSI_YELLOW = "\u001B[33m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_CYAN = "\u001B[36m";    /**
     * Displays the current state of the Jungle Chess board.
     * @param board the game board to display
     */
    public static void displayBoard(Board board) {
        if (board == null) {
            System.out.println("Error: Cannot display null board");
            return;
        }

        System.out.println();

        // Display column headers (A-G)
        System.out.print("    ");
        for (int col = 0; col < board.getColumnCount(); col++) {
            System.out.print(" " + (char)('A' + col) + "  ");
        }
        System.out.println();

        // Display horizontal separator
        System.out.print("   +");
        for (int col = 0; col < board.getColumnCount(); col++) {
            System.out.print("---+");
        }
        System.out.println();

        // Display rows with row numbers (1-9)
        for (int row = 0; row < board.getRowCount(); row++) {
            // Row number
            System.out.printf(" %d |", row + 1);
            
            // Display cells in this row
            for (int col = 0; col < board.getColumnCount(); col++) {
                Square square = board.getSquare(row, col);
                System.out.print(formatSquare(square));
                System.out.print("|");
            }
            System.out.println();

            // Display horizontal separator
            System.out.print("   +");
            for (int col = 0; col < board.getColumnCount(); col++) {
                System.out.print("---+");
            }
            System.out.println();
        }
    }    /**
     * Displays the legend for game symbols and pieces.
     */
    private static void displayLegend() {
        System.out.println(ANSI_GREEN + "=== BOARD LEGEND ===" + ANSI_RESET);
        System.out.println();
        
        // Special squares legend
        System.out.println("SPECIAL SQUARES:");
        System.out.println("  " + ANSI_CYAN + "~~~" + ANSI_RESET + " - River (only RAT can enter; LION/TIGER can jump over)");
        System.out.println("  " + ANSI_YELLOW + "###" + ANSI_RESET + " - Trap (weakens pieces - any animal can capture trapped pieces)");
        System.out.println("  " + ANSI_RED + "***" + ANSI_RESET + " - Red Sanctuary (Blue wins by entering)");
        System.out.println("  " + ANSI_BLUE + "***" + ANSI_RESET + " - Blue Sanctuary (Red wins by entering)");
        System.out.println();
        
        // Pieces legend with colors
        System.out.println("PIECES (by strength, strongest to weakest):");
        System.out.println("  " + ANSI_RED + "ELE" + ANSI_RESET + "/" + ANSI_BLUE + "ELE" + ANSI_RESET + " - Elephant (8)    " + 
                          ANSI_RED + "LIO" + ANSI_RESET + "/" + ANSI_BLUE + "LIO" + ANSI_RESET + " - Lion (7)        " +
                          ANSI_RED + "TIG" + ANSI_RESET + "/" + ANSI_BLUE + "TIG" + ANSI_RESET + " - Tiger (6)");
        System.out.println("  " + ANSI_RED + "PAN" + ANSI_RESET + "/" + ANSI_BLUE + "PAN" + ANSI_RESET + " - Panther (5)    " + 
                          ANSI_RED + "CHI" + ANSI_RESET + "/" + ANSI_BLUE + "CHI" + ANSI_RESET + " - Dog (4)         " +
                          ANSI_RED + "LOU" + ANSI_RESET + "/" + ANSI_BLUE + "LOU" + ANSI_RESET + " - Wolf (3)");
        System.out.println("  " + ANSI_RED + "CHA" + ANSI_RESET + "/" + ANSI_BLUE + "CHA" + ANSI_RESET + " - Cat (2)        " + 
                          ANSI_RED + "RAT" + ANSI_RESET + "/" + ANSI_BLUE + "RAT" + ANSI_RESET + " - Rat (1, can capture Elephant!)");
        System.out.println();
        System.out.println("Colors: " + ANSI_RED + "Red Player" + ANSI_RESET + " vs " + ANSI_BLUE + "Blue Player" + ANSI_RESET);
    }

    /**
     * Displays the legend for the game at startup.
     */
    public static void displayGameLegend() {
        displayLegend();
        System.out.println();
    }

    /**
     * Formats a square for display based on its type and piece.
     * @param square the square to format
     * @return a formatted string representing the square
     */
    private static String formatSquare(Square square) {
        if (square == null) {
            return " ? ";
        }

        Piece piece = square.getPiece();
        SquareType type = square.getType();

        // Format based on square type and piece
        if (piece != null) {
            String pieceStr = getPieceAbbreviation(piece);
            
            // Color the piece based on player (Red or Blue)
            if (piece.getOwner() != null) {
                String playerName = piece.getOwner().getName();
                if ("Red".equals(playerName)) {
                    return ANSI_RED + pieceStr + ANSI_RESET;
                } else if ("Blue".equals(playerName)) {
                    return ANSI_BLUE + pieceStr + ANSI_RESET;
                }
            }
            return pieceStr;
        } else {
            // Display special squares
            switch (type) {
                case RIVIERE:
                    return ANSI_CYAN + "~~~" + ANSI_RESET;
                case PIEGE:
                    return ANSI_YELLOW + "###" + ANSI_RESET;
                case SANCTUAIRE_RED:
                    return ANSI_RED + "***" + ANSI_RESET;
                case SANCTUAIRE_BLUE:
                    return ANSI_BLUE + "***" + ANSI_RESET;
                case NORMAL:
                default:
                    return "   ";
            }
        }
    }

    /**
     * Gets the abbreviation for a piece.
     * @param piece the piece to get the abbreviation for
     * @return a 3-character abbreviation representing the piece
     */
    private static String getPieceAbbreviation(Piece piece) {
        if (piece == null) {
            return "   ";
        }

        Rank rank = piece.getRank();
        switch (rank) {
            case ELEPHANT: return "ELE";
            case LION:     return "LIO";
            case TIGRE:    return "TIG";
            case PANTHERE: return "PAN";
            case CHIEN:    return "CHI";
            case LOUP:     return "LOU";
            case CHAT:     return "CHA";
            case RAT:      return "RAT";
            default:       return "???";
        }
    }

    /**
     * Displays a message to the user.
     * @param message the message to display
     */
    public static void showMessage(String message) {
        System.out.println(message);
    }    /**
     * Displays help information for the game.
     */
    public static void displayHelp() {
        System.out.println();
        System.out.println(ANSI_GREEN + "===============================================" + ANSI_RESET);
        System.out.println(ANSI_GREEN + "===        JUNGLE CHESS (XOU DOU QI)       ===" + ANSI_RESET);
        System.out.println(ANSI_GREEN + "===                 HELP                   ===" + ANSI_RESET);
        System.out.println(ANSI_GREEN + "===============================================" + ANSI_RESET);
        System.out.println();
        
        System.out.println(ANSI_YELLOW + "AVAILABLE COMMANDS:" + ANSI_RESET);
        System.out.println("  " + ANSI_CYAN + "move <from> <to>" + ANSI_RESET + "   - Move a piece (e.g., 'move A1 A2')");
        System.out.println("  " + ANSI_CYAN + "help" + ANSI_RESET + "               - Display this help message");
        System.out.println("  " + ANSI_CYAN + "quit" + ANSI_RESET + " or " + ANSI_CYAN + "exit" + ANSI_RESET + "      - Exit the game (with confirmation)");
        System.out.println();
        
        System.out.println(ANSI_YELLOW + "COORDINATE SYSTEM:" + ANSI_RESET);
        System.out.println("  Columns: A-G (left to right)");
        System.out.println("  Rows: 1-9 (top to bottom)");
        System.out.println("  Examples: A1 (top-left), G9 (bottom-right), D5 (center)");
        System.out.println();
        
        System.out.println(ANSI_YELLOW + "GAME RULES:" + ANSI_RESET);
        System.out.println("  1. " + ANSI_GREEN + "Movement:" + ANSI_RESET + " Animals move to adjacent squares (up, down, left, right)");
        System.out.println("  2. " + ANSI_GREEN + "Capturing:" + ANSI_RESET + " Animals can capture equal or lower-ranked opponents");
        System.out.println("  3. " + ANSI_GREEN + "Special:" + ANSI_RESET + " RAT can capture ELEPHANT (strongest captures weakest!)");
        System.out.println("  4. " + ANSI_GREEN + "River:" + ANSI_RESET + " Only RAT can enter river squares");
        System.out.println("  5. " + ANSI_GREEN + "Jumping:" + ANSI_RESET + " LION and TIGER can jump over river (horizontally/vertically)");
        System.out.println("  6. " + ANSI_GREEN + "Traps:" + ANSI_RESET + " Animals in enemy traps are weakened (any piece can capture them)");
        System.out.println("  7. " + ANSI_GREEN + "Victory:" + ANSI_RESET + " Win by moving any piece into opponent's sanctuary");
        System.out.println();
        
        System.out.println(ANSI_YELLOW + "PIECE RANKINGS (strongest to weakest):" + ANSI_RESET);
        System.out.println("  8. " + ANSI_GREEN + "ELE" + ANSI_RESET + " - Elephant  │  7. " + ANSI_GREEN + "LIO" + ANSI_RESET + " - Lion      │  6. " + ANSI_GREEN + "TIG" + ANSI_RESET + " - Tiger");
        System.out.println("  5. " + ANSI_GREEN + "PAN" + ANSI_RESET + " - Panther   │  4. " + ANSI_GREEN + "CHI" + ANSI_RESET + " - Dog       │  3. " + ANSI_GREEN + "LOU" + ANSI_RESET + " - Wolf");
        System.out.println("  2. " + ANSI_GREEN + "CHA" + ANSI_RESET + " - Cat       │  1. " + ANSI_GREEN + "RAT" + ANSI_RESET + " - Rat (can capture Elephant!)");
        System.out.println();
        
        System.out.println(ANSI_YELLOW + "TIPS FOR NEW PLAYERS:" + ANSI_RESET);
        System.out.println("  • Plan your moves carefully - traps can change the game!");
        System.out.println("  • Use your RAT strategically - it's your only river piece");        System.out.println("  • Lions and Tigers are powerful jumpers - use rivers tactically");
        System.out.println("  • Protect your sanctuary while advancing toward the opponent's");
        System.out.println("  • Remember: RAT beats ELEPHANT, but everything else beats RAT");
        System.out.println();
    }
      /**
     * Displays the current player's turn with enhanced formatting.
     * @param player the current player
     */
    public static void displayCurrentPlayer(Player player) {
        if (player == null) {
            return;
        }
        
        String playerName = player.getName();
        String colorCode = "Red".equals(playerName) ? ANSI_RED : ANSI_BLUE;
        
        System.out.println();
        System.out.println(colorCode + "┌─────────────────────────────────────┐" + ANSI_RESET);
        System.out.println(colorCode + "│  Current Turn: " + playerName.toUpperCase() + " PLAYER" + 
                          (" ".repeat(Math.max(0, 14 - playerName.length()))) + " │" + ANSI_RESET);
        System.out.println(colorCode + "└─────────────────────────────────────┘" + ANSI_RESET);
    }
    
    /**
     * Displays an enhanced input prompt with examples.
     */
    public static void displayInputPrompt() {
        System.out.println();
        System.out.print(ANSI_YELLOW + "Enter command" + ANSI_RESET + " (e.g., '" + ANSI_CYAN + "move A1 A2" + ANSI_RESET + "', '" + 
                        ANSI_CYAN + "help" + ANSI_RESET + "', '" + ANSI_CYAN + "quit" + ANSI_RESET + "'): ");
    }
    
    /**
     * Displays a success message for valid moves.
     * @param fromCoord the source coordinate
     * @param toCoord the destination coordinate
     */
    public static void displayMoveSuccess(String fromCoord, String toCoord) {
        System.out.println(ANSI_GREEN + "✓ Move successful: " + fromCoord + " → " + toCoord + ANSI_RESET);
    }
    
    /**
     * Displays an enhanced error message for invalid moves.
     * @param reason the reason for the invalid move
     */
    public static void displayMoveError(String reason) {
        System.out.println();
        System.out.println(ANSI_RED + "✗ Invalid move!" + ANSI_RESET);
        if (reason != null && !reason.isEmpty()) {
            System.out.println(ANSI_YELLOW + "Reason: " + ANSI_RESET + reason);
        }
        System.out.println();
        System.out.println("Common issues to check:");
        System.out.println("  • You have a piece at the source position");
        System.out.println("  • The destination is adjacent (or valid jump for Lion/Tiger)");
        System.out.println("  • Move follows capture rules (higher/equal rank, or RAT vs Elephant)");
        System.out.println("  • River rules: only RAT can enter, Lion/Tiger can jump over");
        System.out.println("  • You're not moving into your own sanctuary");
        System.out.println();
    }
    
    /**
     * Displays a game over message with the winner.
     * @param winner the winning player
     */
    public static void displayGameOver(Player winner) {
        if (winner == null) {
            System.out.println(ANSI_YELLOW + "Game Over! It's a draw!" + ANSI_RESET);
            return;
        }
        
        String playerName = winner.getName();
        String colorCode = "Red".equals(playerName) ? ANSI_RED : ANSI_BLUE;
        
        System.out.println();
        System.out.println(ANSI_YELLOW + "========================" + ANSI_RESET);
        System.out.println(ANSI_YELLOW + "=      GAME OVER      =" + ANSI_RESET);
        System.out.println(ANSI_YELLOW + "========================" + ANSI_RESET);
        System.out.println();
        System.out.println(colorCode + "Player " + playerName + " wins!" + ANSI_RESET);
        System.out.println();
    }
}
