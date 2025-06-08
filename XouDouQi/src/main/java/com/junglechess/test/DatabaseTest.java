package com.junglechess.test;

import com.junglechess.db.DatabaseManager;
import com.junglechess.model.Player;

/**
 * Simple test to verify database functionality
 */
public class DatabaseTest {
    public static void main(String[] args) {
        DatabaseManager dbManager = new DatabaseManager();
        
        // Test connection
        System.out.println("=== Testing Database Connection ===");
        dbManager.connect();
        dbManager.setupTables();
        
        // Test creating users
        System.out.println("\n=== Testing User Creation ===");
        boolean user1Created = dbManager.createPlayer("testuser1", "password123");
        boolean user2Created = dbManager.createPlayer("testuser2", "password456");
        boolean duplicateUser = dbManager.createPlayer("testuser1", "differentpassword");
        
        System.out.println("User 1 created: " + user1Created);
        System.out.println("User 2 created: " + user2Created);
        System.out.println("Duplicate user created (should be false): " + duplicateUser);
        
        // Test authentication
        System.out.println("\n=== Testing Authentication ===");
        Player validLogin = dbManager.loginPlayer("testuser1", "password123");
        Player invalidLogin = dbManager.loginPlayer("testuser1", "wrongpassword");
        Player nonExistentUser = dbManager.loginPlayer("nonexistent", "password");
        
        System.out.println("Valid login successful: " + (validLogin != null));
        System.out.println("Invalid login failed: " + (invalidLogin == null));
        System.out.println("Non-existent user failed: " + (nonExistentUser == null));
        
        // Test game result saving
        System.out.println("\n=== Testing Game Result Saving ===");
        if (validLogin != null) {
            Player player2 = new Player("testuser2");
            dbManager.saveGameResult(validLogin, player2);
            dbManager.saveGameResult(player2, validLogin);
            dbManager.saveGameResult(validLogin, player2);
            
            System.out.println("Game results saved successfully");
        }
        
        // Test player history
        System.out.println("\n=== Testing Player History ===");
        dbManager.getPlayerHistory("testuser1");
        dbManager.getPlayerHistory("testuser2");
        
        // Clean up
        dbManager.close();
        System.out.println("\n=== Database Test Complete ===");
    }
}
