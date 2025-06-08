package com.junglechess.db;

import com.junglechess.model.Player;

import java.sql.*;

/**
 * Database manager class for handling SQLite database operations.
 * Manages player authentication, registration, and game history tracking.
 */
public class DatabaseManager {
    private static final String DB_URL = "jdbc:sqlite:junglechess.db";
    private Connection connection;

    /**
     * Connects to the SQLite database file.
     * Creates the database file if it doesn't exist.
     */    public void connect() {
        try {
            connection = DriverManager.getConnection(DB_URL);
            System.out.println("Connected to SQLite database successfully.");
        } catch (SQLException e) {
            System.err.println("Error connecting to database: " + e.getMessage());
        }
    }

    /**
     * Creates the necessary tables if they don't exist.
     * - players table: stores user credentials
     * - game_history table: stores match results
     */
    public void setupTables() {
        String createPlayersTable = 
            "CREATE TABLE IF NOT EXISTS players (" +
            "id INTEGER PRIMARY KEY, " +
            "username TEXT UNIQUE, " +
            "password TEXT" +
            ");";

        String createGameHistoryTable = 
            "CREATE TABLE IF NOT EXISTS game_history (" +
            "id INTEGER PRIMARY KEY, " +
            "winner TEXT, " +
            "loser TEXT, " +
            "timestamp DATETIME DEFAULT CURRENT_TIMESTAMP" +
            ");";

        try (Statement stmt = connection.createStatement()) {
            stmt.execute(createPlayersTable);
            stmt.execute(createGameHistoryTable);
            System.out.println("Database tables set up successfully.");
        } catch (SQLException e) {
            System.err.println("Error creating tables: " + e.getMessage());
        }
    }

    /**
     * Creates a new player account.
     * @param username the username for the new account
     * @param password the password for the new account
     * @return true if account created successfully, false if username already exists
     */
    public boolean createPlayer(String username, String password) {
        String sql = "INSERT INTO players (username, password) VALUES (?, ?)";
        
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            pstmt.executeUpdate();
            System.out.println("Account created successfully for: " + username);
            return true;
        } catch (SQLException e) {
            if (e.getErrorCode() == 19) { // SQLITE_CONSTRAINT error code
                System.out.println("Username '" + username + "' already exists. Please choose a different username.");
            } else {
                System.err.println("Error creating player: " + e.getMessage());
            }
            return false;
        }
    }

    /**
     * Authenticates a player login.
     * @param username the username to authenticate
     * @param password the password to verify
     * @return Player object if authentication succeeds, null otherwise
     */
    public Player loginPlayer(String username, String password) {
        String sql = "SELECT username FROM players WHERE username = ? AND password = ?";
        
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    System.out.println("Login successful for: " + username);
                    return new Player(username);
                } else {
                    System.out.println("Invalid username or password.");
                    return null;
                }
            }
        } catch (SQLException e) {
            System.err.println("Error during login: " + e.getMessage());
            return null;
        }
    }

    /**
     * Saves the result of a completed game.
     * @param winner the player who won the game
     * @param loser the player who lost the game
     */
    public void saveGameResult(Player winner, Player loser) {
        String sql = "INSERT INTO game_history (winner, loser) VALUES (?, ?)";
        
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, winner.getName());
            pstmt.setString(2, loser.getName());
            pstmt.executeUpdate();
            System.out.println("Game result saved: " + winner.getName() + " defeated " + loser.getName());
        } catch (SQLException e) {
            System.err.println("Error saving game result: " + e.getMessage());
        }
    }

    /**
     * Retrieves and displays player statistics.
     * @param username the username to get statistics for
     */
    public void getPlayerHistory(String username) {
        String winsSql = "SELECT COUNT(*) FROM game_history WHERE winner = ?";
        String lossesSql = "SELECT COUNT(*) FROM game_history WHERE loser = ?";
        
        try {
            int wins = 0;
            int losses = 0;
            
            // Get wins count
            try (PreparedStatement pstmt = connection.prepareStatement(winsSql)) {
                pstmt.setString(1, username);
                try (ResultSet rs = pstmt.executeQuery()) {
                    if (rs.next()) {
                        wins = rs.getInt(1);
                    }
                }
            }
            
            // Get losses count
            try (PreparedStatement pstmt = connection.prepareStatement(lossesSql)) {
                pstmt.setString(1, username);
                try (ResultSet rs = pstmt.executeQuery()) {
                    if (rs.next()) {
                        losses = rs.getInt(1);
                    }
                }
            }
            
            System.out.println("\nPlayer Statistics for " + username + ":");
            System.out.println("Wins: " + wins);
            System.out.println("Losses: " + losses);
            System.out.println();
            
        } catch (SQLException e) {
            System.err.println("Error retrieving player history: " + e.getMessage());
        }
    }

    /**
     * Closes the database connection.
     */
    public void close() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Database connection closed.");
            }
        } catch (SQLException e) {
            System.err.println("Error closing database connection: " + e.getMessage());
        }
    }
}
