package com.junglechess.tests;

import com.junglechess.model.Piece;
import com.junglechess.model.Player;
import com.junglechess.model.Rank;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Unit tests for the Piece class, focusing on capture logic and special rules.
 */
public class PieceTest {
    
    private Player player1;
    private Player player2;
    
    @Before
    public void setUp() {
        player1 = new Player("Player1");
        player2 = new Player("Player2");
    }
      @Test
    public void rat_can_capture_elephant() {
        Piece rat = new Piece(Rank.RAT, player1);
        Piece elephant = new Piece(Rank.ELEPHANT, player2);
        assertTrue(rat.canCapture(elephant));
    }
    
    @Test
    public void elephant_cannot_capture_rat() {
        Piece elephant = new Piece(Rank.ELEPHANT, player1);
        Piece rat = new Piece(Rank.RAT, player2);
        assertFalse(elephant.canCapture(rat));
    }    @Test
    public void higher_rank_captures_lower() {
        Piece lion = new Piece(Rank.LION, player1);
        Piece tiger = new Piece(Rank.TIGRE, player2);
        assertTrue(lion.canCapture(tiger));
        
        Piece tiger2 = new Piece(Rank.TIGRE, player1);
        Piece panther = new Piece(Rank.PANTHERE, player2);
        assertTrue(tiger2.canCapture(panther));
        
        Piece panther2 = new Piece(Rank.PANTHERE, player1);
        Piece dog = new Piece(Rank.CHIEN, player2);
        assertTrue(panther2.canCapture(dog));
        
        Piece dog2 = new Piece(Rank.CHIEN, player1);
        Piece wolf = new Piece(Rank.LOUP, player2);
        assertTrue(dog2.canCapture(wolf));
        
        Piece wolf2 = new Piece(Rank.LOUP, player1);
        Piece cat = new Piece(Rank.CHAT, player2);
        assertTrue(wolf2.canCapture(cat));
        
        Piece cat2 = new Piece(Rank.CHAT, player1);
        Piece rat2 = new Piece(Rank.RAT, player2);
        assertTrue(cat2.canCapture(rat2));
    }
      @Test
    public void equal_ranks_cannot_capture_each_other() {
        Piece lion1 = new Piece(Rank.LION, player1);
        Piece lion2 = new Piece(Rank.LION, player2);
        assertFalse(lion1.canCapture(lion2));
        assertFalse(lion2.canCapture(lion1));
        
        Piece rat1 = new Piece(Rank.RAT, player1);
        Piece rat2 = new Piece(Rank.RAT, player2);
        assertFalse(rat1.canCapture(rat2));
        assertFalse(rat2.canCapture(rat1));
    }
      @Test
    public void rat_cannot_capture_rat() {
        Piece rat1 = new Piece(Rank.RAT, player1);
        Piece rat2 = new Piece(Rank.RAT, player2);
        assertFalse(rat1.canCapture(rat2));
        assertFalse(rat2.canCapture(rat1));
    }    @Test
    public void lower_rank_cannot_capture_higher() {
        Piece cat = new Piece(Rank.CHAT, player1);
        Piece wolf = new Piece(Rank.LOUP, player2);
        assertFalse(cat.canCapture(wolf));
        
        Piece dog = new Piece(Rank.CHIEN, player1);
        Piece panther = new Piece(Rank.PANTHERE, player2);
        assertFalse(dog.canCapture(panther));
        
        Piece tiger = new Piece(Rank.TIGRE, player1);
        Piece lion = new Piece(Rank.LION, player2);
        assertFalse(tiger.canCapture(lion));
    }    @Test
    public void same_player_pieces_cannot_capture_each_other() {
        Piece lion = new Piece(Rank.LION, player1);
        Piece cat = new Piece(Rank.CHAT, player1);
        assertFalse(lion.canCapture(cat));
        assertFalse(cat.canCapture(lion));
    }    @Test
    public void trapped_piece_can_be_captured_by_any_enemy_piece() {
        // Create pieces
        Piece cat = new Piece(Rank.CHAT, player1);
        Piece elephant = new Piece(Rank.ELEPHANT, player2);
        
        // Set elephant as trapped
        elephant.setTrapped(true);
        
        // Cat should be able to capture trapped elephant
        assertTrue(cat.canCapture(elephant));
        
        // Reset and test the opposite
        elephant.setTrapped(false);
        cat.setTrapped(true);
        
        // Elephant should be able to capture trapped cat
        assertTrue(elephant.canCapture(cat));
    }    @Test
    public void piece_rank_values_are_correct() {
        assertEquals(8, Rank.ELEPHANT.getValue());
        assertEquals(7, Rank.LION.getValue());
        assertEquals(6, Rank.TIGRE.getValue());
        assertEquals(5, Rank.PANTHERE.getValue());
        assertEquals(4, Rank.CHIEN.getValue());
        assertEquals(3, Rank.LOUP.getValue());
        assertEquals(2, Rank.CHAT.getValue());
        assertEquals(1, Rank.RAT.getValue());
    }
}
