package com.twente.game.helper;

public class Ring {

    private int baseTile = 3;
    private int small = 3;
    private int medium = 3;
    private int big = 3;
    private int huge = 3;


    /*@ ensures result != null;

     */

    /**
     *
     * @return
     * gets the baseTile
     */
    public int getBaseTile() {
        return baseTile;
    }

    /*@ ensures result != null;

     */

    /**
     * gets the small ring
     * @return
     */
    public int getSmall() {
        return small;
    }

    /*@ ensures result != null;

     */

    /**
     * gets the medium ring
     * @return
     */
    public int getMedium() {
        return medium;
    }

    /*@ ensures result != null;
      */
    public int getBig() {
        return big;
    }

    /*@ ensures result != null;

     */

    /**
     * gets the huge ring
     * @return
     */
    public int getHuge() {
        return huge;
    }

    /*@ ensures baseTile != null;
        ensures baseTile >=1 && baseTile <= 3

     */

    /**
     * subtracts the ring which are placed on the board
     */
    private void subtractBaseTitle() {
        baseTile = baseTile - 1;
    }

    /*@ ensures small != null;
        ensures small >=1 && baseTile <= 3

     */

    /**
     * subtracts the ring which are placed on the board
     */

    private void subtractSmall() {
        small = small - 1;
    }

    /*@ ensures medium != null;
        ensures medium >=1 && baseTile <= 3

     */

    /**
     * subtracts the ring which are placed on the board
     */
    private void subtractMedium() {
        medium = medium - 1;
    }

    /*@ ensures huge != null;
        ensures huge >=1 && baseTile <= 3

     */

    /**
     * subtracts the ring which are placed on the board
     */
    private void subtractHuge() {
        huge = huge - 1;
    }

    /*@ ensures big != null;
        ensures big >=1 && baseTile <= 3

     */

    /**
     * subtracts the ring which are placed on the board
     */

    private void subtractBig() {
        big = big - 1;
    }

    /*@ ensures \result != null;

     */

    /**
     * subtracts the ring which are placed on the board
     * @param ring
     */
    public void subtract(int ring) {
        if (ring == 0) {
            subtractBaseTitle();
        } else if (ring == 1) {
            subtractSmall();
        } else if (ring == 2) {
            subtractMedium();
        } else if (ring == 3) {
            subtractBig();
        } else if (ring == 4) {
            subtractHuge();
        }
    }

    /*@ ensures \result != null;

     */

    /**
     * displays the rings which are left when they are used
     * @param ring
     * @return
     */
    public int getRoundLeft(int ring) {
        if (ring == 0) {
            return getBaseTile();
        } else if (ring == 1) {
            return getSmall();
        } else if (ring == 2) {
            return getMedium();
        } else if (ring == 3) {
            return getBig();
        } else {
            return getHuge();
        }

    }

}