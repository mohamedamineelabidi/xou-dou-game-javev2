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

    /**
     * Override equals to compare players by name.
     * @param obj the object to compare
     * @return true if players have the same name
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Player player = (Player) obj;
        return name != null ? name.equals(player.name) : player.name == null;
    }

    /**
     * Override hashCode to be consistent with equals.
     * @return hash code based on player name
     */
    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }

    /**
     * Override toString for better debugging.
     * @return string representation of the player
     */
    @Override
    public String toString() {
        return "Player{name='" + name + "'}";
    }
}
