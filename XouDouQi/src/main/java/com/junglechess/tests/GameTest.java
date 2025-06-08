package com.junglechess.tests;

import com.junglechess.game.Game;
import com.junglechess.model.Board;
import com.junglechess.model.Piece;
import com.junglechess.model.Player;
import com.junglechess.model.Rank;
import com.junglechess.model.Square;
import com.junglechess.model.SquareType;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Unit tests for the Game class, focusing on move validation and game rules.
 */
public class GameTest {
    
    private Game game;
    private Player player1;
    private Player player2;
    
    @Before
    public public void setUp() {
        player1 = new Player("Player1");
        player2 = new Player("Player2");
        game = new Game(player1, player2);
    }
      @Test
    public public void player1_starts_first() {
        assertEquals(player1, game.getCurrentPlayer());
    }
    
    @Test
    public public void valid_adjacent_move_succeeds() {
        // Get a piece from player1 (should be at bottom of board)
        Board board = game.getBoard();
        
        // Find a piece that can make a valid move
        // Let's try moving a piece from row 8 to row 7 (one square up)
        boolean moveSucceeded = game.movePiece(player1, 8, 0, 7, 0);
        
        // The move should succeed if there's a piece there
        Square sourceSquare = board.getSquare(8, 0);
        if (sourceSquare.getPiece() != null && sourceSquare.getPiece().getOwner() == player1) {
            assertTrue(moveSucceeded);
        }
    }
    
    @Test
    public void invalid_diagonal_move_fails() {
        // Try to move diagonally (should fail)
        boolean moveSucceeded = game.movePiece(player1, 8, 0, 7, 1);
        
        // Should fail because diagonal moves are not allowed
        assertFalse(moveSucceeded);
    }
    
    @Test
    public void wrong_player_turn_fails() {
        // Player2 tries to move when it's Player1's turn
        boolean moveSucceeded = game.movePiece(player2, 0, 0, 1, 0);
        assertFalse(moveSucceeded);
    }
    
    @Test
    public void rat_can_enter_river() {
        // Create a custom board setup for testing
        Board board = game.getBoard();
        
        // Place a rat near the river
        Piece rat = new Piece(Rank.RAT, player1);
        board.getSquare(3, 2).setPiece(rat); // Place rat next to river
        
        // Try to move rat into river
        boolean moveSucceeded = game.movePiece(player1, 3, 2, 3, 3);
        
        // Should succeed because rats can enter rivers
        assertTrue(moveSucceeded);
    }
    
    @Test
    public void elephant_cannot_enter_river_directly() {
        // Create a custom board setup for testing
        Board board = game.getBoard();
        
        // Place an elephant near the river
        Piece elephant = new Piece(Rank.ELEPHANT, player1);
        board.getSquare(3, 2).setPiece(elephant); // Place elephant next to river
        
        // Try to move elephant into river
        boolean moveSucceeded = game.movePiece(player1, 3, 2, 3, 3);
        
        // Should fail because elephants cannot enter rivers
        assertFalse(moveSucceeded);
    }
    
    @Test
    public void lion_can_jump_over_river_horizontally() {
        // Create a custom board setup for testing
        Board board = game.getBoard();
        
        // Place a lion that can jump over river
        Piece lion = new Piece(Rank.LION, player1);
        board.getSquare(3, 1).setPiece(lion); // Place lion before river
        
        // Clear the destination square
        board.getSquare(3, 6).setPiece(null);
        
        // Try to jump over river horizontally
        boolean moveSucceeded = game.movePiece(player1, 3, 1, 3, 6);
        
        // Should succeed because lions can jump over rivers
        assertTrue(moveSucceeded);
    }
      @Test
    public void tiger_can_jump_over_river_vertically() {
        // Create a custom board setup for testing
        Board board = game.getBoard();
        
        // Place a tiger that can jump over river
        Piece tiger = new Piece(Rank.TIGRE, player1);
        board.getSquare(2, 3).setPiece(tiger); // Place tiger before river
        
        // Clear the destination square
        board.getSquare(5, 3).setPiece(null);
        
        // Try to jump over river vertically
        boolean moveSucceeded = game.movePiece(player1, 2, 3, 5, 3);
        
        // Should succeed because tigers can jump over rivers
        assertTrue(moveSucceeded);
    }
    
