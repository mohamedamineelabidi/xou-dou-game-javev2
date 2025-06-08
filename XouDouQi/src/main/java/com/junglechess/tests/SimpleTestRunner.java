package com.junglechess.tests;

import com.junglechess.model.Piece;
import com.junglechess.model.Player;
import com.junglechess.model.Rank;
import com.junglechess.game.Game;

/**
 * Simple test runner to verify basic functionality without JUnit dependencies.
 * This demonstrates that the core game logic works correctly.
 */
public class SimpleTestRunner {
    
    private static int tests = 0;
    private static int passed = 0;
    
    public static void main(String[] args) {
        System.out.println("=== XOUDOUQI UNIT TEST RUNNER ===");
        System.out.println();
        
        testPieceCapture();
        testGameInitialization();
        testRankValues();
        
        System.out.println();
        System.out.println("=== TEST RESULTS ===");
        System.out.println("Tests run: " + tests);
        System.out.println("Passed: " + passed);
        System.out.println("Failed: " + (tests - passed));
        
        if (passed == tests) {
            System.out.println("✅ All tests passed!");
        } else {
            System.out.println("❌ Some tests failed.");
        }
    }
    
    private static void testPieceCapture() {
        System.out.println("Testing piece capture logic...");
        
        Player player1 = new Player("Player1");
        Player player2 = new Player("Player2");
        
        // Test: Rat can capture Elephant
        Piece rat = new Piece(Rank.RAT, player1);
        Piece elephant = new Piece(Rank.ELEPHANT, player2);
        assertTrue("Rat should capture Elephant", rat.canCapture(elephant));
        
        // Test: Elephant cannot capture Rat
        assertFalse("Elephant should NOT capture Rat", elephant.canCapture(rat));
        
        // Test: Higher rank captures lower
        Piece lion = new Piece(Rank.LION, player1);
        Piece cat = new Piece(Rank.CHAT, player2);
        assertTrue("Lion should capture Cat", lion.canCapture(cat));
        
        // Test: Same player pieces cannot capture each other
        Piece lion2 = new Piece(Rank.LION, player1);
        assertFalse("Same player pieces should not capture each other", lion.canCapture(lion2));
        
        // Test: Trapped piece mechanics
        elephant.setTrapped(true);
        assertTrue("Any piece should capture trapped enemy", cat.canCapture(elephant));
        
        System.out.println();
    }
    
    private static void testGameInitialization() {
        System.out.println("Testing game initialization...");
        
        Player player1 = new Player("Alice");
        Player player2 = new Player("Bob");
        Game game = new Game(player1, player2);
        
        // Test: Player 1 starts first
        assertTrue("Player 1 should start first", game.getCurrentPlayer() == player1);
        
        // Test: Game should not be over at start
        assertFalse("Game should not be over at start", game.isGameOver());
        
        // Test: Board should be initialized
        assertTrue("Board should be initialized", game.getBoard() != null);
        
        System.out.println();
    }
    
    private static void testRankValues() {
        System.out.println("Testing rank values...");
        
        // Test rank hierarchy
        assertTrue("Elephant rank should be 8", Rank.ELEPHANT.getValue() == 8);
        assertTrue("Lion rank should be 7", Rank.LION.getValue() == 7);
        assertTrue("Tiger rank should be 6", Rank.TIGRE.getValue() == 6);
        assertTrue("Panther rank should be 5", Rank.PANTHERE.getValue() == 5);
        assertTrue("Dog rank should be 4", Rank.CHIEN.getValue() == 4);
        assertTrue("Wolf rank should be 3", Rank.LOUP.getValue() == 3);
        assertTrue("Cat rank should be 2", Rank.CHAT.getValue() == 2);
        assertTrue("Rat rank should be 1", Rank.RAT.getValue() == 1);
        
        System.out.println();
    }
    
    private static void assertTrue(String message, boolean condition) {
        tests++;
        if (condition) {
            passed++;
            System.out.println("  ✅ " + message);
        } else {
            System.out.println("  ❌ " + message);
        }
    }
    
    private static void assertFalse(String message, boolean condition) {
        assertTrue(message, !condition);
    }
}
