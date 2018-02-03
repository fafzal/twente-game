package com.twente.game.helper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Player {

    private String name;
    private Ring ring;
    private Map <Color, List <String>> colorMovesMap;


    public void setPossibleMoves(List <String> possibleMoves, Color color) {
        this.colorMovesMap.put(color, possibleMoves);
    }

    public Player(String name, List <Color> colours, Ring ring) {
        this.name = name;
        this.ring = ring;
        colorMovesMap = new HashMap <>();
        for (Color color : colours) {
            colorMovesMap.put(color, new ArrayList <>());
        }
    }

    public String getName() {
        return name;
    }

    public Ring getRing() {
        return ring;
    }

    public void addSinglePossibleMove(String move, Color color) {
        colorMovesMap.get(color).add(move);
    }

    public List <String> getPossibleMoves(Color color) {
        return colorMovesMap.get(color);
    }

}
