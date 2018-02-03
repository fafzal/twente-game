package com.twente.game.helper;


import java.util.List;
import java.util.Map;

public class Coordinate {

    public boolean isValidCoordinates(Player player, int x, int y, Map <String, List <Integer>>[][] board) {

        boolean isEmpty =true;

        if (!(x >= 0 && x <= 5 && y >= 0 && y <= 5)) {
            return false;
        }

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                Map <String, List <Integer>> element = board[i][j];
                if (element != null && element.containsKey(player.getName())){
                    isEmpty = false;
                }

            }
        }

        if(isEmpty){
            return true;
        }

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                Map <String, List <Integer>> element = board[i][j];
                if (element != null && element.containsKey(player.getName())) {
                    if ((i - 1 < x || i + 1 > x) && (j - 1 < y || j + 1 > y)) {
                        return true;
                    }

                }
            }
        }

        return false;

    }

    public boolean isValidMoveForBase(String player, int x, int y, int move, Map <String, List <Integer>>[][] board1) {
        boolean isEmpty = true;
        if (!(x >= 1 && x <= 4 && y >= 1 && y <= 4)) {
            return false;
        }
        for (int i = 1; i <= 3; i++) {
            for (int j = 1; j <= 3; j++) {
                Map <String, List <Integer>> element = board1[i][j];
                if (element.size() != 0) {
                    isEmpty = false;

                }
            }
        }
        return isEmpty;
    }


    public void setAllPossibleCoordinates(Player player, int x, int y, Color color) {
        for (int i = 0; i < 5; i++) {
            player.addSinglePossibleMove(x + "," + i, color);
        }
        for (int j = 0; j < 5; j++) {
            player.addSinglePossibleMove(j + "," + y, color);
        }
    }
}
