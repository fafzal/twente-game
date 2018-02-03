package com.twente.game.helper;

import java.util.ArrayList;
import java.util.List;

public class Player {

    private String name;
    private Color colour;
    private Ring ring;

    public void setPossibleMoves(List <String> possibleMoves) {
        this.possibleMoves = possibleMoves;
    }

    private List <String> possibleMoves;

    public Player(String name, Color colour, Ring ring) {
        this.name = name;
        this.colour = colour;
        this.ring = ring;
        possibleMoves = new ArrayList <>();
    }

    public String getName() {
        return name;
    }

    public Ring getRing() {
        return ring;
    }

    public void addSinglePossibleMove(String move) {
        possibleMoves.add(move);
    }

    public List <String> getPossibleMoves() {
        return possibleMoves;
    }

}
