package com.junglechess.model;

/**
 * Class representing a player in the Jungle Chess game.
 */
public class Player {
    private String name;

    /**
     * Constructor to initialize a player with a name.
     * @param name the name of the player
     */
    public Player(String name) {
        this.name = name;
    }

    /**
     * Get the player's name.
     * @return the player's name
     */
    public String getName() {
        return name;
    }
}
