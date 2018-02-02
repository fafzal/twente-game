package com.twente.game.helper;

public class Player {

    private String name;
    private Color colour;
    private Ring ring;

    public Player(String name, Color colour, Ring ring) {
        this.name = name;
        this.colour = colour;
        this.ring = ring;
    }

    public String getName() {
        return name;
    }

    public Ring getRing() {
        return ring;
    }

}
