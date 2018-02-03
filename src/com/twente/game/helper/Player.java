package com.twente.game.helper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Player {

    private String name;
    private Ring ring;
    private Map <Color, List <String>> colorMovesMap;
    private List <Color> colors;

    public Player(String name, List <Color> colours, Ring ring) {
        this.name = name;
        this.ring = ring;
        this.colors = colours;
        colorMovesMap = new HashMap <>();
        for (Color color : colours) {
            colorMovesMap.put(color, new ArrayList <>());
        }
    }

    public List <Color> getColors() {
        return colors;
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
