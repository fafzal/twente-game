package com.twente.game.helper;

public class Ring {

    private int baseTile = 3;
    private int small = 3;
    private int medium = 3;
    private int big = 3;
    private int huge = 3;

    public int getBaseTile() {
        return baseTile;
    }

    public int getSmall() {
        return small;
    }

    public int getMedium() {
        return medium;
    }

    public int getBig() {
        return big;
    }

    public int getHuge() {
        return huge;
    }

    private void subtractBaseTitle() {
        baseTile = baseTile - 1;
    }

    private void subtractSmall() {
        small = small - 1;
    }

    private void subtractMedium() {
        medium = medium - 1;
    }

    private void subtractHuge() {
        huge = huge - 1;
    }

    private void subtractBig() {
        big = big - 1;
    }

    // if start tile has already been placed then this will happen.
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
}