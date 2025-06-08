package com.junglechess.model;

/**
 * Class representing the Jungle Chess game board.
 * The board is a 9x7 grid with special zones and pieces placed according to Jungle Chess rules.
 */
public class Board {
    private Square[][] grid = new Square[9][7];
    
    /**
     * Constructor to initialize the board with all squares and pieces.
     */
    public Board() {
        // Initialize all squares with default NORMAL type
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 7; col++) {
                grid[row][col] = new Square(SquareType.NORMAL);
            }
        }
        
        // Set up the board with special zones and pieces
        setupBoard();
    }
    
    /**
     * Get the square at the specified position.
     * @param row the row index (0-8)
     * @param col the column index (0-6)
     * @return the square at the specified position
     */
    public Square getSquare(int row, int col) {
        if (row >= 0 && row < 9 && col >= 0 && col < 7) {
            return grid[row][col];
        }
        return null;
    }
    
    /**
     * Sets up the board with special zones and places pieces in their initial positions.
     * This includes:
     * - River areas (RIVIERE)
     * - Trap zones (PIEGE)
     * - Sanctuaries (SANCTUAIRE_RED and SANCTUAIRE_BLUE)
     * - Initial placement of all 16 animal pieces
     */
    private void setupBoard() {
        // Create two players: Red (Player 1) and Blue (Player 2)
        Player playerRed = new Player("Red");
        Player playerBlue = new Player("Blue");
        
        // Set up river (RIVIERE) - Columns 1-5, Rows 3-5
        for (int row = 3; row < 6; row++) {
            for (int col = 1; col < 6; col++) {
                // No river on column 3
                if (col != 3) {
                    grid[row][col] = new Square(SquareType.RIVIERE);
                }
            }
        }
        
        // Set up dens (SANCTUAIRE)
        grid[0][3] = new Square(SquareType.SANCTUAIRE_BLUE);
        grid[8][3] = new Square(SquareType.SANCTUAIRE_RED);
        
        // Set up traps (PIEGE) around dens
        // Blue player's traps (around SANCTUAIRE_BLUE)
        grid[0][2] = new Square(SquareType.PIEGE);
        grid[0][4] = new Square(SquareType.PIEGE);
        grid[1][3] = new Square(SquareType.PIEGE);
        
        // Red player's traps (around SANCTUAIRE_RED)
        grid[8][2] = new Square(SquareType.PIEGE);
        grid[8][4] = new Square(SquareType.PIEGE);
        grid[7][3] = new Square(SquareType.PIEGE);
        
        // Place Blue player's pieces
        grid[0][0] = new Square(SquareType.NORMAL, new Piece(Rank.LION, playerBlue));
        grid[0][6] = new Square(SquareType.NORMAL, new Piece(Rank.TIGRE, playerBlue));
        grid[1][1] = new Square(SquareType.NORMAL, new Piece(Rank.CHIEN, playerBlue));
        grid[1][5] = new Square(SquareType.NORMAL, new Piece(Rank.CHAT, playerBlue));
        grid[2][0] = new Square(SquareType.NORMAL, new Piece(Rank.RAT, playerBlue));
        grid[2][2] = new Square(SquareType.NORMAL, new Piece(Rank.PANTHERE, playerBlue));
        grid[2][4] = new Square(SquareType.NORMAL, new Piece(Rank.LOUP, playerBlue));
        grid[2][6] = new Square(SquareType.NORMAL, new Piece(Rank.ELEPHANT, playerBlue));
        
        // Place Red player's pieces (mirror image of Blue's pieces)
        grid[8][0] = new Square(SquareType.NORMAL, new Piece(Rank.TIGRE, playerRed));
        grid[8][6] = new Square(SquareType.NORMAL, new Piece(Rank.LION, playerRed));
        grid[7][1] = new Square(SquareType.NORMAL, new Piece(Rank.CHAT, playerRed));
        grid[7][5] = new Square(SquareType.NORMAL, new Piece(Rank.CHIEN, playerRed));
        grid[6][0] = new Square(SquareType.NORMAL, new Piece(Rank.ELEPHANT, playerRed));
        grid[6][2] = new Square(SquareType.NORMAL, new Piece(Rank.LOUP, playerRed));
        grid[6][4] = new Square(SquareType.NORMAL, new Piece(Rank.PANTHERE, playerRed));
        grid[6][6] = new Square(SquareType.NORMAL, new Piece(Rank.RAT, playerRed));
    }
    
    /**
     * Get the number of rows on the board.
     * @return the number of rows
     */
    public int getRowCount() {
        return grid.length;
    }
    
    /**
     * Get the number of columns on the board.
     * @return the number of columns
     */
    public int getColumnCount() {
        return grid[0].length;
    }
}
