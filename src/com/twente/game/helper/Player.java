package com.twente.game.helper;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;

public class Player {

    private String name;
    private Ring ring;
    private Map <Color, List <String>> colorMovesMap;
    private List <Color> colors;

    /*@ requires name != null;
      @ requires ring ! = null;
      @ requires colour != null;
      @ ensures getRing == ring;
      @ ensures getName == name;

     */

    /**
     *
     * @param name
     * get the name of the player
     * @param colours
     * takes the colour of the rings as a list
     * @param ring
     * get the ring of the player
     */
    public Player(String name, List <Color> colours, Ring ring) {
        this.name = name;
        this.ring = ring;
        this.colors = colours;
        colorMovesMap = new HashMap <>();
        for (Color color : colours) {
            colorMovesMap.put(color, new ArrayList <>());
        }
    }
    /*@ ensures \result != null;
     */

    /**
     *
     * @return
     * returns the colour of the player
     */
    public List <Color> getColors() {
        return colors;
    }


    /*@ ensures \result != null;

     */

    /**
     *
     * @return
     * return the name of the player
     */
    public String getName() {
        return name;
    }

    /*@ ensures \result != null;

     */

    /**
     *
     * @return
     * returns the rings of the player
     */
    public Ring getRing() {
        return ring;
    }

    /*@ ensures result != null;

     */

    /**
     *
     * @param move
     * take the move as a parameter for the player
     * @param color
     * take the color as a parameter for the player
     */
    public void addSinglePossibleMove(String move, Color color) {
        colorMovesMap.get(color).add(move);
    }

    /*@ ensures result != null;

     */

    /**
     *
     * @param color
     * take the color for the move for the player
     * @return
     * returns the colour of the player for that move
     */
    public List <String> getPossibleMoves(Color color) {
        return colorMovesMap.get(color);
    }

}
