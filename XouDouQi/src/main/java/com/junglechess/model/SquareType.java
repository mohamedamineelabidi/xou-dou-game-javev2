package com.junglechess.model;

/**
 * Enum representing the different types of squares on the Jungle Chess board.
 */
public enum SquareType {
    /**
     * A regular tile where any piece can move (subject to other rules)
     */
    NORMAL,
    
    /**
     * Represents water tiles (river); only Rat can enter directly
     */
    RIVIERE,
    
    /**
     * Trap zone; any piece landing here is weakened and can be captured by any enemy piece
     */
    PIEGE,
    
    /**
     * Player 1's den; cannot be entered by opponent
     */
    SANCTUAIRE_RED,
    
    /**
     * Player 2's den; cannot be entered by opponent
     */
    SANCTUAIRE_BLUE
}
