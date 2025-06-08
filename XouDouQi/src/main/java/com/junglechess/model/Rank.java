package com.junglechess.model;

/**
 * Enum representing the eight animal ranks in Jungle Chess with their corresponding strength levels.
 * ELEPHANT(8) is the strongest and RAT(1) is the weakest.
 */
public enum Rank {
    ELEPHANT(8),
    LION(7),
    TIGRE(6),
    PANTHERE(5),
    CHIEN(4),
    LOUP(3),
    CHAT(2),
    RAT(1);

    private final int level;

    /**
     * Constructor to initialize the rank level
     * @param level the strength level of the animal
     */
    Rank(int level) {
        this.level = level;
    }

    /**
     * Get the strength level of the rank
     * @return the level value
     */
    public int getLevel() {
        return level;
    }
    
    /**
     * Get the strength value of the rank (alias for getLevel)
     * @return the value
     */
    public int getValue() {
        return level;
    }
}
