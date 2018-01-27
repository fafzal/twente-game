package com.twente.game;

public class Ring {
    private int baseTile = 0;
    private int small = 1;
    private int medium = 2;
    private int big = 3;
    private int huge = 4;
    private int start = 5;

    public void subtractBaseTitle() {
        baseTile = baseTile -1;
    }
    public void subtractSmall() {
        small = small -1;
    }
    public void subtractMedium() {
        medium = medium - 1;
    }
    public void subtractHuge() {
        huge = huge - 1;
    }
    public void subtractBig() {
        big = big - 1;
    }



}
