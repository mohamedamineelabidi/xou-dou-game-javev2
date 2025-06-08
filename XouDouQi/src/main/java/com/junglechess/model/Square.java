package com.junglechess.model;

/**
 * Class representing a square on the Jungle Chess board.
 */
public class Square {
    private Piece piece;
    private SquareType type;
    
    /**
     * Constructor to initialize a square with a type.
     * @param type the type of the square
     */
    public Square(SquareType type) {
        this.type = type;
        this.piece = null;
    }
    
    /**
     * Constructor to initialize a square with a type and piece.
     * @param type the type of the square
     * @param piece the piece on the square, or null if empty
     */
    public Square(SquareType type, Piece piece) {
        this.type = type;
        this.piece = piece;
    }
    
    /**
     * Get the piece on this square.
     * @return the piece, or null if empty
     */
    public Piece getPiece() {
        return piece;
    }
    
    /**
     * Set a piece on this square.
     * @param piece the piece to place, or null to clear
     */
    public void setPiece(Piece piece) {
        this.piece = piece;
    }
    
    /**
     * Get the type of this square.
     * @return the square type
     */
    public SquareType getType() {
        return type;
    }
}
