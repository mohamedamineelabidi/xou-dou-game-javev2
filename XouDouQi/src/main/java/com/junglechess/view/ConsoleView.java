package com.junglechess.view;

import com.junglechess.model.Board;
import com.junglechess.model.Piece;
import com.junglechess.model.Player;
import com.junglechess.model.Rank;
import com.junglechess.model.Square;
import com.junglechess.model.SquareType;

/**
 * View component for displaying the game state in the console.
 * This class is stateless and only contains static methods for rendering.
 */
public class ConsoleView {    // ANSI color codes for colored output
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_BLUE = "\u001B[34m";
    private static final String ANSI_YELLOW = "\u001B[33m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_CYAN = "\u001B[36m";
    private static final String ANSI_PURPLE = "\u001B[35m";
    private static final String ANSI_WHITE = "\u001B[37m";
    private static final String ANSI_BRIGHT_WHITE = "\u001B[97m";
    private static final String ANSI_BRIGHT_GREEN = "\u001B[92m";
    private static final String ANSI_BRIGHT_RED = "\u001B[91m";
    private static final String ANSI_BRIGHT_BLUE = "\u001B[94m";
    
    // Background colors for better visual distinction
    private static final String ANSI_BG_BLACK = "\u001B[40m";
    private static final String ANSI_BG_WHITE = "\u001B[47m";
    private static final String ANSI_BG_GRAY = "\u001B[100m";
    
    // Text styling
    private static final String ANSI_BOLD = "\u001B[1m";
    private static final String ANSI_UNDERLINE = "\u001B[4m";    /**
     * Displays the current state of the Jungle Chess board.
     * @param board the game board to display
     */
    public static void displayBoard(Board board) {
        if (board == null) {
            System.out.println("Error: Cannot display null board");
            return;
        }

        System.out.println();
          // Enhanced title with styling
        System.out.println(ANSI_BOLD + ANSI_BRIGHT_WHITE + "+==========================================+" + ANSI_RESET);
        System.out.println(ANSI_BOLD + ANSI_BRIGHT_WHITE + "|" + ANSI_BRIGHT_GREEN + "        JUNGLE CHESS BOARD        " + ANSI_BRIGHT_WHITE + "|" + ANSI_RESET);
        System.out.println(ANSI_BOLD + ANSI_BRIGHT_WHITE + "+==========================================+" + ANSI_RESET);
        System.out.println();

        // Display column headers (A-G) with enhanced styling
        System.out.print("     ");
        for (int col = 0; col < board.getColumnCount(); col++) {
            System.out.print(ANSI_BOLD + ANSI_YELLOW + " " + (char)('A' + col) + "  " + ANSI_RESET);
        }
        System.out.println();        // Display enhanced horizontal separator
        System.out.print("   " + ANSI_BRIGHT_WHITE + "+");
        for (int col = 0; col < board.getColumnCount(); col++) {
            System.out.print("---");
            if (col < board.getColumnCount() - 1) {
                System.out.print("+");
            }
        }
        System.out.println("+" + ANSI_RESET);

        // Display rows with row numbers (1-9)
        for (int row = 0; row < board.getRowCount(); row++) {            // Row number with styling
            System.out.print(ANSI_BOLD + ANSI_YELLOW + " " + (row + 1) + " " + ANSI_RESET + ANSI_BRIGHT_WHITE + "|" + ANSI_RESET);
            
            // Display cells in this row
            for (int col = 0; col < board.getColumnCount(); col++) {
                Square square = board.getSquare(row, col);
                System.out.print(formatSquareEnhanced(square));
                System.out.print(ANSI_BRIGHT_WHITE + "|" + ANSI_RESET);
            }
            System.out.println();            // Display horizontal separator (except for last row)
            if (row < board.getRowCount() - 1) {
                System.out.print("   " + ANSI_BRIGHT_WHITE + "+");
                for (int col = 0; col < board.getColumnCount(); col++) {
                    System.out.print("---");
                    if (col < board.getColumnCount() - 1) {
                        System.out.print("+");
                    }
                }
                System.out.println("+" + ANSI_RESET);
            }
        }
          // Bottom border
        System.out.print("   " + ANSI_BRIGHT_WHITE + "+");
        for (int col = 0; col < board.getColumnCount(); col++) {
            System.out.print("---");
            if (col < board.getColumnCount() - 1) {
                System.out.print("+");
            }
        }
        System.out.println("+" + ANSI_RESET);
    }    /**
     * Displays the legend for game symbols and pieces.
     */
    private static void displayLegend() {        System.out.println(ANSI_BOLD + ANSI_BRIGHT_GREEN + "+==========================================+" + ANSI_RESET);
        System.out.println(ANSI_BOLD + ANSI_BRIGHT_GREEN + "|" + ANSI_BRIGHT_WHITE + "            BOARD LEGEND            " + ANSI_BRIGHT_GREEN + "|" + ANSI_RESET);
        System.out.println(ANSI_BOLD + ANSI_BRIGHT_GREEN + "+==========================================+" + ANSI_RESET);
        System.out.println();
          // Special squares legend with enhanced visuals
        System.out.println(ANSI_BOLD + ANSI_YELLOW + "SPECIAL TERRAIN:" + ANSI_RESET);
        System.out.println("  " + ANSI_BOLD + ANSI_CYAN + "~~~" + ANSI_RESET + " - River (only RAT can enter; LION/TIGER can jump over)");
        System.out.println("  " + ANSI_BOLD + ANSI_YELLOW + "###" + ANSI_RESET + " - Trap (weakens pieces - any animal can capture trapped pieces)");
        System.out.println("  " + ANSI_BOLD + ANSI_BRIGHT_RED + "***" + ANSI_RESET + " - Red Sanctuary (Blue wins by entering)");
        System.out.println("  " + ANSI_BOLD + ANSI_BRIGHT_BLUE + "***" + ANSI_RESET + " - Blue Sanctuary (Red wins by entering)");
        System.out.println();
          // Enhanced pieces legend with both symbols and abbreviations
        System.out.println(ANSI_BOLD + ANSI_YELLOW + "ANIMALS (by strength, strongest to weakest):" + ANSI_RESET);
        System.out.println("  8. " + ANSI_BRIGHT_RED + "ELE" + ANSI_RESET + " / " + ANSI_BRIGHT_BLUE + "ELE" + ANSI_RESET + " - Elephant    " + 
                          "7. " + ANSI_BRIGHT_RED + "LIO" + ANSI_RESET + " / " + ANSI_BRIGHT_BLUE + "LIO" + ANSI_RESET + " - Lion        " +
                          "6. " + ANSI_BRIGHT_RED + "TIG" + ANSI_RESET + " / " + ANSI_BRIGHT_BLUE + "TIG" + ANSI_RESET + " - Tiger");
        System.out.println("  5. " + ANSI_BRIGHT_RED + "PAN" + ANSI_RESET + " / " + ANSI_BRIGHT_BLUE + "PAN" + ANSI_RESET + " - Panther     " + 
                          "4. " + ANSI_BRIGHT_RED + "CHI" + ANSI_RESET + " / " + ANSI_BRIGHT_BLUE + "CHI" + ANSI_RESET + " - Dog         " +
                          "3. " + ANSI_BRIGHT_RED + "LOU" + ANSI_RESET + " / " + ANSI_BRIGHT_BLUE + "LOU" + ANSI_RESET + " - Wolf");
        System.out.println("  2. " + ANSI_BRIGHT_RED + "CHA" + ANSI_RESET + " / " + ANSI_BRIGHT_BLUE + "CHA" + ANSI_RESET + " - Cat         " + 
                          "1. " + ANSI_BRIGHT_RED + "RAT" + ANSI_RESET + " / " + ANSI_BRIGHT_BLUE + "RAT" + ANSI_RESET + " - Rat (can capture Elephant!)");
        System.out.println();
        System.out.println("Colors: " + ANSI_BOLD + ANSI_BRIGHT_RED + "Red Player" + ANSI_RESET + " vs " + ANSI_BOLD + ANSI_BRIGHT_BLUE + "Blue Player" + ANSI_RESET);
    }

    /**
     * Displays the legend for the game at startup.
     */
    public static void displayGameLegend() {
        displayLegend();
        System.out.println();
    }    /**
     * Formats a square for enhanced display with Unicode symbols and better colors.
     * @param square the square to format
     * @return a formatted string representing the square
     */
    private static String formatSquareEnhanced(Square square) {
        if (square == null) {
            return " ? ";
        }

        Piece piece = square.getPiece();
        SquareType type = square.getType();

        // Format based on square type and piece
        if (piece != null) {
            String pieceSymbol = getPieceSymbol(piece);
              // Color the piece based on player with enhanced colors
            if (piece.getOwner() != null) {
                // Try to determine color by checking common patterns
                boolean isRedPlayer = isRedPlayerPiece(piece);
                
                if (isRedPlayer) {
                    return ANSI_BOLD + ANSI_BRIGHT_RED + pieceSymbol + ANSI_RESET;
                } else {
                    return ANSI_BOLD + ANSI_BRIGHT_BLUE + pieceSymbol + ANSI_RESET;
                }
            }
            return ANSI_BOLD + ANSI_WHITE + pieceSymbol + ANSI_RESET;
        } else {            // Display special squares with enhanced styling
            switch (type) {
                case RIVIERE:
                    return ANSI_BOLD + ANSI_CYAN + "~~~" + ANSI_RESET;
                case PIEGE:
                    return ANSI_BOLD + ANSI_YELLOW + "###" + ANSI_RESET;
                case SANCTUAIRE_RED:
                    return ANSI_BOLD + ANSI_BRIGHT_RED + "***" + ANSI_RESET;
                case SANCTUAIRE_BLUE:
                    return ANSI_BOLD + ANSI_BRIGHT_BLUE + "***" + ANSI_RESET;
                case NORMAL:
                default:
                    return "   ";
            }
        }
    }
    
    /**
     * Helper method to determine if a piece belongs to the red player.
     * This is a temporary solution - ideally we'd have better context.
     */
    private static boolean isRedPlayerPiece(Piece piece) {
        // For now, we'll assume the first player encountered is red
        // This is not perfect but works for the current game structure
        // A better solution would be to pass game context or player colors
        return piece.getOwner().getName().equals("asmae") || 
               piece.getOwner().getName().toLowerCase().contains("red") ||
               piece.getOwner().getName().equals("player1");
    }
      /**
     * Gets the symbol for a piece - using ASCII-safe characters.
     * @param piece the piece to get the symbol for
     * @return a simple text symbol representing the piece
     */
    private static String getPieceSymbol(Piece piece) {
        if (piece == null) {
            return "   ";
        }

        Rank rank = piece.getRank();
        switch (rank) {
            case ELEPHANT: return "ELE";  // Elephant
            case LION:     return "LIO";  // Lion
            case TIGRE:    return "TIG";  // Tiger
            case PANTHERE: return "PAN";  // Panther
            case CHIEN:    return "CHI";  // Dog
            case LOUP:     return "LOU";  // Wolf
            case CHAT:     return "CHA";  // Cat
            case RAT:      return "RAT";  // Rat
            default:       return "???";  // Unknown
        }
    }/**
     * Formats a square for display based on its type and piece (original version).
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
            
            // Color the piece based on player
            if (piece.getOwner() != null) {
                boolean isRedPlayer = isRedPlayerPiece(piece);
                if (isRedPlayer) {
                    return ANSI_RED + pieceStr + ANSI_RESET;
                } else {
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
        }    }
    
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
        System.out.println();        System.out.println(ANSI_BOLD + ANSI_BRIGHT_GREEN + "+======================================================================+" + ANSI_RESET);
        System.out.println(ANSI_BOLD + ANSI_BRIGHT_GREEN + "|" + ANSI_BRIGHT_WHITE + "                  JUNGLE CHESS HELP                          " + ANSI_BRIGHT_GREEN + "|" + ANSI_RESET);
        System.out.println(ANSI_BOLD + ANSI_BRIGHT_GREEN + "|" + ANSI_BRIGHT_WHITE + "                    XOU DOU QI                              " + ANSI_BRIGHT_GREEN + "|" + ANSI_RESET);
        System.out.println(ANSI_BOLD + ANSI_BRIGHT_GREEN + "+======================================================================+" + ANSI_RESET);
        System.out.println();
          System.out.println(ANSI_BOLD + ANSI_YELLOW + "AVAILABLE COMMANDS:" + ANSI_RESET);
        System.out.println("  * " + ANSI_BOLD + ANSI_CYAN + "move <from> <to>" + ANSI_RESET + "   - Move a piece (e.g., 'move A1 A2')");
        System.out.println("  ? " + ANSI_BOLD + ANSI_CYAN + "help" + ANSI_RESET + "               - Display this help message");
        System.out.println("  X " + ANSI_BOLD + ANSI_CYAN + "quit" + ANSI_RESET + " or " + ANSI_BOLD + ANSI_CYAN + "exit" + ANSI_RESET + "      - Exit the game (with confirmation)");
        System.out.println();
          System.out.println(ANSI_BOLD + ANSI_YELLOW + "COORDINATE SYSTEM:" + ANSI_RESET);
        System.out.println("  * Columns: A-G (left to right)");
        System.out.println("  * Rows: 1-9 (top to bottom)");
        System.out.println("  * Examples: A1 (top-left), G9 (bottom-right), D5 (center)");
        System.out.println();
          System.out.println(ANSI_BOLD + ANSI_YELLOW + "GAME RULES:" + ANSI_RESET);
        System.out.println("  1. " + ANSI_BOLD + ANSI_GREEN + "Movement:" + ANSI_RESET + " Animals move to adjacent squares (up/down/left/right)");
        System.out.println("  2. " + ANSI_BOLD + ANSI_GREEN + "Capturing:" + ANSI_RESET + " Animals can capture equal or lower-ranked opponents");
        System.out.println("  3. " + ANSI_BOLD + ANSI_GREEN + "Special:" + ANSI_RESET + " RAT can capture ELEPHANT (strongest captures weakest!)");
        System.out.println("  4. " + ANSI_BOLD + ANSI_GREEN + "River:" + ANSI_RESET + " Only RAT can enter river squares");
        System.out.println("  5. " + ANSI_BOLD + ANSI_GREEN + "Jumping:" + ANSI_RESET + " LION and TIGER can jump over river (horizontally/vertically)");
        System.out.println("  6. " + ANSI_BOLD + ANSI_GREEN + "Traps:" + ANSI_RESET + " Animals in enemy traps are weakened (any piece can capture them)");
        System.out.println("  7. " + ANSI_BOLD + ANSI_GREEN + "Victory:" + ANSI_RESET + " Win by moving any piece into opponent's sanctuary");
        System.out.println();
          System.out.println(ANSI_BOLD + ANSI_YELLOW + "PIECE RANKINGS (strongest to weakest):" + ANSI_RESET);
        System.out.println("  8. " + ANSI_BOLD + ANSI_GREEN + "ELE" + ANSI_RESET + " - Elephant  |  7. " + ANSI_BOLD + ANSI_GREEN + "LIO" + ANSI_RESET + " - Lion      |  6. " + ANSI_BOLD + ANSI_GREEN + "TIG" + ANSI_RESET + " - Tiger");
        System.out.println("  5. " + ANSI_BOLD + ANSI_GREEN + "PAN" + ANSI_RESET + " - Panther   |  4. " + ANSI_BOLD + ANSI_GREEN + "CHI" + ANSI_RESET + " - Dog       |  3. " + ANSI_BOLD + ANSI_GREEN + "LOU" + ANSI_RESET + " - Wolf");
        System.out.println("  2. " + ANSI_BOLD + ANSI_GREEN + "CHA" + ANSI_RESET + " - Cat       |  1. " + ANSI_BOLD + ANSI_GREEN + "RAT" + ANSI_RESET + " - Rat (can capture Elephant!)");
        System.out.println();
          System.out.println(ANSI_BOLD + ANSI_YELLOW + "TIPS FOR NEW PLAYERS:" + ANSI_RESET);
        System.out.println("  * Plan your moves carefully - traps can change the game!");
        System.out.println("  * Use your RAT strategically - it's your only river piece");
        System.out.println("  * Lions and Tigers are powerful jumpers - use rivers tactically");
        System.out.println("  * Protect your sanctuary while advancing toward the opponent's");
        System.out.println("  * Remember: RAT beats ELEPHANT, but everything else beats RAT");
        System.out.println();
    }/**
     * Displays the current player's turn with enhanced formatting.
     * @param player the current player
     */
    public static void displayCurrentPlayer(Player player) {
        if (player == null) {
            return;
        }
          String playerName = player.getName().toUpperCase();
        boolean isRedPlayer = isRedPlayerPiece(new Piece(Rank.RAT, player)); // Temporary hack to determine color
        String colorCode = isRedPlayer ? ANSI_BRIGHT_RED : ANSI_BRIGHT_BLUE;
        String playerIcon = isRedPlayer ? "[R]" : "[B]";
        
        System.out.println();
        System.out.println(ANSI_BOLD + colorCode + "+==========================================+" + ANSI_RESET);
        System.out.println(ANSI_BOLD + colorCode + "|  " + playerIcon + " CURRENT TURN: " + playerName + 
                          (" ".repeat(Math.max(0, 20 - playerName.length()))) + " |" + ANSI_RESET);
        System.out.println(ANSI_BOLD + colorCode + "+==========================================+" + ANSI_RESET);
    }
      /**
     * Displays an enhanced input prompt with examples.
     */
    public static void displayInputPrompt() {
        System.out.println();        System.out.print(ANSI_BOLD + ANSI_YELLOW + ">> Enter command" + ANSI_RESET + " (e.g., '" + 
                        ANSI_BOLD + ANSI_CYAN + "move A1 A2" + ANSI_RESET + "', '" + 
                        ANSI_BOLD + ANSI_CYAN + "help" + ANSI_RESET + "', '" + 
                        ANSI_BOLD + ANSI_CYAN + "quit" + ANSI_RESET + "'): ");
    }
    
    /**
     * Displays a success message for valid moves.
     * @param fromCoord the source coordinate
     * @param toCoord the destination coordinate
     */
    public static void displayMoveSuccess(String fromCoord, String toCoord) {
        System.out.println(ANSI_BOLD + ANSI_BRIGHT_GREEN + "[OK] Move successful: " + fromCoord + " -> " + toCoord + ANSI_RESET);
    }
      /**
     * Displays an enhanced error message for invalid moves.
     * @param reason the reason for the invalid move
     */
    public static void displayMoveError(String reason) {
        System.out.println();        System.out.println(ANSI_BOLD + ANSI_BRIGHT_RED + "[X] Invalid move!" + ANSI_RESET);
        if (reason != null && !reason.isEmpty()) {
            System.out.println(ANSI_BOLD + ANSI_YELLOW + "Reason: " + ANSI_RESET + reason);
        }
        System.out.println();
        System.out.println(ANSI_BOLD + ANSI_WHITE + "Common issues to check:" + ANSI_RESET);
        System.out.println("  * You have a piece at the source position");
        System.out.println("  * The destination is adjacent (or valid jump for Lion/Tiger)");
        System.out.println("  * Move follows capture rules (higher/equal rank, or RAT vs Elephant)");
        System.out.println("  * River rules: only RAT can enter, Lion/Tiger can jump over");
        System.out.println("  * You're not moving into your own sanctuary");
        System.out.println();
    }
    
    /**
     * Displays a game over message with the winner.
     * @param winner the winning player
     */
    public static void displayGameOver(Player winner) {        if (winner == null) {
            System.out.println();
            System.out.println(ANSI_BOLD + ANSI_YELLOW + "Game Over! It's a draw!" + ANSI_RESET);
            System.out.println();
            return;
        }
        
        String playerName = winner.getName().toUpperCase();
        boolean isRedPlayer = isRedPlayerPiece(new Piece(Rank.RAT, winner)); // Temporary hack
        String colorCode = isRedPlayer ? ANSI_BRIGHT_RED : ANSI_BRIGHT_BLUE;
        String trophy = "[WINNER]";
        String celebration = isRedPlayer ? "[RED WINS]" : "[BLUE WINS]";
        
        System.out.println();
        System.out.println(ANSI_BOLD + ANSI_YELLOW + "+==========================================+" + ANSI_RESET);
        System.out.println(ANSI_BOLD + ANSI_YELLOW + "|" + ANSI_BRIGHT_WHITE + "               GAME OVER!               " + ANSI_YELLOW + "|" + ANSI_RESET);
        System.out.println(ANSI_BOLD + ANSI_YELLOW + "+==========================================+" + ANSI_RESET);
        System.out.println();
        System.out.println(ANSI_BOLD + colorCode + "*** CONGRATULATIONS " + playerName + "! " + trophy + " ***" + ANSI_RESET);
        System.out.println(ANSI_BOLD + ANSI_WHITE + "You have successfully entered the enemy sanctuary!" + ANSI_RESET);
        System.out.println(celebration + " " + ANSI_BOLD + colorCode + "VICTORY!" + ANSI_RESET + " " + celebration);
        System.out.println();
    }
    
    /**
     * Displays enhanced player statistics with better formatting.
     * @param playerName the player's name
     * @param wins number of wins
     * @param losses number of losses
     */
    public static void displayPlayerStats(String playerName, int wins, int losses) {        boolean isRedPlayer = playerName.equals("asmae") || playerName.toLowerCase().contains("red");
        String colorCode = isRedPlayer ? ANSI_BRIGHT_RED : ANSI_BRIGHT_BLUE;
        String playerIcon = isRedPlayer ? "[R]" : "[B]";
        
        System.out.println();
        System.out.println(ANSI_BOLD + colorCode + "+==========================================+" + ANSI_RESET);
        System.out.println(ANSI_BOLD + colorCode + "|  " + playerIcon + " PLAYER: " + playerName.toUpperCase() + 
                          (" ".repeat(Math.max(0, 25 - playerName.length()))) + " |" + ANSI_RESET);
        System.out.println(ANSI_BOLD + colorCode + "+==========================================+" + ANSI_RESET);
        System.out.println(ANSI_BOLD + colorCode + "|  [W] Wins: " + String.format("%-27s", wins) + " |" + ANSI_RESET);
        System.out.println(ANSI_BOLD + colorCode + "|  [L] Losses: " + String.format("%-25s", losses) + " |" + ANSI_RESET);
        
        // Calculate win rate
        int totalGames = wins + losses;
        double winRate = totalGames > 0 ? (double) wins / totalGames * 100 : 0;
        System.out.println(ANSI_BOLD + colorCode + "|  [%] Win Rate: " + String.format("%.1f%%%-18s", winRate, "") + " |" + ANSI_RESET);
        System.out.println(ANSI_BOLD + colorCode + "+==========================================+" + ANSI_RESET);
    }
    
    /**
     * Displays enhanced game statistics header.
     */
    public static void displayStatsHeader() {
        System.out.println();        System.out.println(ANSI_BOLD + ANSI_YELLOW + "+======================================================================+" + ANSI_RESET);
        System.out.println(ANSI_BOLD + ANSI_YELLOW + "|" + ANSI_BRIGHT_WHITE + "                    PLAYER STATISTICS                         " + ANSI_YELLOW + "|" + ANSI_RESET);
        System.out.println(ANSI_BOLD + ANSI_YELLOW + "+======================================================================+" + ANSI_RESET);
    }
}