    @Test
    public void lion_cannot_jump_if_rat_blocks_path() {
        // Create a custom board setup for testing
        Board board = game.getBoard();
        
        // Place a lion that wants to jump over river
        Piece lion = new Piece(Rank.LION, player1);
        board.getSquare(3, 1).setPiece(lion);
        
        // Place a rat in the river to block the jump
        Piece rat = new Piece(Rank.RAT, player2);
        board.getSquare(3, 3).setPiece(rat); // Rat in river
        
        // Clear the destination square
        board.getSquare(3, 6).setPiece(null);
        
        // Try to jump over river (should fail due to rat blocking)
        boolean moveSucceeded = game.movePiece(player1, 3, 1, 3, 6);
        
        // Should fail because rat blocks the jump
        assertFalse(moveSucceeded);
    }
      @Test
    public void piece_in_enemy_trap_can_be_captured_by_weaker_piece() {
        // Create a custom board setup for testing
        Board board = game.getBoard();
        
        // Place a strong piece (elephant) in enemy trap
        Piece elephant = new Piece(Rank.ELEPHANT, player2);
        board.getSquare(8, 0).setPiece(elephant); // Red player's trap
        
        // Place a weak piece (cat) nearby
        Piece cat = new Piece(Rank.CHAT, player1);
        board.getSquare(7, 0).setPiece(cat);
        
        // Cat should be able to capture trapped elephant
        boolean moveSucceeded = game.movePiece(player1, 7, 0, 8, 0);
        
        // Should succeed because elephant is in enemy trap
        assertTrue(moveSucceeded);
    }
      @Test
    public void cannot_move_to_own_sanctuary() {
        // Create a custom board setup for testing
        Board board = game.getBoard();
        
        // Place a piece near player1's sanctuary
        Piece cat = new Piece(Rank.CHAT, player1);
        board.getSquare(7, 3).setPiece(cat);
        
        // Try to move into own sanctuary
        boolean moveSucceeded = game.movePiece(player1, 7, 3, 8, 3);
        
        // Should fail because pieces cannot enter their own sanctuary
        assertFalse(moveSucceeded);
    }
      @Test
    public void game_ends_when_piece_enters_enemy_sanctuary() {
        // Create a custom board setup for testing
        Board board = game.getBoard();
        
        // Place a piece near enemy sanctuary
        Piece cat = new Piece(Rank.CHAT, player1);
        board.getSquare(1, 3).setPiece(cat);
        
        // Move into enemy sanctuary
        boolean moveSucceeded = game.movePiece(player1, 1, 3, 0, 3);
        
        // Should succeed and game should be over
        assertTrue(moveSucceeded);
        assertTrue(game.isGameOver());
    }
      @Test
    public void cannot_capture_own_pieces() {
        // Create a custom board setup for testing
        Board board = game.getBoard();
        
        // Place two pieces of same player adjacent to each other
        Piece cat = new Piece(Rank.CHAT, player1);
        Piece dog = new Piece(Rank.CHIEN, player1);
        board.getSquare(4, 2).setPiece(cat);
        board.getSquare(4, 3).setPiece(dog);
        
        // Try to capture own piece
        boolean moveSucceeded = game.movePiece(player1, 4, 2, 4, 3);
        
        // Should fail because you cannot capture your own pieces
        assertFalse(moveSucceeded);
    }
    
    @Test
    public void turn_switches_after_valid_move() {
        // Make a valid move with player1
        Board board = game.getBoard();
        
        // Find a valid move for player1
        Piece piece = null;
        int fromRow = -1, fromCol = -1, toRow = -1, toCol = -1;
        
        // Look for a piece that can make a valid move
        for (int row = 6; row < 9; row++) {
            for (int col = 0; col < 7; col++) {
                Square square = board.getSquare(row, col);
                if (square.getPiece() != null && square.getPiece().getOwner() == player1) {
                    // Try to move up one square
                    if (row > 0 && board.getSquare(row - 1, col).getPiece() == null) {
                        fromRow = row;
                        fromCol = col;
                        toRow = row - 1;
                        toCol = col;
                        break;
                    }
                }
            }
            if (fromRow != -1) break;
        }
        
        if (fromRow != -1) {
            assertEquals(player1, game.getCurrentPlayer());
            game.movePiece(player1, fromRow, fromCol, toRow, toCol);
            assertEquals(player2, game.getCurrentPlayer());
        }
    }
}
