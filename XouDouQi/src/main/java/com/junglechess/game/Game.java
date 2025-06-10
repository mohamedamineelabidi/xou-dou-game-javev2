package com.junglechess.game;

import com.junglechess.model.Board;
import com.junglechess.model.Piece;
import com.junglechess.model.Player;
import com.junglechess.model.Rank;
import com.junglechess.model.Square;
import com.junglechess.model.SquareType;

/**
 * Class representing the game engine for Jungle Chess.
 * Handles all game rules, move validation, and game state.
 */
public class Game {
    private Board board;
    private Player player1;
    private Player player2;
    private Player currentPlayer;    /**
     * Constructor to initialize a new game with two players.
     * @param player1 the first player
     * @param player2 the second player
     */
    public Game(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
        this.board = new Board(player1, player2); // Pass players to board
        this.currentPlayer = player1; // Player 1 starts the game
    }

    /**
     * Validates and executes a move if valid.
     * @param player the player attempting to move
     * @param fromRow the source row
     * @param fromCol the source column
     * @param toRow the destination row
     * @param toCol the destination column
     * @return true if move was successful, false otherwise
     */    public boolean movePiece(Player player, int fromRow, int fromCol, int toRow, int toCol) {
        // Check if it's the player's turn
        if (!player.equals(currentPlayer)) {
            return false;
        }

        // Check if source and destination are within bounds
        Square sourceSquare = board.getSquare(fromRow, fromCol);
        Square destSquare = board.getSquare(toRow, toCol);
        
        if (sourceSquare == null || destSquare == null) {
            return false;
        }

        // Check if there's a piece at the source square and it belongs to the player
        Piece piece = sourceSquare.getPiece();
        
        // DEBUG: Add debug output
        System.out.println("=== MOVE DEBUG ===");
        System.out.println("From: (" + fromRow + "," + fromCol + ") To: (" + toRow + "," + toCol + ")");
        System.out.println("Piece at source: " + (piece != null ? piece.getRank() : "null"));
        if (piece != null) {
            System.out.println("Piece owner: '" + piece.getOwner().getName() + "'");
            System.out.println("Piece owner object: " + piece.getOwner());
        }
        System.out.println("Current player: '" + player.getName() + "'");
        System.out.println("Current player object: " + player);        System.out.println("Players equal (==): " + (piece != null ? piece.getOwner().equals(player) : "N/A"));
        System.out.println("==================");
        
        if (piece == null || !piece.getOwner().equals(player)) {
            return false;
        }

        // Check if move is adjacent (no diagonals)
        if (!isAdjacentMove(fromRow, fromCol, toRow, toCol)) {
            // Special case for Lion and Tiger jumping over river
            if ((piece.getRank() == Rank.LION || piece.getRank() == Rank.TIGRE) && 
                canJumpRiver(fromRow, fromCol, toRow, toCol)) {
                // Continue processing the move
            } else {
                return false;
            }
        }        // Check if destination is the player's own sanctuary
        if ((player.equals(player1) && destSquare.getType() == SquareType.SANCTUAIRE_RED) ||
            (player.equals(player2) && destSquare.getType() == SquareType.SANCTUAIRE_BLUE)) {
            return false;
        }

        // Check river rule: Only RAT can enter water
        if (destSquare.getType() == SquareType.RIVIERE && piece.getRank() != Rank.RAT) {
            return false;
        }

        // Check if destination has a piece that can be captured
        Piece targetPiece = destSquare.getPiece();
        if (targetPiece != null) {            // Trap rule: Any piece in an enemy trap can be captured by any piece
            boolean isInEnemyTrap = 
                (targetPiece.getOwner().equals(player1) && destSquare.getType() == SquareType.PIEGE && 
                 isBluePlayerTrap(toRow, toCol)) ||
                (targetPiece.getOwner().equals(player2) && destSquare.getType() == SquareType.PIEGE && 
                 isRedPlayerTrap(toRow, toCol));
                
            // Check if the piece can capture the target
            if (!isInEnemyTrap && !piece.canCapture(targetPiece)) {
                return false;
            }
        }

        // All validations passed, execute the move
        destSquare.setPiece(piece);
        sourceSquare.setPiece(null);        // Check for win condition
        if ((player.equals(player1) && destSquare.getType() == SquareType.SANCTUAIRE_BLUE) ||
            (player.equals(player2) && destSquare.getType() == SquareType.SANCTUAIRE_RED)) {
            // Player has entered opponent's sanctuary, game is won
            return true;
        }

        // Switch turns
        switchTurn();
        return true;
    }

    /**
     * Checks if a move is to an adjacent square (no diagonals).
     * @param fromRow source row
     * @param fromCol source column
     * @param toRow destination row
     * @param toCol destination column
     * @return true if the move is adjacent, false otherwise
     */
    private boolean isAdjacentMove(int fromRow, int fromCol, int toRow, int toCol) {
        int rowDiff = Math.abs(toRow - fromRow);
        int colDiff = Math.abs(toCol - fromCol);
        
        // Adjacent means exactly one square horizontally or vertically
        return (rowDiff == 1 && colDiff == 0) || (rowDiff == 0 && colDiff == 1);
    }

