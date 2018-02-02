package com.twente.game.core;

import com.twente.game.helper.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Board {

    SingleMove singleMove;
    Coordinate coordinate;

    private Map <String, List <Integer>> boardArray[][] = new HashMap[5][5];

    public Board(List <String> players) {
        initialize();
        singleMove = new SingleMove(players);
        coordinate = new Coordinate(players);
    }

    public boolean applySingleMove(Player player, int x, int y, int size, Color yellow) {

        Ring ring = player.getRing();
        if (coordinate.isValidCoordinates(player, x, y, boardArray) && ring.getRoundLeft(size) != 0) {
            ring.subtract(size);
            return singleMove.move(player, size, boardArray[x][y]);
        }
        return false;
    }

    private void initialize() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                boardArray[i][j] = new HashMap <>();
            }
        }
    }

}
