package com.junglechess.model;

/**
 * Class representing an animal piece in the Jungle Chess game.
 */
public class Piece {
    private Rank rank;
    private Player owner;
    private boolean trapped; // Whether this piece is currently in an enemy trap

    /**
     * Constructor to initialize a piece with a rank and owner.
     * @param rank the rank of the animal piece
     * @param owner the player who owns the piece
     */
    public Piece(Rank rank, Player owner) {
        this.rank = rank;
        this.owner = owner;
        this.trapped = false; // Pieces are not trapped upon creation
    }

    /**
     * Get the rank of the piece.
     * @return the rank of the animal piece
     */
    public Rank getRank() {
        return rank;
    }

    /**
     * Get the owner of the piece.
     * @return the player who owns the piece
     */
    public Player getOwner() {
        return owner;
    }

    /**
     * Set the trapped state of this piece.
     * @param trapped true if the piece is in an enemy trap, false otherwise
     */
    public void setTrapped(boolean trapped) {
        this.trapped = trapped;
    }

    /**
     * Check if this piece is currently trapped.
     * @return true if the piece is in an enemy trap, false otherwise
     */
    public boolean isTrapped() {
        return trapped;
    }

    /**
     * Determines if this piece can capture a target piece.
     * Rules:
     * - A piece cannot capture a piece owned by the same player
     * - Generally, a piece can capture another if its rank is >= the target's rank
     * - Exception: Rat can capture Elephant
     * - Any piece can capture a trapped enemy piece
     * @param target the piece to be captured
     * @return true if capture is possible, false otherwise
     */
    public boolean canCapture(Piece target) {
        // Cannot capture if target is null (empty square)
        if (target == null) {
            return false;
        }
        
        // Cannot capture own pieces
        if (this.owner == target.getOwner()) {
            return false;
        }
        
        // Any piece can capture a trapped enemy piece
        if (target.isTrapped()) {
            return true;
        }
        
        // Special case: RAT can capture ELEPHANT
        if (this.rank == Rank.RAT && target.getRank() == Rank.ELEPHANT) {
            return true;
        }
        
        // General rule: Can capture if rank is >= target's rank
        return this.rank.getLevel() >= target.getRank().getLevel();
    }
}