    /**
     * Checks if a Lion or Tiger can jump over the river.
     * @param fromRow source row
     * @param fromCol source column
     * @param toRow destination row
     * @param toCol destination column
     * @return true if the jump is valid, false otherwise
     */
    private boolean canJumpRiver(int fromRow, int fromCol, int toRow, int toCol) {
        // Jump must be horizontal or vertical, not diagonal
        if (fromRow != toRow && fromCol != toCol) {
            return false;
        }
        
        // Check if jumping horizontally
        if (fromRow == toRow) {
            int minCol = Math.min(fromCol, toCol);
            int maxCol = Math.max(fromCol, toCol);
            
            // Verify it's a river jump
            boolean isRiverJump = false;
            for (int col = minCol + 1; col < maxCol; col++) {
                Square square = board.getSquare(fromRow, col);
                if (square.getType() == SquareType.RIVIERE) {
                    isRiverJump = true;
                } else if (square.getPiece() != null) {
                    // Can't jump over pieces
                    return false;
                }
            }
            
            // Check for Rat in the river blocking the jump
            if (isRiverJump) {
                for (int col = minCol + 1; col < maxCol; col++) {
                    if (hasRatInRiver(fromRow, col)) {
                        return false;
                    }
                }
                return true;
            }
        }
        
        // Check if jumping vertically
        if (fromCol == toCol) {
            int minRow = Math.min(fromRow, toRow);
            int maxRow = Math.max(fromRow, toRow);
            
            // Verify it's a river jump
            boolean isRiverJump = false;
            for (int row = minRow + 1; row < maxRow; row++) {
                Square square = board.getSquare(row, fromCol);
                if (square.getType() == SquareType.RIVIERE) {
                    isRiverJump = true;
                } else if (square.getPiece() != null) {
                    // Can't jump over pieces
                    return false;
                }
            }
            
            // Check for Rat in the river blocking the jump
            if (isRiverJump) {
                for (int row = minRow + 1; row < maxRow; row++) {
                    if (hasRatInRiver(row, fromCol)) {
                        return false;
                    }
                }
                return true;
            }
        }
        
        return false;
    }

    /**
     * Checks if there is a Rat in the river at the given position.
     * @param row the row to check
     * @param col the column to check
     * @return true if there is a Rat in the river, false otherwise
     */
    private boolean hasRatInRiver(int row, int col) {
        Square square = board.getSquare(row, col);
        if (square != null && square.getType() == SquareType.RIVIERE) {
            Piece piece = square.getPiece();
            return piece != null && piece.getRank() == Rank.RAT;
        }
        return false;
    }

    /**
     * Checks if a position is a Red player's trap.
     * @param row the row to check
     * @param col the column to check
     * @return true if it's a Red player's trap, false otherwise
     */
    private boolean isRedPlayerTrap(int row, int col) {
        return (row == 8 && col == 2) || (row == 8 && col == 4) || (row == 7 && col == 3);
    }

    /**
     * Checks if a position is a Blue player's trap.
     * @param row the row to check
     * @param col the column to check
     * @return true if it's a Blue player's trap, false otherwise
     */
    private boolean isBluePlayerTrap(int row, int col) {
        return (row == 0 && col == 2) || (row == 0 && col == 4) || (row == 1 && col == 3);
    }    /**
     * Switches the current player.
     */
    public void switchTurn() {
        currentPlayer = (currentPlayer.equals(player1)) ? player2 : player1;
    }

    /**
     * Checks if the game is over.
     * @return true if a player has entered the opponent's sanctuary, false otherwise
     */
    public boolean isGameOver() {
        // Check if any piece has entered the opponent's sanctuary
        for (int row = 0; row < board.getRowCount(); row++) {
            for (int col = 0; col < board.getColumnCount(); col++) {
                Square square = board.getSquare(row, col);
                if (square != null) {
                    SquareType type = square.getType();
                    Piece piece = square.getPiece();
                      if (piece != null) {
                        if ((type == SquareType.SANCTUAIRE_BLUE && piece.getOwner().equals(player1)) ||
                            (type == SquareType.SANCTUAIRE_RED && piece.getOwner().equals(player2))) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    /**
     * Gets the current player.
     * @return the current player
     */
    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    /**
     * Gets the board.
     * @return the game board
     */
    public Board getBoard() {
        return board;
    }

    /**
     * Gets player 1.
     * @return player 1
     */
    public Player getPlayer1() {
        return player1;
    }

    /**
     * Gets player 2.
     * @return player 2
     */
    public Player getPlayer2() {
        return player2;
    }
}
